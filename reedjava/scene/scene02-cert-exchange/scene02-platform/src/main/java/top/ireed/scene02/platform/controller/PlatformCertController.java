package top.ireed.scene02.platform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import top.ireed.scene02.common.model.CertPayload;
import top.ireed.scene02.common.model.FaultMode;
import top.ireed.scene02.common.service.CertParty;

/**
 * 8.1.1 获取平台公钥证书
 * <p>
 * 资方方(中信)调用本接口获取平台方(京东方)的公钥证书,获取后可本地缓存。
 * 证书有效期1年,轮换时直接覆盖,同一时刻只有一张证书在用。
 */
@RestController
@RequestMapping("/scene02/api/8.1.1")
public class PlatformCertController {

    private final CertParty party;

    public PlatformCertController(CertParty party) {
        this.party = party;
    }

    /**
     * 获取平台公钥证书
     *
     * @return 证书载荷
     */
    @GetMapping("/platform-cert")
    public CertPayload platformCert() {
        if (party.faultMode() == FaultMode.CERT_HTTP_500) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "平台证书服务暂不可用");
        }
        return party.selfPayload();
    }
}
