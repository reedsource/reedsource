package top.ireed.scene02.common.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import top.ireed.scene02.common.service.CertParty;

/**
 * 证书交换定时任务
 * <p>
 * 双方各自定时执行:拉取对端证书(按每日策略) -> 使用对端公钥加密 -> 发送对端 -> 校验解密结果。
 * 默认间隔5分钟,可通过 scene02.exchange-interval-ms 调整。
 */
public class CertExchangeJob {

    private static final Logger log = LoggerFactory.getLogger(CertExchangeJob.class);

    private final CertParty party;

    public CertExchangeJob(CertParty party) {
        this.party = party;
    }

    /**
     * 定时执行一次证书交换
     */
    @Scheduled(fixedDelayString = "${scene02.exchange-interval-ms:300000}",
            initialDelayString = "${scene02.exchange-initial-delay-ms:5000}")
    public void exchange() {
        log.info("定时证书交换开始 角色={}", party.role());
        party.doExchange();
    }
}
