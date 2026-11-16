package top.ireed.scene02.common.cert;

import top.ireed.scene02.common.model.ExchangeRecord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 证书交换流水
 * <p>
 * 内存态流水,记录证书拉取、加解密、校验、轮换与异常,用于方案文档各案例的证据留存。
 * 每个参与方持有一份,互不共享。
 */
public class CertExchangeLog {

    private static final int MAX_RECORDS = 500;

    private final AtomicLong sequence = new AtomicLong();

    private final List<ExchangeRecord> records = Collections.synchronizedList(new ArrayList<>());

    /**
     * 记录一条流水
     *
     * @param action      操作类型
     * @param role        执行方角色
     * @param fingerprint 证书指纹
     * @param result      结果
     * @param detail      描述
     */
    public void add(String action, String role, String fingerprint, String result, String detail) {
        ExchangeRecord record = new ExchangeRecord();
        record.setId(sequence.incrementAndGet());
        record.setAction(action);
        record.setRole(role);
        record.setFingerprint(fingerprint);
        record.setResult(result);
        record.setDetail(detail);
        record.setTime(System.currentTimeMillis());
        records.add(record);
        if (records.size() > MAX_RECORDS) {
            records.remove(0);
        }
    }

    /**
     * 全部流水
     *
     * @return 流水列表
     */
    public List<ExchangeRecord> list() {
        synchronized (records) {
            return new ArrayList<>(records);
        }
    }

    /**
     * 清空流水
     */
    public void clear() {
        records.clear();
    }
}
