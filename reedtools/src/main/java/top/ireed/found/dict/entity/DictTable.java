/*
 * Author:   reedsource
 */
package top.ireed.found.dict.entity;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/3 15:57
 * reedsource@189.cn
 */
public class DictTable {

	private String id;

	/**
	 * 源文本
	 */
	private String msg;

	/**
	 * 翻译后文本
	 */
	private String toMsg;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getToMsg() {
		return toMsg;
	}

	public void setToMsg(String toMsg) {
		this.toMsg = toMsg;
	}
}
