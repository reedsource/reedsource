package top.ireed.found.dict.baidu;

import java.util.HashMap;
import java.util.Map;

public class TransApi {
	private static final String TRANS_API_HOST = "https://api.fanyi.baidu.com/api/trans/vip/translate";

	private final Object appid;
	private final String securityKey;

	public TransApi(Object appid, String securityKey) {
		this.appid = appid;
		this.securityKey = securityKey;
	}

	public String getTransResult(String query, String from, String to) {
		Map<String, Object> params = buildParams(query, from, to);
		return HttpGet.get(TRANS_API_HOST, params);
	}

	private Map<String, Object> buildParams(String query, String from, String to) {
		Map<String, Object> params = new HashMap<>();
		params.put("q", query);
		params.put("from", from);
		params.put("to", to);

		params.put("appid", appid);

		// 随机数
		String salt = String.valueOf(System.currentTimeMillis());
		params.put("salt", salt);

		// 签名
		String src = appid + query + salt + securityKey; // 加密前的原文
		params.put("sign", MD5.md5(src));

		return params;
	}

}
