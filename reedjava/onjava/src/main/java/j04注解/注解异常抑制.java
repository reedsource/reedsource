/*
 * Author:   reedsource
 */
package j04注解;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import top.ireed.deal.DealLog;
import top.ireed.general.TopException;

import java.util.Map;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/1 16:39
 * reedsource@189.cn
 */
public class 注解异常抑制 {
	@Test
	public void 注解异常抑制_Test() throws TopException {
		DealLog.log(toMap(""));
	}

	/**
	 * @ SuppressWarnings该批注的作用是给编译器一条指令，告诉它对被批注的代码元素内部的某些警告保持静默
	 * @ SuppressWarnings(“unchecked”) 告诉编译器忽略 unchecked 警告信息，如使用List，ArrayList等未进行参数化产生的警告信息
	 * @ SuppressWarnings(“serial”) 如果编译器出现这样的警告信息：The serializable class WmailCalendar does not declare a static final serialVersionUID field of type long
	 * @ SuppressWarnings(“deprecation”) 如果使用了使用@Deprecated注释的方法，编译器将出现警告信息。使用这个注释将警告信息去掉。
	 * 可以两个注解同时使用
	 */
	@SuppressWarnings("unchecked")
	public static Map<Object, Object> toMap(String str) throws TopException {
		try {
			return new ObjectMapper().readValue(str, Map.class);
		} catch (JsonProcessingException e) {
			throw new TopException("json转Map异常", e);
		}
	}

}
