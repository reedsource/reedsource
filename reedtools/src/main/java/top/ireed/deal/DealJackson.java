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
	static final ObjectMapper mapper = new ObjectMapper();

	private DealJackson() {

	}

	/**
	 * 对象转json
	 *
	 * @param objects objects
	 * @return json
	 * @throws TopException 异常
	 */
	public static String getJson(Object objects) throws TopException {
		try {
			return mapper.writeValueAsString(objects);
		} catch (JsonProcessingException e) {
			throw new TopException("对象转json异常", e);
		}
	}

	/**
	 * json转object
	 *
	 * @param str json
	 * @param t   class
	 * @param <T> 泛型class
	 * @return object
	 * @throws TopException 异常
	 */
	public static <T> T toObject(String str, Class<T> t) throws TopException {
		try {
			return mapper.readValue(str, t);
		} catch (JsonProcessingException e) {
			throw new TopException("json转object异常", e);
		}
	}


	/**
	 * json转Map
	 *
	 * @param str json字符串
	 * @return json
	 * @throws TopException 异常
	 */
	@SuppressWarnings("unchecked")
	public static Map<Object, Object> toMap(String str) throws TopException {
		return toObject(str, Map.class);
	}


	/**
	 * json转List
	 *
	 * @param str json
	 * @param t   class
	 * @param <T> 泛型 class
	 * @return List
	 * @throws TopException 异常
	 */
	public static <T> List<T> toList(String str, Class<T> t) throws TopException {
		//返回 List<T>
		List<T> p = new ArrayList<>();
		//list默认值为linkHashMap  有序的实体集合 实际就是实体
		//本处调用方法将字符串转化为List<linkHashMap>
		List<T> persons;
		try {
			persons = mapper.readValue(str, new TypeReference<>() {
			});
		} catch (JsonProcessingException e) {
			throw new TopException("json转List异常", e);
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
