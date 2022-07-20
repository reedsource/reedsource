/*
 * FileName: jacksonArray
 * Author:   reedsource
 */
package jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import top.ireed.deal.DealLog;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/19 16:58
 * reedsource@189.cn
 */
public class jacksonList {
	public static void main(String[] args) {
		listToJson();
		jsonToList();
	}

	private static void listToJson() {
		List<Person> list = new ArrayList<>();
		Person p1 = new Person("张三", 23, "北京");
		Person p2 = new Person("李四", 24, "北京");
		Person p3 = new Person("王五", 25, "北京");
		list.add(p1);
		list.add(p2);
		list.add(p3);
		ObjectMapper mapper = new ObjectMapper();
		String string = null;
		try {
			string = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		DealLog.log(string);
	}

	private static void jsonToList() {
		String str = "[{\"name\":\"张三\",\"age\":23,\"site\":\"北京\"},{\"name\":\"李四\",\"age\":24,\"site\":\"北京\"},{\"name\":\"王五\",\"age\":25,\"site\":\"北京\"}]";
		ObjectMapper mapper = new ObjectMapper();
		List<Person> persons = null;
		try {
			persons = mapper.readValue(str, new TypeReference<List<Person>>() {
			});
			for (Person person : persons) {
				DealLog.log(person);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
