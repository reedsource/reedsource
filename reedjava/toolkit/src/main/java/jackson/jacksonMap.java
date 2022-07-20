/*
 * FileName: jacksonMap
 * Author:   reedsource
 */
package jackson;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import top.ireed.deal.DealLog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能简述:
 * 〈jacksonMap操作〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/19 16:58
 * reedsource@189.cn
 */
public class jacksonMap {
	public static void main(String[] args) {
		mapToJson();
		jsonToMap();
	}

	/**
	 * map转json
	 */
	private static void mapToJson() {
		Map<String, String> map = new HashMap<>(16);
		ObjectMapper objectMapper = new ObjectMapper();
		map.put("b", "b");
		map.put("a", "a");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put("occurTime", sdf.format(new Date()));
		DealLog.log("print map: " + map);
		String jsonStr = null;
		try {
			jsonStr = objectMapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		DealLog.log("print json: " + jsonStr);
	}


	/**
	 * json转map
	 */
	private static void jsonToMap() {
		Map<String, String> map = new HashMap<>(16);
		String str = "{\"a\":\"a\",\"b\":\"b\",\"occurTime\":\"2020-05-13 10:02:59\"}";
		ObjectMapper mapper = new ObjectMapper();
		try {
			map = mapper.readValue(str, Map.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		DealLog.log(map);
	}

}
