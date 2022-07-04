/*
 * Author:   reedsource
 */
package top.ireed.found.dict;

import top.ireed.deal.DealJackson;
import top.ireed.deal.DealLog;
import top.ireed.found.dict.baidu.TransApi;
import top.ireed.found.dict.entity.DictReturn;
import top.ireed.found.dict.entity.TransResult;
import top.ireed.general.TopException;

/**
 * 功能简述:
 * 〈
 * 翻译api接入
 * 当前支持百度翻译
 * 〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/3 15:40
 * reedsource@189.cn
 */
public class FoundDict {

	/**
	 * 百度翻译id
	 */
	private final String appId;
	/**
	 * 百度翻译key
	 */
	private final String securityKey;

	/**
	 * @param appId       百度翻译id
	 * @param securityKey 百度翻译key
	 */
	public FoundDict(String appId, String securityKey) throws TopException {
		if (appId.isEmpty() || securityKey.isEmpty()) {
			throw new TopException("连接百度翻译api失败,id或key为空");
		}
		this.appId = appId;
		this.securityKey = securityKey;
	}

	public String dict(String query) {
		return dict(query, "en", "zh");
	}


	/**
	 * 调用百度翻译api
	 *
	 * @param query 需要翻译的文本
	 * @param from  翻译源文件 auth自动检测 zh中文 en英文
	 * @param to    译文语言 不可为auth
	 * @return 翻译结果
	 */
	public String dict(String query, String from, String to) {
		try {
			//
			TransApi api = new TransApi(appId, securityKey);
			//注意 这里百度返回的是 {"from":"en","to":"zh","trans_result":[{"src":"reed","dst":"\u82a6\u82c7"}]}
			//注意 trans_result是数组
			DictReturn dictReturn = DealJackson.toObject(api.getTransResult(query, from, to), DictReturn.class);
			TransResult[] transResults = dictReturn.getTrans_result();
			StringBuilder m = new StringBuilder();
			for (TransResult transResult : transResults) {
				m.append(transResult.getDst());
			}
			return m.toString();
		} catch (Exception e) {
			DealLog.log("翻译工具异常 翻译词汇", query, e);
		}
		return "";
	}
}
