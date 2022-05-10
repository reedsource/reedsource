/*
 * FileName: JavaFile
 * Author:   reedsource
 */
package k14流.文件属性读取;

import org.junit.Test;
import top.ireed.deal.DealLog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 功能简述:
 * 〈File类属性解析〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/9 22:57
 * reedsource@189.cn
 */
public class JavaFile {

	@Test
	public void m2() {
		new File("d:/file").mkdir();
		String m = "d:/file/333.txt";

		try {
			FileOutputStream fos = new FileOutputStream(m, true);
			fos.write("文本文件".getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		new File(m).mkdir();
		File file = new File(m);

		//在指定位置创建文件，如果该文件已经存在，则不创建，返回false。
		//和输出流不一样，输出流对象一建立创建文件，而且文件已经存在，会覆盖原文件。
		try {
			DealLog.log("是否创建文件               " + file.createNewFile());
		} catch (IOException e) {
			e.printStackTrace();
		}


		DealLog.log("文件或文件夹是否存在       " + file.exists());
		DealLog.log("文件是否可执行             " + file.canExecute());
		DealLog.log("是否是一个标准文件         " + file.isFile());
		DealLog.log("文件是否是一个文件夹       " + file.isDirectory());
		DealLog.log("是否是一个隐藏文件         " + file.isHidden());
		DealLog.log("抽象路径名是否为绝对路径名 " + file.isAbsolute());

		DealLog.log("文件名称                   " + file.getName());
		DealLog.log("文件名称无后缀             " + file.getName().replaceAll("[.][^.]+$", ""));
		DealLog.log("文件后缀                   " + file.getName().substring(file.getName().lastIndexOf('.') + 1));
		DealLog.log("路径名字符串               " + file.getPath());
		DealLog.log("父级路径                   " + file.getParent());
		DealLog.log("路径组合               " + file.getParent() + "\\" + file.getName().replaceAll("[.][^.]+$", "") + "." + file.getName().substring(file.getName().lastIndexOf('.') + 1));
		DealLog.log("绝对路径                   " + file.getAbsolutePath());
		DealLog.log("上次修改时间               " + file.lastModified());
		DealLog.log("文件长度                   " + file.length());

		//删除失败返回false。
		file.delete();
		//在程序退出时删除指定文件。
		file.deleteOnExit();
	}
}
