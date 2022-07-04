/*
 * FileName: DictReturn
 * Author:   ireedtop
 */
package top.ireed.found.dict.entity;

import java.util.Arrays;

/**
 * 功能简述:
 * 〈百度词典返回实体类〉
 * {"from":"en","to":"zh","trans_result":[{"src":"Height 600m","dst":"\u9ad8600\u7c73"}]}
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/3 15:57
 * reedsource@189.cn
 */
public class DictReturn {
	/**
	 * 源文件格式
	 */
	private String from;
	/**
	 * 目标文件格式
	 */
	private String to;
	/**
	 * 翻译文件
	 */
	private TransResult[] trans_result;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public TransResult[] getTrans_result() {
		return trans_result;
	}

	public void setTrans_result(TransResult[] trans_result) {
		this.trans_result = trans_result;
	}

	@Override
	public String toString() {
		return "DictReturn{" +
				"from='" + from + '\'' +
				", to='" + to + '\'' +
				", trans_result=" + Arrays.toString(trans_result) +
				'}';
	}
}

