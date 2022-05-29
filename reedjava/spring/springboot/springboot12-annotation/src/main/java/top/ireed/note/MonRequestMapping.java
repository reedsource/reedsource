/*
 * FileName: MongoRequestMapping
 * Author:   reedsource
 */
package top.ireed.note;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能简述:
 * 〈springboot自定义注解〉
 *
 * ElementType的取值包含以下几种：
 * TYPE:类，接口或者枚举
 * FIELD:域，包含枚举常量
 * METHOD:方法
 * PARAMETER:参数
 * CONSTRUCTOR:构造方法
 * LOCAL_VARIABLE:局部变量
 * ANNOTATION_TYPE:注解类型
 * PACKAGE:包
 *
 * Retention
 * SOURCE 编译器处理完Annotation后不存储在class中
 * CLASS 编译器把Annotation存储在class中，这是默认值
 * RUNTIME 编译器把Annotation存储在class中，可以由虚拟机读取,反射需要
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping
public @interface MonRequestMapping {

	@AliasFor(annotation = RequestMapping.class)
	String[] value() default {};

}