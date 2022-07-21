/*
 * FileName: FoundHtmlTranslate
 * Author:   ireedtop
 */
package top.ireed.found.html;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import top.ireed.deal.DealLog;
import top.ireed.found.dict.FoundDict;
import top.ireed.general.TopException;

import java.io.*;
import java.util.List;


/**
 * 功能简述:
 * 〈解析html文件,将文件中的英文翻译为中文
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/20 15:57
 * reedsource@189.cn
 */
public class FoundHtmlTranslate {


	private final String oFile;
	private final String backupFile;
	private static FoundDict foundDict;

	public FoundHtmlTranslate(String oFile, String backupFile, FoundDict foundDict) throws TopException {
		if (StrUtil.isEmpty(oFile) || StrUtil.isEmpty(backupFile)) {
			throw new TopException("需要翻译的路径和翻译完成保存路径不能为空", oFile, backupFile);
		}
		if (foundDict == null) {
			throw new TopException("foundDict翻译对象不能为空");
		}
		this.oFile = oFile;
		this.backupFile = backupFile;
		FoundHtmlTranslate.foundDict = foundDict;
	}

	/**
	 *
	 */
	public void dictHtml() {
		//获取开始时间
		long startTime = System.currentTimeMillis();
		System.out.println("===================html英文翻译中文工具启动===================");
		System.out.println("===翻译后的文件目录只包含html文件,请手动复制入依赖的相关文件====");
		System.out.println("=============================================================");
		System.out.println("===文件目录" + oFile);
		System.out.println("===文件翻译结果目录" + backupFile);
		if (oFile.isEmpty()) {
			System.out.println("html文件所在路径" + oFile + "异常,清检查");
			return;
		}
		if (backupFile.isEmpty()) {
			System.out.println("html文件翻译后指定路径" + backupFile + "异常,清检查");
			return;
		}

		File baFile = new File(backupFile);

		//文件夹不存在 创建文件夹
		if (!baFile.exists()) {
			baFile.mkdirs();
		}


		//源文件路径
		File file = new File(oFile);

		//文件过滤器
		FileFilter fileFilter = f -> f.getName().endsWith(".html") || f.getName().endsWith(".htm");

		//文件不为空
		if (!FileUtil.isEmpty(file)) {
			//1 读取html文件夹 遍历文件 递归0层  并初步剔除其他类型文件a
			List<File> list = FileUtil.loopFiles(file, -1, fileFilter);

			System.out.println("html文件总数量 " + list.size());
			int i = 1;

			DealLog.log("开始复制非html文件");
			//复制其它非过滤文件
			List<File> list1 = FileUtil.loopFiles(file);
			int sum = 0;
			for (File f : list1) {
				//跳过上面已经翻译的文件
				if (FileUtil.getSuffix(f).equals("html") || FileUtil.getSuffix(f).equals("htm")) {
					continue;
				}
				sum++;
				//复制文件 目录不存在将创建
				FileUtil.copy(f, new File(backupFile + f.toString().substring(oFile.length())), true);
			}

			DealLog.log("复制非html文件完成 共", sum, "个");

			for (File f : list) {
				long start0Time = System.currentTimeMillis();
				System.out.print(i + "\t开始翻译文件 " + f.getName() + "   ====> ");
				//获取file中的html文件内容
				String fileString = readToString(f);
				//获取目录查下全部文件,并翻译

				// 翻译前处理
				String fileToString = allFront(fileString);
				//翻译处理
//				fileToString = all(fileToString);
				//翻译后处理
				fileToString = allLater(fileToString);

				//更新的文件名称 将文件保存到翻译目录  保留原有目录结构
				File s = new File(backupFile + f.toString().substring(oFile.length()));
				//写入到文件
				writeToString(s, fileToString);

				//获取结束时间
				long endTime = System.currentTimeMillis();
				//输出程序运行时间
				System.out.println("\t翻译完成 耗时：" + (endTime - start0Time) + "ms");
				i++;
			}


		}
		//获取结束时间
		long endTime = System.currentTimeMillis();
		//输出程序运行时间
		System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
		System.out.println("===================文档处理翻译完成,谢谢使用==================");
	}


	/**
	 * 翻译处理前的html文本处理  将影响处理流程的特殊文本替换处理
	 *
	 * @param src 源文本
	 * @return 处理后的源文本
	 */
	private static String allFront(String src) {
		src = StrUtil.replaceIgnoreCase(src, "<!--", "$**$");
		src = StrUtil.replaceIgnoreCase(src, "-->", "$*$");
		return src;
	}

	/**
	 * 翻译处理前的html文本处理  将影响处理流程的特殊文本替换处理
	 *
	 * @param src 处理后的源文本
	 * @return 反处理后的源文本
	 */
	private static String allLater(String src) {
		src = StrUtil.replaceIgnoreCase(src, "$*$", "-->");
		src = StrUtil.replaceIgnoreCase(src, "$**$", "<!--");
		return src;
	}


	/**
	 * 1.0
	 * 思路
	 * 以查询><位置为核心   按顺序逐个匹配  替换
	 * <p>
	 * 获取全部文本 本处获取全部>  < 中的内容 组 翻译并返回翻译后的文本
	 *
	 * @param src html读取的文本
	 */
	private static String all(String src) {


		//<z左括号初始位置
		int z;
		//>右括号初始位置
		int y;
		//左括号与右括号中间的字符数 即z-y-1
		int c;

		for (int i = 1; ; i++) {
			//<z左括号初始位置
			z = StrUtil.ordinalIndexOf(src, "<", i + 1);
			//>右括号初始位置
			y = StrUtil.ordinalIndexOf(src, ">", i);

			//z括号找不到 表示遍历完成
			if (z == -1) {
				break;
			}
			//html异常
			if (z < y) {
				System.out.println("异常  " + z + "  " + y + " " + src.substring(z, y));
				break;
			}

			//如果<后的文本为</script>  不需要操作
			if (z + 9 < src.length() && "</script>".equals(src.substring(z, z + 9))) {
				break;
			}

			//如果 >右括号+1位置等于左括号位置,表示中间无文本 不需要操作
			if (y + 1 != z) {
				//取><中间的文本  注意 substring左包含右不包含
				String m = src.substring(y + 1, z);
				//1.处理前数据统计================================================
				//取中间的字符数
				c = z - y - 1;
				//1.1 统计源文本中换行符次数
				int sum_r = getCount("\r\n", m);

				//2.翻译前文本处理================================================
				//去除\r\n
				m = StrUtil.replaceIgnoreCase(m, "\r\n", "");
				//去除空格
				m = m.replaceAll(" +", " ");
				//去除 '
				m = StrUtil.replaceIgnoreCase(m, "'", ".");
				//去除首尾空格
				m = m.trim();

				//3,获取翻译结果=================================================

				//第一时间记录翻译的标准结果
				//需要翻译返回翻译结果 否则返回原文本
				String k = exclude(m) ? foundDict.dict(m) : m;
				//翻译结果处理文本
				StringBuilder n = new StringBuilder(k);

				//4.翻译后文本处理===============================================
				//4.回车 换行符恢复补全
				for (int j = 0; j < sum_r; j++) {
					n.append("\r");
				}

				//翻译后的文本长度
				int num = n.length();
				//翻译结果一般比原数据短 少的用空格补齐  减去 翻译前文本长度-翻译后文本长度  的差数  避免过多的空格添加
				for (int j = 0; j < (c - num - (m.length() - k.length())); j++) {
					n.append(" ");
				}

				//处理结果替换原文本  左包 右不包
				StringBuilder sb = new StringBuilder(src);
				sb.replace(y + 1, z, n.toString());
				src = sb.toString();
			}
		}
		return src;
	}


	/**
	 * 排除不需要翻译的内容
	 *
	 * @param m 字符串
	 * @return 是否需要翻译
	 */
	private static boolean exclude(String m) {
		//去除全部空格
		m = StrUtil.replaceIgnoreCase(m, " ", "");

		//===================长度判断=================
		//如果字符串长度为1
		if (m.length() == 1) {
			//单个字母
			return !m.matches("[^a-zA-Z]+");
			//===================内容判断=================
		} else if ("".equals(m)) {
			return false;
			//是否为数值
		} else if (m.matches("\\d+")) {
			return false;
			//===================内容存在判断 需要翻译的文本,存在以下内容 一律不翻译
		} else if (StrUtil.lastIndexOfIgnoreCase(m, "$") != -1) {
			return false;
		} else return StrUtil.lastIndexOfIgnoreCase(m, "div") == -1;
	}

	/**
	 * 读取文件文本
	 *
	 * @param file 路径
	 * @return 文件文本
	 */
	private static String readToString(File file) {
		String encoding = "UTF-8";
		long filelength = file.length();
		byte[] filecontent = new byte[(int) filelength];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return new String(filecontent, encoding);
		} catch (UnsupportedEncodingException e) {
			System.err.println("操作系统不支持" + encoding);
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * 写文件
	 *
	 * @param file 路径
	 * @param m    字符串
	 */
	private static void writeToString(File file, String m) {
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(file);
			outputStream.write(m.getBytes());
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回字符串在另一个字符串中出现的次数
	 *
	 * @param target 需要查询的字符串
	 * @param str    被查询字符串
	 * @return int indexOf(String str):返回的是str在字符串中第一次出现的位置
	 */
	public static int getCount(String target, String str) {
		// 定义计数器
		int count = 0;
		// 定义角标
		int index;

		while ((index = str.indexOf(target)) != -1) {
			// sp("str"+str);
			// 截取未被查找到目标字符串的子串
			str = str.substring(index + target.length());
			count++;
		}

		return count;
	}
}


