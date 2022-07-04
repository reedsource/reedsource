/*
 * FileName: TopException
 * Author:   reedsource
 */
package top.ireed.general;

import top.ireed.deal.DealString;

/**
 * 功能简述:
 * 〈topException异常类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2020/9/10 15:59
 * reedsource@189.cn
 */
public class TopException extends Exception {

	/**
	 * 自定义抛出异常
	 *
	 * @param o 拦截的异常信息 多个异常将空格划分
	 */
	public TopException(Object... o) {
		super(DealString.objectToString(o));
	}
}
