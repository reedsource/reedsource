/*
 * Author:   reedsource
 */
package top.ireed.found.dns.aliyun.v20150109;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.DescribeSubDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeSubDomainRecordsResponse;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordRequest;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import top.ireed.deal.DealLog;
import top.ireed.general.TopException;

/**
 * 功能简述:
 * 〈阿里云dns工具  V20150109
 * 阿里云 dnsapi地址https://help.aliyun.com/document_detail/124923.html
 * 〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/25 8:49
 * reedsource@189.cn
 */
public class FoundAliDns {

	static IAcsClient client;


	public FoundAliDns(String REGION_ID, String ACCESS_KEY_ID, String ACCESS_KEY_SECRET) {
		//阿里云数据组装
		DefaultProfile profile = DefaultProfile.getProfile(REGION_ID, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
		//创建连接对象
		client = new DefaultAcsClient(profile);
	}

	/**
	 * 更新阿里云解析ip
	 *
	 * @param iP 需要更新的ip
	 */
	public void updateIp(String iP, String rr, String recordId) throws TopException {

		//阿里云更新子域记录请求组装
		UpdateDomainRecordRequest request = new UpdateDomainRecordRequest();
		//记录值 IP
		request.setValue(iP);
		// 默认 A
		request.setType("A");
		// 根据设置 如果要解析@.exmaple.com，主机记录要填写”@”，而不是空。
		request.setRR(rr);
		//解析记录的ID
		request.setRecordId(recordId);

		try {
			//阿里云更新子域记录返回数据
			UpdateDomainRecordResponse response = client.getAcsResponse(request);
			//打印结果
			DealLog.log(new Gson().toJson(response));
		} catch (ClientException e) {
			throw new TopException("更新阿里云解析ip异常", e);
		}
	}

	/**
	 * 获取当前阿里云配置
	 *
	 * @return ip
	 */
	public DescribeSubDomainRecordsResponse.Record getAliIp(String yu) throws TopException {
		//阿里云子域记录请求组装
		DescribeSubDomainRecordsRequest request = new DescribeSubDomainRecordsRequest();
		//子域名
		request.setSubDomain(yu);
		try {
			//阿里云子域记录返回数据获取
			DescribeSubDomainRecordsResponse response = client.getAcsResponse(request);
			//返回数据的第一个
			return response.getDomainRecords().get(0);
		} catch (ClientException e) {
			throw new TopException("获取当前阿里云配置的ip异常", e);
		}
	}


}
