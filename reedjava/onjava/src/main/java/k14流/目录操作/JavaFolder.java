/*
 * FileName: JavaFolder
 * Author:   reedsource
 */
package k14流.目录操作;

import top.ireed.deal.DealLog;

import java.io.*;
import java.util.Objects;

/**
 * 功能简述:
 * 〈java文件夹操作〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/9 22:57
 * reedsource@189.cn
 */
public class JavaFolder {
	public static void main(String[] args) {
		//删除文件夹
		delete(new File("d:/file"));
		delete(new File("d:/files"));


		//1) 参数为字符串,绝对路径, File对象既可以指向文件夹,也可以指向文件
		//创建单层文件夹
		File dir = new File("d:/file");
		//创建文件夹
		dir.mkdir();

		//创建多层文件夹 一般用mkdirs即可
		File dir2 = new File("d:/file/file2/file3");
		dir2.mkdirs();


		//2) 第一个参数指定父目录, 上一级目录, 第二个参数是当前文件夹/对象
		File sub1 = new File(dir, "sub1");
		sub1.mkdir();
		File sub2 = new File(dir, "sub1/sub2");
		sub2.mkdirs();


		//复制文件夹
		copyDir("d:/file", "d:/files");

		// 使用renameTo复制或重命名文件
		File file = new File("d:/files");
		//同一个目录是重命名
		file.renameTo(new File("d:/filess"));

		File file2 = new File("d:/filess");
		//不同的目录相当于文件移动  当目标文件存在时,移动将失败
		file2.renameTo(new File("d:/file/fileM"));

		//遍历文件夹
		listSubDirs("d:/file");


		try {
			FileOutputStream fos = new FileOutputStream("d:/file/def.txt", true);
			fos.write("文本文件".getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		DealLog.log("===以下显示目录下文件文件===");
		//读取文件夹下文件
		listFile("d:/file");

		DealLog.log("===以下只显示jpg或java文件===");
		//筛选读取文件夹下文件
		listFileSelect("d:/file");


		DealLog.log(File.pathSeparator);    //; 路径分隔符
		DealLog.log(File.separator);        //\ 文件分隔符, 在其他操作系统 中是/
	}

	/**
	 * 文件夹的复制
	 *
	 * @param dirname  文件夹
	 * @param destname 目标文件夹
	 */
	private static void copyDir(String dirname, String destname) {
		//遍历源文件夹的内容
		File srcdir = new File(dirname);
		File[] listFiles = srcdir.listFiles();
		if (listFiles != null) {
			for (File file : listFiles) {
				//如果是文件,就复制文件
				if (file.isFile()) {
					File destFile = new File(destname, file.getName());
					copyFile(file, destFile);
				} else {
					//如果是文件夹, 在目标位置先创建文件夹, 再复制子文件夹中的内容
					File subDir = new File(destname, file.getName());
					subDir.mkdirs();
					copyDir(file.getAbsolutePath(), subDir.getAbsolutePath());
				}
			}
		}
	}

	/**
	 * 文件复制
	 *
	 * @param file     文件源路径
	 * @param destFile 文件复制目标路径
	 */
	private static void copyFile(File file, File destFile) {

		try (
				FileInputStream fis = new FileInputStream(file);
				FileOutputStream fos = new FileOutputStream(destFile)
		) {
			byte[] bytes = new byte[1024];
			int len = fis.read(bytes);
			while (len != -1) {
				fos.write(bytes, 0, len);
				len = fis.read(bytes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 遍历文件夹
	 * <p>
	 * 显示指定目录的内容,包括子目录的内容
	 * dir显示文件列表
	 *
	 * @param dirname 文件夹路径
	 */
	private static void listSubDirs(String dirname) {
		File dir = new File(dirname);
		File[] listFiles = dir.listFiles();
		//遍历
		for (File file : Objects.requireNonNull(listFiles)) {
			DealLog.log(file.getAbsolutePath());
			//如果file是子目录, 把这个子目录的内容显示出来
			if (file.isDirectory()) {
				//递归调用
				listSubDirs(file.getAbsolutePath());
			}
		}
	}


	/**
	 * 遍历读取文件夹下目录或文件
	 */
	private static void listFile(String dirname) {
		File dir = new File(dirname);
		//列出所有的内容
		String[] sub = dir.list();
		for (String string : sub) {
			DealLog.log(string);
		}
	}

	/**
	 * 遍历读取文件夹下指定的格式的文件
	 */
	private static void listFileSelect(String dirname) {
		File dir = new File(dirname);
		//列出所有的内容
		String[] sub = dir.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				// 指定.java
				return name.endsWith(".java") || name.endsWith(".txt");
			}
		});
		for (String string : sub) {
			DealLog.log(string);
		}
	}

	/**
	 * 删除文件
	 *
	 * @param file 文件路径
	 */
	private static void delete(File file) {
		if (!file.exists()) {
			return;
		}
		if (file.isFile() || file.list() == null) {
			file.delete();
			DealLog.log("删除了" + file.getName());
		} else {
			File[] files = file.listFiles();
			for (File a : files) {
				delete(a);
			}
			file.delete();
			DealLog.log("删除了" + file.getName());
		}
	}
}
