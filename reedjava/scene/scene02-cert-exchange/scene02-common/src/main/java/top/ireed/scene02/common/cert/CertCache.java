package top.ireed.scene02.common.cert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.ireed.scene02.common.model.CertPayload;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 对端证书本地缓存
 * <p>
 * 对应方案文档的拉取策略:每次请求都会判断是否需要拉取,
 * 但在证书有效期内,每个自然日只在前若干次请求真正向后端拉取,其余请求直接命中缓存。
 * force=true 时绕过每日次数限制,用于解密失败后的强制刷新。
 */
public class CertCache {

    private static final Logger log = LoggerFactory.getLogger(CertCache.class);

    /**
     * 缓存的对端证书
     */
    private volatile CertPayload payload;

    /**
     * 最近一次真实拉取日期 格式 yyyy-MM-dd
     */
    private volatile String lastPullDate;

    /**
     * 当日真实拉取次数
     */
    private volatile int todayPullCount;

    /**
     * 累计真实拉取次数
     */
    private volatile int totalPullCount;

    /**
     * 最近一次错误信息
     */
    private volatile String lastError;

    /**
     * 最近一次刷新时间
     */
    private volatile long lastRefreshTime;

    /**
     * 每日真实拉取次数上限
     */
    private final int dailyPullThreshold;

    public CertCache(int dailyPullThreshold) {
        this.dailyPullThreshold = dailyPullThreshold;
    }

    /**
     * 获取对端证书
     *
     * @param force   是否强制刷新
     * @param fetcher 真实拉取动作
     * @return 对端证书
     * @throws Exception 拉取或校验失败且无可用缓存
     */
    public CertPayload get(boolean force, ThrowingSupplier<CertPayload> fetcher) throws Exception {
        if (force || payload == null || needPullToday()) {
            return pull(fetcher);
        }
        return payload;
    }

    /**
     * 是否当日仍允许真实拉取
     *
     * @return true 表示应当拉取
     */
    public boolean needPullToday() {
        String today = today();
        if (!today.equals(lastPullDate)) {
            return true;
        }
        return todayPullCount < dailyPullThreshold;
    }

    private CertPayload pull(ThrowingSupplier<CertPayload> fetcher) throws Exception {
        try {
            CertPayload fresh = fetcher.get();
            this.payload = fresh;
            String today = today();
            if (!today.equals(this.lastPullDate)) {
                this.lastPullDate = today;
                this.todayPullCount = 0;
            }
            this.todayPullCount = this.todayPullCount + 1;
            this.totalPullCount = this.totalPullCount + 1;
            this.lastRefreshTime = System.currentTimeMillis();
            this.lastError = null;
            log.info("拉取对端证书成功 指纹={} 当日拉取次数={}", fresh.getFingerprint(), this.todayPullCount);
            return fresh;
        } catch (Exception e) {
            this.lastError = e.getMessage();
            log.warn("拉取对端证书失败 使用缓存兜底 原因={}", e.getMessage());
            throw e;
        }
    }

    private String today() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * 清空缓存
     */
    public void clear() {
        this.payload = null;
        this.lastPullDate = null;
        this.todayPullCount = 0;
    }

    public CertPayload payload() {
        return payload;
    }

    public String lastPullDate() {
        return lastPullDate;
    }

    public int todayPullCount() {
        return todayPullCount;
    }

    public int totalPullCount() {
        return totalPullCount;
    }

    public String lastError() {
        return lastError;
    }

    public long lastRefreshTime() {
        return lastRefreshTime;
    }

    public int dailyPullThreshold() {
        return dailyPullThreshold;
    }

    /**
     * 支持抛出受检异常的拉取动作
     */
    public interface ThrowingSupplier<T> {
        T get() throws Exception;
    }
}
