/*
 * FileName: DealMarkdownPathDocument
 * Author:   reedsource
 */
package top.ireed.deal.markdown01;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 功能简述:
 * 〈处理核心
 * 主要把路径解析后 生成为markdown格式文件
 * 可以导入到思维导图格式生成导图
 * <p>
 * 〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2021-03-23 13:55
 * reedsource@189.cn
 */
public class DealMarkdownPath {

	//当前遍历的根目录
	static String pathStr;

	//当前屏蔽相对根目录的相对路径列表
	private static List<String> shieldingPathLists = new ArrayList<>(16);

	//要屏蔽的文件的后缀名称list 只判断后缀
	private static List<String> shieldingSuffixLists = new ArrayList<>(16);

	//要屏蔽的文件名称list   注意 会屏蔽全部符合名称的文件
	private static List<String> shieldingNameLists = new ArrayList<>(16);


	//保存全部目录下文件数据
	static ArrayList<MdEntity> list = new ArrayList<>();
	/**
	 * 保留当前文件层级父级索引的索引值
	 * 最大支持层级
	 */
	private static int[] maxArray = new int[30];


	private DealMarkdownPath() {
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
	public static String core(File file, List<String> shieldingPathList, List<String> shieldingSuffixList, List<String> shieldingNameList) {
		shieldingPathLists = shieldingPathList;
		shieldingSuffixLists = shieldingSuffixList;
		shieldingNameLists = shieldingNameList;
		//获取需要遍历的根目录的层级  在后面的所有子目录减去层级
		int fileSum = file.getPath().split("\\\\").length;

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
			if (list.get(i).isDirectory() || list.get(i).getLevel(fileSum) > 7) {
				list.get(i).setTitle(mdPrefix(list.get(i).getLevel(fileSum)) + list.get(i).getName());

				//如果当前行已经是标题/无序列表了 更新当前级别的父级为本行
				maxArray[list.get(i).getLevel(fileSum)] = i;
			} else {
				//如果等于和小于 说明是同一级别 认为是一个行数据
				//读取当前行所属的上级级别
				is = maxArray[list.get(i).getLevel(fileSum) - 1];
				//将行数据写入到行所属的父级行的 文本中
				if (StrUtil.isBlank(list.get(is).getText())) {
					list.get(is).setText(list.get(i).getName());
				} else {
					list.get(is).setText(list.get(is).getText() + "\r\n" + list.get(i).getName());
				}
			}
		}

		//将计算完成的数据集合 整合到返回的String中
		StringBuilder stringBuilder = new StringBuilder();
		for (MdEntity mdEntity : list) {
			//如果标题不为空
			if (StrUtil.isNotBlank(mdEntity.getTitle())) {
				stringBuilder.append(mdEntity.getTitle());
				stringBuilder.append("\r\n");
				//如果内容不为空
				if (StrUtil.isNotBlank(mdEntity.getText())) {
					stringBuilder.append(mdEntity.getText());
					stringBuilder.append("\r\n");
				}
			}
		}

		return stringBuilder.toString();
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
			list.add(new MdEntity(file, true));
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
					list.add(new MdEntity(f, false));
				} else {
					list.add(new MdEntity(f, true));
					//是文件夹 迭代打印
					tree(f);
				}

			}
		}

	}
}
