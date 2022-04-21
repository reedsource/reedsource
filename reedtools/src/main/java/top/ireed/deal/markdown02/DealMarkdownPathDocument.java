/*
 * FileName: DealMarkdownPathDocument
 * Author:   reedsource
 */
package top.ireed.deal.markdown02;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import top.ireed.deal.DealLog;
import top.ireed.deal.parse.java.ParseJavaStructure;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Objects;

/**
 * 功能简述:
 * 〈处理核心  结合java文件解析 将java文件解析到树结构〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2021-03-23 13:55
 * reedsource@189.cn
 */
public class DealMarkdownPathDocument {

	//当前遍历的根目录
	static String pathStr;

	//当前屏蔽相对根目录的相对路径列表
	private static ArrayList<String> shieldingPathLists = new ArrayList<>(16);

	//要屏蔽的文件的后缀名称list 只判断后缀
	private static ArrayList<String> shieldingSuffixLists = new ArrayList<>(16);

	//要屏蔽的文件名称list   注意 会屏蔽全部符合名称的文件
	private static ArrayList<String> shieldingNameLists = new ArrayList<>(16);


	//保存全部目录下文件数据
	static ArrayList<MdEntityDocument> list = new ArrayList<>();
	/**
	 * 保留当前文件层级父级索引的索引值
	 * 最大支持层级
	 */
	private static int[] maxArray = new int[30];

	/**
	 * 获取需要遍历的根目录的层级  在后面的所有子目录减去层级
	 */
	static int fileSum;


	/**
	 * 将保存全部数据的list处理为符合规则的md格式文本
	 *
	 * @param file                目标根路径
	 * @param shieldingPathList   要屏蔽的路径list  以根目录向后相对路径
	 * @param shieldingSuffixList 要屏蔽的文件的后缀名称list 只判断后缀
	 * @param shieldingNameList   要屏蔽的文件名称list   注意 会屏蔽全部符合名称的文件
	 * @param son                 是迭代子文件夹生成文件夹? 忽略子文件
	 * @param toFile              生成的md文件的目录
	 * @return 返回String
	 */
	public static void toMd(File file, ArrayList<String> shieldingPathList, ArrayList<String> shieldingSuffixList, ArrayList<String> shieldingNameList, boolean son, File toFile) {
		shieldingPathLists = shieldingPathList;
		shieldingSuffixLists = shieldingSuffixList;
		shieldingNameLists = shieldingNameList;
		if (son) {
			//遍历path下的文件和目录，放在File数组中
			File[] fs = file.listFiles();
			if (fs != null) {
				for (File f : fs) {
					if (f.isDirectory()) {
						//屏蔽目录
						//是否屏蔽
						boolean isShielding = false;
						//去除屏蔽路径 如果是屏蔽
						for (String s : shieldingPathLists) {
							if ((file.getPath() + "\\" + s).equals(f.getPath())) {
								DealLog.log(f, "项目路径设置屏蔽");
								isShielding = true;
								break;
							}
						}
						if (!isShielding) {
							toMd(new File(toFile.getPath() + "\\" + f.getName() + ".md"), core(f, shieldingPathList, shieldingSuffixList, shieldingNameList));
							//清空全局数据
							list.clear();
						}
					}
				}
			}

		} else {
			toMd(new File(toFile.getPath() + "\\" + file.getName() + ".md"), core(file, shieldingPathList, shieldingSuffixList, shieldingNameList));
		}
	}

	/**
	 * 将字符串写入到文件中
	 *
	 * @param file 生成文件的路径
	 * @param str  文件的字符串
	 */
	public static void toMd(File file, String str) {
		try {
			// 先得到文件的上级目录，并创建上级目录，在创建文件
			if (!file.exists()) {
				file.getParentFile().mkdir();
				boolean newFile = file.createNewFile();
				if (!newFile) {
					DealLog.log(file, "写入到文件创建失败");
					return;
				}
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


	/**
	 * 将保存全部数据的list处理为符合规则的md格式文本
	 *
	 * @param file                目标路径
	 * @param shieldingPathList   要屏蔽的路径list  以根目录向后相对路径
	 * @param shieldingSuffixList 要屏蔽的文件的后缀名称list 只判断后缀
	 * @param shieldingNameList   要屏蔽的文件名称list   注意 会屏蔽全部符合名称的文件
	 * @return 返回String
	 */
	private static String core(File file, ArrayList<String> shieldingPathList, ArrayList<String> shieldingSuffixList, ArrayList<String> shieldingNameList) {

		//获取需要遍历的根目录的层级  在后面的所有子目录减去层级
		fileSum = file.getPath().split("\\\\").length;

		//记录根目录
		pathStr = file.getPath() + "\\";

		//迭代遍历获取目录下全部文件,放入全局list
		tree(file);

		//当前行所属的上级级别
		int is = 0;

		//1 先将文件树按照md的格式生成符合规则的 标题+内容  结构
		//2 但是此时内容是换行符格式的文本 是便于组合为md文件的 如果进一步解析文件 将会是阻碍
		//思路
		//方案一 在写入到行文本时 对数据进行可解析辨别处理


		//md级别 将文件树按照md格式排序
		for (int i = 0; i < list.size(); i++) {
			//如果当前是目录
			//如果层级大于7 表示必定为无序列表
			//如果解析文本不为空
			if (list.get(i).isDirectory() || list.get(i).getLevel(fileSum) > 7 || StrUtil.isNotBlank(list.get(i).getParse())) {
				list.get(i).setTitle(mdPrefix(list.get(i).getLevel(fileSum)) + list.get(i).getName());

				//如果当前行已经是标题/无序列表了 更新当前级别的父级为本行
				maxArray[list.get(i).getLevel(fileSum)] = i;
			} else {
				//如果等于和小于 说明是同一级别 认为是一个行数据
				//读取当前行所属的上级级别
				is = maxArray[list.get(i).getLevel(fileSum) - 1];
				//将行数据写入到行所属的父级行的 文本中

				ArrayList<MdEntityDocument> mdEntityDocumentArrayList = list.get(is).getList();
				mdEntityDocumentArrayList.add(list.get(i));
				list.get(is).setList(mdEntityDocumentArrayList);
			}
		}


		//将计算完成的数据集合 整合到返回的String中
		StringBuilder stringBuilder = new StringBuilder();
		for (MdEntityDocument mdEntityDocument : list) {
			//如果标题不为空
			if (StrUtil.isNotBlank(mdEntityDocument.getTitle())) {
				stringBuilder.append(mdEntityDocument.getTitle());
				stringBuilder.append("\r\n");
				//如果内容不为空
				if (!mdEntityDocument.getList().isEmpty()) {
					for (MdEntityDocument entityDocument : mdEntityDocument.getList()) {
						//处理解析文本
						stringBuilder.append(entityDocument.getName());
						stringBuilder.append("\r\n");
						if (StrUtil.isNotBlank(entityDocument.getParse())) {
							stringBuilder.append(mdParsePrefix(entityDocument.getLevel(fileSum), entityDocument.getParse()));
							stringBuilder.append("\r\n");
						}
					}
				}
				if (StrUtil.isNotBlank(mdEntityDocument.getParse())) {
					stringBuilder.append(mdParsePrefix(mdEntityDocument.getLevel(fileSum), mdEntityDocument.getParse()));
					stringBuilder.append("\r\n");
				}
			}
		}

		return stringBuilder.toString();
	}

	/**
	 * 根据文件层级 更新解析文件
	 *
	 * @param s     上级数量
	 * @param parse 解析的文本
	 * @return 文件
	 */
	private static String mdParsePrefix(int s, String parse) {
		if (s > 6) {
			StringBuilder stringBuilder = new StringBuilder();
			StringBuilder stringBuilder1 = new StringBuilder();
			for (int i = 0; i < s - 6; i++) {
				stringBuilder.append("  ");
			}

			String[] parseArray = parse.split("\r\n");
			for (String s1 : parseArray) {
				stringBuilder1.append(stringBuilder.toString());
				stringBuilder1.append(s1);
				stringBuilder1.append("\r\n");
			}
			parse = stringBuilder1.toString();
		}
		return parse;
	}


	/**
	 * 根据级别 生成md标题格式前缀
	 *
	 * @param s 级别
	 * @return 生成的前缀字符串
	 */
	private static String mdPrefix(int s) {

		StringBuilder stringBuilder = new StringBuilder();

		if (s < 7) {
			for (int i = 0; i < s; i++) {
				stringBuilder.append("#");
			}
			stringBuilder.append(" ");

		} else {
			for (int i = 0; i < (s - 7); i++) {
				//两个空格
				stringBuilder.append("  ");
			}
			//md无序列表格式的前缀
			stringBuilder.append("- ");
		}
		return stringBuilder.toString();
	}


	/**
	 * 遍历目录下的文件  写入到全局list
	 *
	 * @param file 遍历的路径
	 */
	public static void tree(File file) {
		//如果list是空的 把需要迭代的目录本身加入
		if (list.isEmpty()) {
			list.add(new MdEntityDocument(file, true));
		}

		//遍历path下的文件和目录，放在File数组中
		File[] fs = file.listFiles();
		if (Objects.requireNonNull(fs).length != 0) {

			//遍历File[]数组
			for (File f : fs) {
				//是否屏蔽
				boolean isShielding = false;
				//去除屏蔽路径 如果是屏蔽 跳过
				for (String s : shieldingPathLists) {
					if ((pathStr + s).equals(f.getPath())) {
						isShielding = true;
						break;
					}
				}
				//去除屏蔽后缀名称 如果是屏蔽 跳过
				for (String s : shieldingSuffixLists) {
					if (s.equals("." + FileUtil.getSuffix(f))) {
						isShielding = true;
						break;
					}
				}
				//去除屏蔽文件名称 如果是屏蔽 跳过
				for (String s : shieldingNameLists) {
					if (s.equals(FileUtil.getName(f))) {
						isShielding = true;
						break;
					}
				}
				//如果 是屏蔽 不再迭代 跳出
				if (isShielding) {
					continue;
				}
				//不是屏蔽时
				//如果不是文件夹
				if (!f.isDirectory()) {
					MdEntityDocument mdEntityDocument = new MdEntityDocument();
					//路径原数据
					mdEntityDocument.setMdPath(f);
					//路径是文件夹
					mdEntityDocument.setDirectory(false);

					//判断是否解析文件
					//java文件解析
					if ("java".equals(mdEntityDocument.getSuffix())) {
						try {
							mdEntityDocument.setParse(ParseJavaStructure.javaParse(mdEntityDocument.getMdPath()));
						} catch (Exception e) {
							//DealLog.log(mdEntityDocument.getMdPath(), e);
						}
					}
					list.add(mdEntityDocument);
				} else {
					list.add(new MdEntityDocument(f, true));
					//是文件夹 迭代打印
					tree(f);
				}

			}
		}

	}
}
