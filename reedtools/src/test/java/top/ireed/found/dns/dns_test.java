/*
 * Author:   reedsource
 */
package top.ireed.found.dns;

import com.aliyuncs.alidns.model.v20150109.DescribeSubDomainRecordsResponse;
import org.junit.Test;
import top.ireed.deal.DealIo;
import top.ireed.deal.DealJackson;
import top.ireed.deal.DealLog;
import top.ireed.found.dns.aliyun.v20150109.FoundAliDns;
import top.ireed.general.TopException;

import java.io.File;
import java.util.Map;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/25 8:43
 * reedsource@189.cn
 */
public class dns_test {


	@Test
	public void UpdateDomainRecord_test() throws TopException {
		//配置文件位置可以自定义修改
		Map<Object, Object> map = DealJackson.toMap(DealIo.getFileIo(new File("D:\\reed\\reed.json")));

		// dns对象
		FoundAliDns foundAliDns = new FoundAliDns(map.get("REGION_ID").toString(), map.get("ACCESS_KEY_ID").toString(), map.get("ACCESS_KEY_SECRET").toString());


		// 获取设置的dns
		DescribeSubDomainRecordsResponse.Record record = foundAliDns.getAliIp(map.get("SubDomain").toString());
		DealLog.log("设置的dns为", record.getValue(), "recordId解析记录的ID为", record.getRecordId());


		//更新解析记录的对应的ID的解析ip
		foundAliDns.updateIp("127.0.0.1", "dns", record.getRecordId());


	}
}
