/*
 * FileName: DealObjectTest
 * Author:   reedsource
 */
package top.ireed.deal;

import org.junit.Assert;
import org.junit.Test;
import top.ireed.model.Model;


/**
 * 功能简述:
 * 〈DealObject测试类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2020/7/31 22:17
 * reedsource@189.cn
 */
public class DealObjectTest {

	@Test
	public void test() {
		Model model = new Model("10000","key的值","value的值","","super的值");
		//通过本方法可以获取继承的父类的数据
		Assert.assertEquals("super的值", DealObject.getSuperMember(model, "suS"));
		//测试getMember 只能获取类本身的参数数据
		Assert.assertEquals("key的值", DealObject.getMember(model, "key"));
		//测试isExistFieldName
		Assert.assertEquals(true, DealObject.isExistFieldName(model, "key"));
	}
}