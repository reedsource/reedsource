/*
 * FileName: Optional使用原因
 * Author:   reedsource
 */
package k03对象.工具类.Optional处理空指针;

import org.junit.Test;

/**
 * 功能简述:
 * 〈Optional类引入的原因〉
 * 解决 空指针异常（NullPointerException
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class Optional使用原因 {

	@Test
	public void Optional使用原因_Test() {
	/* 伪代码
	当我们获取一个实体的子属性时,可能需要不断的判空处理 很容易就变得冗长，难以维护

		if (user != null) {
		    Address address = user.getAddress();
		    if (address != null) {
		        Country country = address.getCountry();
		        if (country != null) {
		            String isocode = country.getIsocode();
		            if (isocode != null) {
		                isocode = isocode.toUpperCase();
		            }
		        }
		    }
		}
	*/

	/*  Optional 的方法 处理
	@Test
	public void whenChaining_thenOk() {
		User user = new User("anna@gmail.com", "1234");

		String result = Optional.ofNullable(user)
				.flatMap(u -> u.getAddress())
				.flatMap(a -> a.getCountry())
				.map(c -> c.getIsocode())
				.orElse("default");

		assertEquals(result, "default");
	}*/

	/*	进一步缩减
	String result = Optional.ofNullable(user)
			.flatMap(User::getAddress)
			.flatMap(Address::getCountry)
			.map(Country::getIsocode)
			.orElse("default");
	*/
	}

}
