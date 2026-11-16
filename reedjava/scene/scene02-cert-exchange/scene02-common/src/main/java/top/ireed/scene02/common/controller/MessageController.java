package top.ireed.scene02.common.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ireed.scene02.common.model.EncryptedEnvelope;
import top.ireed.scene02.common.model.MessageResult;
import top.ireed.scene02.common.service.CertParty;

/**
 * 网关报文接收接口
 * <p>
 * 双方同路径:接收对端加密报文,使用本端私钥解密。
 * 解密失败(证书不一致)时返回409,发送方据此强制刷新证书缓存后重试。
 */
@RestController
@RequestMapping("/scene02/api/message")
public class MessageController {

    private final CertParty party;

    public MessageController(CertParty party) {
        this.party = party;
    }

    /**
     * 接收加密报文并解密
     *
     * @param envelope 加密信封
     * @return 解密结果
     */
    @PostMapping
    public MessageResult receive(@RequestBody EncryptedEnvelope envelope) {
        return party.decrypt(envelope);
    }
}
