/*
 * FileName: TopException
 * Author:   reedsource
 */
package top.ireed.general;

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
	 * @param msg 异常信息
	 */
	public TopException(String msg) {
		super(msg);
	}

	/**
	 * 自定义抛出异常
	 *
	 * @param msg 异常信息
	 * @param o   拦截的异常信息
	 */
	public TopException(String msg, Object o) {
		super(msg + o.toString());
	}
}
