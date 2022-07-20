/*
 * FileName: jacksonObject
 * Author:   reedsource
 */
package jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/19 16:58
 * reedsource@189.cn
 */
public class jacksonObject {
	public static void main(String[] args) {
		objectToJson();
		jsonToObject();
	}

	private static void objectToJson() {
		Person p = new Person("张三", 23, "北京");
		ObjectMapper mapper = new ObjectMapper();
		String string = null;
		try {
			string = mapper.writeValueAsString(p);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		DealLog.log(string);
	}

	private static void jsonToObject() {
		String str = "{\"name\":\"张三\",\"age\":23,\"site\":\"北京\"}";
		ObjectMapper mapper = new ObjectMapper();
		Person readValue = null;
		try {
			readValue = mapper.readValue(str, Person.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		DealLog.log(readValue);
	}

}
