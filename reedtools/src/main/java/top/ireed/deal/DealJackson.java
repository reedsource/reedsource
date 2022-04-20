/*
 * FileName: DealJackson
 * Author:   reedsource
 */
package top.ireed.deal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import top.ireed.general.TopException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 功能简述:
 * 〈jackson转换工具类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2020/7/27 15:49
 * reedsource@189.cn
 */
public class DealJackson {
	static ObjectMapper mapper = new ObjectMapper();

	private DealJackson() {

	}

	/**
	 * 对象转json
	 *
	 * @param objects
	 * @return json
	 * @throws JsonProcessingException 异常
	 */
	public static String getJson(Object objects) throws TopException {
		try {
			return mapper.writeValueAsString(objects);
		} catch (JsonProcessingException e) {
			throw new TopException("对象转json异常",e);
		}
	}

	/**
	 *  json转object
	 * @param str json
	 * @param t class
	 * @param <T> 泛型class
	 * @return object
	 * @throws JsonProcessingException 异常
	 */
	public static <T> T toObject(String str, Class<T> t) throws TopException {
		try {
			return mapper.readValue(str, t);
		} catch (JsonProcessingException e) {
			throw new TopException("json转object异常",e);
		}
	}


	/**
	 * json转Map
	 *
	 * @param str
	 * @return json
	 * @throws JsonProcessingException 异常
	 */
	public static Map toMap(String str) throws TopException {
		try {
			return mapper.readValue(str, Map.class);
		} catch (JsonProcessingException e) {
			throw new TopException("json转Map异常",e);
		}
	}


	/**
	 *  json转List
	 * @param str json
	 * @param t class
	 * @param <T> 泛型 class
	 * @return
	 * @throws JsonProcessingException 异常
	 */
	public static <T> List<T> toList(String str, Class<T> t) throws TopException {
		//返回 List<T>
		List<T> p = new ArrayList<>();
		//list默认值为linkHashMap  有序的实体集合 实际就是实体
		//本处调用方法将字符串转化为List<linkHashMap>
		List<T> persons = null;
		try {
			persons = mapper.readValue(str, new TypeReference<List<T>>() {
			});
		} catch (JsonProcessingException e) {
			throw new TopException("json转List异常",e);
		}
		//将List<linkHashMap> 转换为 List<T>
		for (T person : persons) {
			//将对象转json 再转为实体类
			T ts = toObject(getJson(person), t);
			if (ts != null) {
				p.add(ts);
			}
		}
		return p;
	}
}
