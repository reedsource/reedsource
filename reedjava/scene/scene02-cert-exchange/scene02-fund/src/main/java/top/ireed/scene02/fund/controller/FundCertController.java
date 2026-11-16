package top.ireed.scene02.fund.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import top.ireed.scene02.common.model.CertPayload;
import top.ireed.scene02.common.model.FaultMode;
import top.ireed.scene02.common.service.CertParty;

/**
 * 8.1.2 获取资方公钥证书
 * <p>
 * 平台方(京东方)调用本接口获取资方方(中信)的公钥证书。
 * 证书有效期1年,不支持新旧证书共存,轮换时直接覆盖。
 */
@RestController
@RequestMapping("/scene02/api/8.1.2")
public class FundCertController {

    private final CertParty party;

    public FundCertController(CertParty party) {
        this.party = party;
    }

    /**
     * 获取资方公钥证书
     *
     * @return 证书载荷
     */
    @GetMapping("/fund-cert")
    public CertPayload fundCert() {
        if (party.faultMode() == FaultMode.CERT_HTTP_500) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "资方证书服务暂不可用");
        }
        return party.selfPayload();
    }
}
