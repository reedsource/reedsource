/*
 * FileName: DealMarkdownPathDocument
 * Author:   reedsource
 */
package top.ireed.found.markdown;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import top.ireed.deal.DealLog;
import top.ireed.found.markdown.parse.ParseStructure;
import top.ireed.general.TopException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 功能简述:
 * 〈
 * 解析资料到markdown文档
 * <p>
 * 情景:
 * 1. 解析目录结构到md格式 包含目录层级和文件名称
 * 2. 解析目录下文件 根据文件解析器规则,解析文件结构
 *
 * @author reedsource
 * @version 1.0.0
 * date 2021-03-23 13:55
 * reedsource@189.cn
 */
public class FoundMarkdown {

	//保存全部目录下文件数据
	static ArrayList<MdEntity> list = new ArrayList<>();
	/**
	 * 保留当前文件层级父级索引的索引值
	 * 最大支持层级
	 */
	private static int[] maxArray = new int[30];

	/**
	 * 将保存全部数据的list处理为符合规则的md格式文本
	 *
	 * @param file                目标根路径
	 * @param shieldingPathList   要屏蔽的路径list  以根目录向后相对路径
	 * @param shieldingSuffixList 要屏蔽的文件的后缀名称list 只判断后缀
	 * @param shieldingNameList   要屏蔽的文件名称list   注意 会屏蔽全部符合名称的文件
	 * @param isSon               是将根目录解析为一个文件还是将根目录下的文件及文件夹,解析为多个文件
	 * @param parseSuffixList     文件类型解析列表
	 * @param toFile              生成的md文件的目录
	 */
	public void toMd(File file
			, List<String> shieldingPathList
			, List<String> shieldingSuffixList
			, List<String> shieldingNameList
			, boolean isSon
			, List<String> parseSuffixList
			, File toFile
	) throws TopException {
		//基础处理 将相对路径专为绝对路径
		for (int i = 0; i < shieldingPathList.size(); i++) {
			shieldingPathList.set(i, new File(file, shieldingPathList.get(i)).getPath());
		}

		//如果 文件夹 子文件/文件夹 单独 解析
		if (isSon) {
			//遍历path下的文件和目录，放在File数组中
			File[] fs = file.listFiles();
			if (fs != null) {
				for (File f : fs) {
					//符合屏蔽条件 跳出
					if (shieldingPathList.contains(f.getPath()) || shieldingSuffixList.contains("." + FileUtil.getSuffix(f)) || shieldingNameList.contains(FileUtil.getName(f))) {
						continue;
					}
					//根目录下的每个 子文件/文件夹 单独 解析
					mdToFile(new File(toFile.getPath(), f.getName() + ".md"), core(f, shieldingPathList, shieldingSuffixList, shieldingNameList, parseSuffixList));
					//清空全局数据
					list.clear();
				}
			}
		} else {
			mdToFile(new File(toFile.getPath(), file.getName() + ".md"), core(file, shieldingPathList, shieldingSuffixList, shieldingNameList, parseSuffixList));
		}
	}

	/**
	 * 将保存全部数据的list处理为符合规则的md格式文本
	 *
	 * @param file                目标根路径
	 * @param shieldingPathList   要屏蔽的路径list  以根目录向后相对路径
	 * @param shieldingSuffixList 要屏蔽的文件的后缀名称list 只判断后缀
	 * @param shieldingNameList   要屏蔽的文件名称list   注意 会屏蔽全部符合名称的文件
	 * @param parseSuffixList     文件类型解析列表
	 * @return 处理为符合规则的md格式文本
	 * @throws TopException 异常
	 */
	private static String core(File file
			, List<String> shieldingPathList
			, List<String> shieldingSuffixList
			, List<String> shieldingNameList
			, List<String> parseSuffixList
	) throws TopException {
		//核心 迭代遍历获取目录下全部文件,放入全局list
		//注意 本处调用时 根目录会写入list 故层级为1
		tree(file, 1, shieldingPathList, shieldingSuffixList, shieldingNameList, parseSuffixList);

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
			if (list.get(i).isDirectory() || list.get(i).getLevel() > 7 || StrUtil.isNotBlank(list.get(i).getUnorderedListMsg())) {
				list.get(i).setTitle(prefixMd(list.get(i).getLevel()) + list.get(i).getMdPath().getName());

				//如果当前行已经是标题/无序列表了 更新当前级别的父级为本行
				maxArray[list.get(i).getLevel()] = i;
			} else {
				//如果等于和小于 说明是同一级别 认为是一个行数据
				//读取当前行所属的上级级别
				is = maxArray[list.get(i).getLevel() - 1];
				//将行数据写入到行所属的父级行的 文本中

				List<MdEntity> mdEntityArrayList = list.get(is).getList();
				mdEntityArrayList.add(list.get(i));
				list.get(is).setList(mdEntityArrayList);
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
				if (!mdEntity.getList().isEmpty()) {
					for (MdEntity entityDocument : mdEntity.getList()) {
						//处理解析文本
						stringBuilder.append(entityDocument.getMdPath().getName());
						stringBuilder.append("\r\n");
						if (StrUtil.isNotBlank(entityDocument.getUnorderedListMsg())) {
							stringBuilder.append(mdParsePrefix(entityDocument.getLevel(), entityDocument.getUnorderedListMsg()));
							stringBuilder.append("\r\n");
						}
					}
				}
				if (StrUtil.isNotBlank(mdEntity.getUnorderedListMsg())) {
					stringBuilder.append(mdParsePrefix(mdEntity.getLevel(), mdEntity.getUnorderedListMsg()));
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
	 * 1 #
	 * 2 ##
	 * 3 ###
	 * 4 ####
	 * 5 #####
	 * 6 ######
	 * 7 -
	 * 8   -
	 *
	 * @param s 级别
	 * @return 生成的前缀字符串
	 */
	private static String prefixMd(int s) {
		StringBuilder returnStringBuilder = new StringBuilder();
		if (s < 6) {
			//默认一级标题占用
			for (int i = 0; i < s; i++) {
				returnStringBuilder.append("#");
			}
			//#与标题之间空格
			returnStringBuilder.append(" ");
		} else {
			StringBuilder builder = new StringBuilder();
			//无序列表下一集级与上一级分隔为2个空格
			for (int i = 0; i < (s - 6); i++) {
				//两个空格
				builder.append("  ");
			}
			//md无序列表格式的前缀
			builder.append("- ");
			returnStringBuilder.append(builder);
		}
		return returnStringBuilder.toString();
	}


	/**
	 * 遍历目录下的文件  写入到全局list
	 *
	 * @param file                目标根路径  遍历的路径
	 * @param level               当前路径与项目根目录层数的绝对值  默认创建文件为标题 为1认为是根目录
	 * @param shieldingPathList   要屏蔽的路径list  以根目录向后相对路径
	 * @param shieldingSuffixList 要屏蔽的文件的后缀名称list 只判断后缀
	 * @param shieldingNameList   要屏蔽的文件名称list   注意 会屏蔽全部符合名称的文件
	 * @param isParse             是否做文件解析
	 * @param parseSuffixList     文件类型解析列表
	 */
	public static void tree(File file
			, int level
			, List<String> shieldingPathList
			, List<String> shieldingSuffixList
			, List<String> shieldingNameList
			, List<String> parseSuffixList
	) throws TopException {

		//符合屏蔽条件 跳出
		if (shieldingPathList.contains(file.getPath()) || shieldingSuffixList.contains("." + FileUtil.getSuffix(file)) || shieldingNameList.contains(FileUtil.getName(file))) {
			return;
		}

		//如果 是文件
		if (!file.isDirectory()) {
			MdEntity mdEntity = new MdEntity();
			//路径原数据
			mdEntity.setMdPath(file);
			//路径是文件夹
			mdEntity.setDirectory(false);
			//相对根目录 层级+1
			mdEntity.setLevel(level);
			//文件解析开关为开
			if (!parseSuffixList.isEmpty()) {
				// 进入文件解析器 交由文件解析器解析
				mdEntity.setUnorderedListMsg(ParseStructure.parse(mdEntity, parseSuffixList));
			}
			list.add(mdEntity);
		} else {
			//文件夹 将目录加入list
			list.add(new MdEntity(file, level, true));
			//遍历path下的文件和目录，放在File数组中
			File[] fs = file.listFiles();
			if (Objects.requireNonNull(fs).length != 0) {
				//遍历File[]数组
				for (File f : fs) {
					//是文件夹 继续迭代 迭代时目录层级+1
					tree(f, level + 1, shieldingPathList, shieldingSuffixList, shieldingNameList, parseSuffixList);
				}
			}
		}
	}


	/**
	 * 将字符串写入到文件中
	 *
	 * @param file 生成文件的路径
	 * @param str  文件的字符串
	 */
	public void mdToFile(File file, String str) {
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
}
