/*
 * FileName: DealJacksonTest
 * Author:   reedsource
 */
package top.ireed.deal;

import org.junit.Assert;
import org.junit.Test;
import top.ireed.general.TopException;
import top.ireed.model.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能简述:
 * 〈DealJackson测试类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2020/7/31 16:54
 * reedsource@189.cn
 */
public class DealJacksonTest {

	private String lISTS = "[{\"suS\":null,\"id\":\"1\",\"key\":\"11\",\"value\":\"111\"},{\"suS\":null,\"id\":\"2\",\"key\":\"22\",\"value\":\"222\"}]";
	private String MAPS = "{\"1\":{\"suS\":null,\"id\":null,\"key\":\"1\",\"value\":\"11\",\"date\":\"111\"},\"2\":{\"suS\":null,\"id\":null,\"key\":\"2\",\"value\":\"22\",\"date\":\"222\"}}";

	/**
	 * 对象转json
	 *
	 * @throws TopException 异常
	 */
	@Test
	public void getJackson() throws TopException {
		//list转json
		List<Model> list = new ArrayList<>(16);
		list.add(new Model("1", "11", "111"));
		list.add(new Model("2", "22", "222"));

		//map转json
		Map<String, Model> modelMap = new HashMap<>(16);
		modelMap.put("1", new Model("1", "11", "111"));
		modelMap.put("2", new Model("2", "22", "222"));
		Assert.assertEquals(MAPS, DealJackson.getJson(modelMap));
	}

	/**
	 * json转list
	 *
	 * @throws TopException 异常
	 */
	@Test
	public void toList() throws TopException {
		List<Model> list = DealJackson.toList(lISTS, Model.class);
		for (Model model : list) {
			DealLog.log(model);
		}
		Assert.assertTrue(true);
	}

	/**
	 * json转map
	 *
	 * @throws TopException 异常
	 */
	@Test
	public void toMap() throws TopException {
		Map modelMap = DealJackson.toMap(MAPS);
		//遍历map
		for (Object stringModelEntry : modelMap.entrySet()) {
			DealLog.log(stringModelEntry);
		}
		Assert.assertTrue(true);
	}
}
