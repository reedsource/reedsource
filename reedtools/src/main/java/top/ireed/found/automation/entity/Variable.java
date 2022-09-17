/*
 * Author:   reedsource
 */
package top.ireed.found.automation.entity;

/**
 * 功能简述:
 * 〈变量〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/9/16 16:15
 * reedsource@189.cn
 */
public class Variable {

	/**
	 * 变量名称
	 */
	private String key;
	/**
	 * 变量值
	 */
	private Object value;
	/**
	 * 变量描述
	 */
	private String msg;

	public Variable() {
	}

	public Variable(String key, Object value, String msg) {
		this.key = key;
		this.value = value;
		this.msg = msg;
	}


	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "\t变量名称 : " + key + "\r\n" + "\t变量描述 : " + msg + "\r\n" + "\t变量值   : " + value + "\r\n";
	}
}
