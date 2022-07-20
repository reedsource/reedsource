/*
 * Author:   reedsource
 */
package top.ireed.found.dict;

import top.ireed.deal.DealJackson;
import top.ireed.deal.DealLog;
import top.ireed.found.FoundSqlite;
import top.ireed.found.dict.baidu.TransApi;
import top.ireed.found.dict.entity.DictReturn;
import top.ireed.found.dict.entity.DictTable;
import top.ireed.found.dict.entity.TransResult;
import top.ireed.general.TopException;

import java.util.List;
import java.util.Objects;

/**
 * 功能简述:
 * 〈
 * 翻译工具 本地缓存
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
	 * 本地缓存的数据库地址,当前为sqlite
	 */
	private final String dataFile;

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
		this.dataFile = null;
	}

	/**
	 * @param appId       百度翻译id
	 * @param securityKey 百度翻译key
	 * @param dataFile    本地缓存数据库地址  sqlite
	 */
	public FoundDict(String appId, String securityKey, String dataFile) throws TopException {
		if (appId.isEmpty() || securityKey.isEmpty()) {
			throw new TopException("连接百度翻译api失败,id或key为空");
		}
		this.appId = appId;
		this.securityKey = securityKey;
		this.dataFile = dataFile;
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
			// 读取本地缓存 不同表示有缓存 返回缓存
			String queryTo = opinion(query);
			if (!Objects.equals(query, queryTo)) {
				return queryTo;
			}
			// 无缓存 调用翻译api
			TransApi api = new TransApi(appId, securityKey);
			//注意 这里百度返回的是 {"from":"en","to":"zh","trans_result":[{"src":"reed","dst":"\u82a6\u82c7"}]}
			//注意 trans_result是数组
			DictReturn dictReturn = DealJackson.toObject(api.getTransResult(query, from, to), DictReturn.class);
			TransResult[] transResults = dictReturn.getTrans_result();
			StringBuilder m = new StringBuilder();
			for (TransResult transResult : transResults) {
				m.append(transResult.getDst());
			}
			queryTo = m.toString();
			//将翻译结果与需要翻译的文本做对比,如果不同 记录到缓存数据库
			if (!Objects.equals(query, queryTo)) {
				//本地缓存路径为空,不读取缓存
				if (dataFile != null) {
					//创建数据库对象
					FoundSqlite foundSqlite = new FoundSqlite(dataFile);
					//写入数据库
					foundSqlite.insert(new DictTable(query, queryTo));
				}
			}
			return queryTo;
		} catch (Exception e) {
			DealLog.log("翻译工具异常 翻译词汇", query, e);
		}
		return "";
	}

	/**
	 * 翻译前本地预对比处理
	 * 如果本地无缓存 将返回原文本
	 * 需要对是否翻译进行判断
	 *
	 * @param m 待处理文本
	 * @return 翻译后文本
	 */
	private String opinion(String m) throws TopException {
		//本地缓存路径为空,不读取缓存
		if (dataFile == null) {
			return m;
		}


		//创建数据库对象
		FoundSqlite foundSqlite = new FoundSqlite(dataFile);
		//翻译数据类
		DictTable dictTable = new DictTable(m);

		//2 获取翻译结果
		//判断m是否需要翻译
		//数据库返回数据
		List<DictTable> resultSet = null;
		//翻译
		List<DictTable> list1 = null;
		try {
			//先判断数据库中是否已经存在
			if (foundSqlite.getSum(dictTable) > 0) {
				resultSet = foundSqlite.get(dictTable);
			}
		} catch (Exception e) {
			DealLog.log(dictTable, "无翻译缓存记录");
		}

		//如果有缓存结果
		if (resultSet != null) {
			m = resultSet.get(0).getToMsg();
		} else {
			//取得翻译结果
			m = dict(m);
		}
		return m;
	}
}
