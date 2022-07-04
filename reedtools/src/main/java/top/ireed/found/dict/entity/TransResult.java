package top.ireed.found.dict.entity;

/**
 * 功能简述:
 * 〈}〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/3 15:57
 * reedsource@189.cn
 */
public class TransResult {
	/**
	 * 翻译源文件
	 */
	private String src;
	/**
	 * 翻译结果文件
	 */
	private String dst;

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getDst() {
		return dst;
	}

	public void setDst(String dst) {
		this.dst = dst;
	}

	@Override
	public String toString() {
		return "TransResult{" +
				"src='" + src + '\'' +
				", dst='" + dst + '\'' +
				'}';
	}
}
