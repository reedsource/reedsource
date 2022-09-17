/*
 * Author:   reedsource
 */
package j03集合;

import top.ireed.deal.DealJackson;
import top.ireed.deal.DealLog;
import top.ireed.general.TopException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/9/17 10:51
 * reedsource@189.cn
 */
public class object与List互转 {

	public static void main(String[] args) throws TopException {
		//知道传入值真实的原始类型的确为list的时候可用
		List<String> list = new ArrayList<>();
		list.add("111111");
		Object m = list;

		List<String> list1 = DealJackson.toList(DealJackson.getJson(m), String.class);
		DealLog.log(list1.get(0));

		//注意 Arrays.asList(m).get(0)取得的是一个ArrayList对象,并不是需要的字符串
		String a = Arrays.asList(m).get(0).toString();
		String a1 = ((ArrayList) Arrays.asList(m).get(0)).get(0).toString();
		String a2 = List.of(m).get(0).toString();
		//String某些情况下可为?
		String b = ((ArrayList<String>) m).get(0);

		DealLog.log(list);
		DealLog.log(a);
		DealLog.log(a2);
		DealLog.log(b);

	}


}
