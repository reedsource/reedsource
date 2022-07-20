/*
 * FileName: jacksonArray
 * Author:   reedsource
 */
package jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import top.ireed.deal.DealLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能简述:
 * 〈复杂的转换〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/19 16:58
 * reedsource@189.cn
 */
public class jacksonComplex {
	public static void main(String[] args) {
		complexToJson();
		jsonToComplex();
	}

	private static void complexToJson() {
		List<Person> list = new ArrayList<>();
		Person p1 = new Person("张三", 22, "北京");
		Person p2 = new Person("李四", 24, "北京");
		Person p3 = new Person("王五", 25, "北京");
		list.add(p1);
		list.add(p2);
		list.add(p3);
		Map<String, Object> map = new HashMap<>(16);
		map.put("data", list);
		map.put("message", "传输数据");
		ObjectMapper mapper = new ObjectMapper();
		String string = null;
		try {
			string = mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		DealLog.log(string);

	}

	private static void jsonToComplex() {
		String str = "{\"message\":\"传输数据\",\"data\":[{\"name\":\"张三\",\"age\":23,\"addr\":\"北京\"},{\"name\":\"李四\",\"age\":24,\"addr\":\"北京\"},{\"name\":\"王五\",\"age\":25,\"addr\":\"北京\"}]}";
		ObjectMapper mapper = new ObjectMapper();
		Map readValue = null;
		try {
			readValue = mapper.readValue(str, Map.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		List list = (List) (readValue != null ? readValue.get("data") : null);
		DealLog.log(list);
	}
}
