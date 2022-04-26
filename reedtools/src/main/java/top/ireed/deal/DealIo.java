/*
 * FileName: DealIo
 * Author:   reedbook
 */
package top.ireed.deal;

import cn.hutool.core.io.IoUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 功能简述:
 * 〈IO工具类〉
 *
 * @author reedbook
 * @version 1.0.0
 * date 2022/4/26 23:21
 * reedbook@189.cn
 */
public class DealIo {
	/**
	 * 将字符串写入到文件中
	 * <p>
	 * 目录及文件不存在将尝试创建,注意文件存在时,内容将会被覆盖
	 *
	 * @param file 生成文件的路径
	 * @param str  文件的字符串
	 */
	public static void toFileIo(File file, String str) {
		try {
			//文件不存在  且  创建文件父级目录 创建文件
			if (!file.exists() && (!DealFile.newFile(file.getParentFile()) || !file.createNewFile())) {
				DealLog.log(file, "写入到文件创建失败");
				return;
			}

			// 构建FileOutputStream对象,文件不存在会自动新建--------------------------------
			FileOutputStream fop = new FileOutputStream(file);
			OutputStreamWriter outputStreamWriter = IoUtil.getUtf8Writer(fop);
			outputStreamWriter.write(str);
			// 关闭写入流,同时会把缓冲区内容写入文件,所以上面的注释掉
			outputStreamWriter.close();

			// 关闭输出流,释放系统资源
			fop.close();
			DealLog.log(file, "完成");
		} catch (IOException e) {
			DealLog.log(file, "写入到文件异常");
		}

	}
}
