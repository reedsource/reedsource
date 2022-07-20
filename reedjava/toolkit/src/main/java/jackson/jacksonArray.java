/*
 * FileName: jacksonArray
 * Author:   reedsource
 */
package jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
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
public class jacksonArray {
	public static void main(String[] args) {
		arrayToJson();
		jsonToArray();
	}

	/**
	 * 数组转json
	 */
	private static void arrayToJson() {
		Person p1 = new Person("张三", 23, "北京");
		Person p2 = new Person("李四", 24, "北京");
		Person p3 = new Person("王五", 25, "北京");
		Person[] persons = {p1, p2, p3};
		ObjectMapper mapper = new ObjectMapper();
		String string = null;
		try {
			string = mapper.writeValueAsString(persons);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		DealLog.log(string);
	}

	/**
	 * 字符串转数组
	 */
	private static void jsonToArray() {
		String str = "[{\"name\":\"张三\",\"age\":23,\"site\":\"北京\"},{\"name\":\"李四\",\"age\":24,\"site\":\"北京\"},{\"name\":\"王五\",\"age\":25,\"site\":\"北京\"}]";
		ObjectMapper mapper = new ObjectMapper();
		Person[] persons = new Person[0];
		try {
			persons = mapper.readValue(str, new TypeReference<Person[]>() {
			});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		for (Person person : persons) {
			DealLog.log(person);
		}
	}
}
