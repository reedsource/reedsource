/*
 * FileName: DealObject
 * Author:   reedsource
 */
package top.ireed.deal;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 功能简述:
 * 〈Object工具类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2019/10/6 11:43
 * reedsource@189.cn
 */
public class DealObject {
	private DealObject() {
	}

	/**
	 * 获取object的某个值 包括被继承的父类属性
	 *
	 * @param object object
	 * @param name   key
	 * @return value
	 */
	public static String getSuperMember(Object object, String name) {
		try {
			PropertyDescriptor prop = new PropertyDescriptor(name, object.getClass());
			Object str = prop.getReadMethod().invoke(object);
			return str.toString();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 获取object的某个值
	 *
	 * @param object object
	 * @param name   key
	 * @return value
	 */
	public static String getMember(Object object, String name) {
		Object o = ReflectUtil.getFieldValue(object, name);
		return o == null ? "" : o.toString();
	}

	/**
	 * 判断你一个类是否存在某个属性（字段）
	 *
	 * @param obj       类对象
	 * @param fieldName 字段
	 * @return true:存在，false:不存在
	 */
	public static Boolean isExistFieldName(Object obj, String fieldName) {
		//属性为空或不存在
		if (obj == null || StrUtil.isEmpty(fieldName)) {
			return false;
		}
		//获取这个类的所有属性
		Field[] fields = obj.getClass().getDeclaredFields();
		//循环遍历所有的fields
		for (Field field : fields) {
			//如果当前id名称等于需要查询的值
			if (field.getName().equals(fieldName)) {
				return true;
			}
		}
		return false;
	}


	/**
	 * Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
	 *
	 * @param map 键值map
	 * @param t   原始类
	 * @param <T> 类
	 * @return data
	 */
	public static <T> T transMapBean(Map<String, Object> map, T t) {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (map.containsKey(key)) {
					Object value = map.get(key);
					// 得到property对应的setter方法
					Method setter = property.getWriteMethod();
					setter.invoke(t, value);
				}
			}
		} catch (Exception e) {
			DealLog.log("更新bean数据异常" , t, map, e);
		}
		return t;
	}
}
