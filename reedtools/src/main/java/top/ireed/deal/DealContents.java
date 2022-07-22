/*
 * FileName: DealContents
 * Author:   reedsource
 */
package top.ireed.deal;

import java.io.File;

/**
 * 功能简述:
 * 〈父级目录创建工具类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2020/9/10 19:58
 * reedsource@189.cn
 */
public class DealContents {
	private DealContents() {
	}

	/**
	 * 根据目录对象创建目录
	 *
	 * @param file          目录对象
	 * @param getParentFile 只创建父级目录   适用与包含文件名的file需要创建父级目录  如明确录入file不包含文件名称, 为 false
	 * @return 创建目录结果
	 */
	public static Boolean newContents(File file, boolean getParentFile) {
		try {
			if (getParentFile) {
				file = file.getParentFile();
			}
			//目录不存在创建
			if (!file.exists()) {
				return file.mkdirs();
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	/**
	 * 根据目录对象创建目录
	 *
	 * @param file          目录对象字符串
	 * @param getParentFile 只创建父级目录   适用与包含文件名的file需要创建父级目录  如明确录入file不包含文件名称, 为 false
	 * @return 创建目录结果
	 */
	public static Boolean newContents(String file, boolean getParentFile) {
		return newContents(new File(file), getParentFile);
	}


	/**
	 * 根据file属性,自动提取父级目录  创建父级目录
	 * <p>
	 * 根目录文件,默认为不需要创建 返回true
	 *
	 * @param file 目录对象
	 * @return 创建目录结果
	 */
	public static Boolean newContents(File file) {
		//根据.判断是否是文件
		int dian = file.getName().indexOf(".");

		//是文件
		if (dian != -1) {
			//路径\倒数出现的位置,判断是否包含父级目录
			int to = file.toString().indexOf("\\");
			//有父级目录
			if (to != -1) {
				//提取父级目录创建
				return newContents(file, true);
			}
			//无父级目录 认为无需创建
			return true;
		}

		//不是文件 直接按照目录创建
		return newContents(file, false);
	}


	/**
	 * 根据file字符串属性,自动提取父级目录  创建父级目录
	 *
	 * @param file 目录对象
	 * @return 创建目录结果
	 */
	public static Boolean newContents(String file) {
		return newContents(new File(file));
	}

}
