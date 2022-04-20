/*
 * FileName: DealFile
 * Author:   reedsource
 */
package top.ireed.deal;

import java.io.File;

/**
 * 功能简述:
 * 〈路径工具类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2020/9/10 19:58
 * reedsource@189.cn
 */
public class DealFile {
	private DealFile() {

	}

	/**
	 *  根据目录字符串创建目录
	 *
	 * @param fileString 目录字符串
	 * @return 创建目录结果
	 */
	public static Boolean getFile(String fileString) {
		try {
			File baFile = new File(fileString);
			//目录不存在创建
			if (!baFile.exists()) {
				baFile.mkdirs();
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 *  根据目录对象创建目录
	 *
	 * @param file 目录对象
	 * @return 创建目录结果
	 */
	public static Boolean getFile(File file) {
		try {
			//目录不存在创建
			if (!file.exists()) {
				file.mkdirs();
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
