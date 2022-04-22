/*
 * FileName: DealGetSet
 * Author:   reedsource
 */
package top.ireed.deal;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

import static top.ireed.general.TopConstant.F_F;
import static top.ireed.general.TopConstant.F_X_Z;

/**
 * 功能简述:
 * 〈获取实体类getSet方法打印〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2019/8/7 15:33
 * reedsource@189.cn
 */
public class DealGetSet {
	private DealGetSet() {
	}

	/**
	 * @param obj  实体类
	 * @param name 需要的实体类名称
	 */
	public static void getMethod(Object obj, String name) {
		//获得实体类名
		Class<?> clazz = obj.getClass();
		//获得属性
		Field[] fields = obj.getClass().getDeclaredFields();
		//获得Object对象中的所有方法
		String txt;
		//set方法
		for (Field field : fields) {
			try {
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
				txt = String.valueOf(pd.getReadMethod());
				String str=name + txt.substring(DealString.inverted(txt, '.', 1)) + F_F;
				DealLog.log(str);
			} catch (IntrospectionException e) {
				DealLog.log("获取set全部方法异常");
			}
		}
		//get方法
		for (Field field : fields) {
			try {
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
				txt = String.valueOf(pd.getWriteMethod());
				txt = txt.substring(0, txt.indexOf(F_X_Z) + 1);
				String str=name + txt.substring(DealString.inverted(txt, '.', 1)) + ");";
				DealLog.log(str);
			} catch (IntrospectionException e) {
				DealLog.log("获取get全部方法异常");
			}
		}
	}
}
