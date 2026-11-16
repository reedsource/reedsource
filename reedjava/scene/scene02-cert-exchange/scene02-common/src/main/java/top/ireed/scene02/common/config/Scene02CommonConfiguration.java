package top.ireed.scene02.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.ireed.scene02.common.cert.CertExchangeLog;
import top.ireed.scene02.common.cert.TrustRootProvider;
import top.ireed.scene02.common.controller.AdminController;
import top.ireed.scene02.common.controller.MessageController;
import top.ireed.scene02.common.job.CertExchangeJob;
import top.ireed.scene02.common.service.CertParty;

/**
 * 场景02公共装配
 * <p>
 * 公共模块为纯工具类库,不做组件扫描,由平台方与资方方各自显式导入本配置后注册共享Bean。
 * 平台方与资方方各自独立进程,各自持有一份流水与证书仓库,仅共享同一份信任根。
 */
@Configuration
public class Scene02CommonConfiguration {

    /**
     * 交换流水
     *
     * @return 流水
     */
    @Bean
    public CertExchangeLog certExchangeLog() {
        return new CertExchangeLog();
    }

    /**
     * 共享信任根
     *
     * @param props 场景配置
     * @return 信任根
     */
    @Bean
    public TrustRootProvider trustRootProvider(Scene02Properties props) {
        return new TrustRootProvider(props.getTrustRootPassword());
    }

    /**
     * 证书参与方
     *
     * @param props       场景配置
     * @param exchangeLog 交换流水
     * @param trustRoot   共享信任根
     * @return 参与方
     */
    @Bean
    public CertParty certParty(Scene02Properties props, CertExchangeLog exchangeLog, TrustRootProvider trustRoot) {
        return new CertParty(props, exchangeLog, trustRoot);
    }

    /**
     * 报文接收接口
     *
     * @param party 参与方
     * @return 控制器
     */
    @Bean
    public MessageController messageController(CertParty party) {
        return new MessageController(party);
    }

    /**
     * 场景管理接口
     *
     * @param party 参与方
     * @return 控制器
     */
    @Bean
    public AdminController adminController(CertParty party) {
        return new AdminController(party);
    }

    /**
     * 证书交换定时任务
     *
     * @param party 参与方
     * @return 任务
     */
    @Bean
    public CertExchangeJob certExchangeJob(CertParty party) {
        return new CertExchangeJob(party);
    }
}
