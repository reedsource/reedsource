/*
 * FileName: ParseJavaStructure
 * Author:   reedsource
 */
package top.ireed.deal.parse.java;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HTMLFilter;
import top.ireed.deal.DealLog;
import top.ireed.general.TopException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static top.ireed.general.TopConstant.*;

/**
 * 功能简述:
 * 〈解析java文件〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2021-04-01 14:28
 * reedsource@189.cn
 */
public class ParseJavaStructure {

	public static final String STR = "    - ";

	public static String javaParse(File path) throws TopException {
		StringBuilder sb = new StringBuilder();
		//返回的符合规则的文件markdown字符串
		StringBuilder parseString = new StringBuilder();

		//读取文件的内容
		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8), 512 * 1024)
		) {
			String line;

			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
				sb.append("\r\n");
			}

			//java文件的内容
			String str = sb.toString();

			//1 文件方法解析
			// 获取第一个{前的所有内容 本部分只有一个 可以解析出对类文件的说明
			String headStr = str.substring(0, str.indexOf(F_D_Z));
			//将文件名称及注释解析加入
			parseString.append(headParse(headStr));

			//2 文件子方法解析
			// 获取主要{}内的内容 是一个多个{ } 组成的多子程序
			// 截取身体部分
			// 截取包含第一个{与最后一个}的全部内容
			// 符合通用的正则查找
			String bodyStr = str.substring(str.indexOf(F_D_Z), str.lastIndexOf(F_D_Y));
			//2.1 获取全部符合 } 到  ) { 中文格式的文本组
			//方法的结构必然会有的格式
			//解析的结果包含前后的 }方法文本信息{
			//初始方法文本组结构
			List<String> bodyList = ReUtil.findAll("\\}(.*?)\\) \\{", bodyStr, 0);

			//2.2 解析
			for (String title : bodyList) {
				//解析方法文本 加入结果
				try {
					parseString.append(methodParse(title));
				} catch (Exception e) {
					DealLog.log(path, e);
				}
			}

		} catch (IOException e) {
			throw new TopException("解析java文件异常", e);
		}

		//html文本转译
		return HTMLFilter.htmlSpecialChars(parseString.toString());
	}


	/**
	 * 提取方法 markdown 无序文本
	 *
	 * @param methodStr 方法字符串
	 * @return 符合markdown  无序列表格式的字符串 文件名称为一级- 文件名
	 */
	private static String methodParse(String methodStr) {
		//到目前 全部解析为符合 以下格式的集合
		//  }/**
		//  * 公开方法返回文件名注释
		//  *
		//  * @param filePath 公开方法返回文件名文件
		//  * @return 公开方法返回文件名文件名
		//  * @since 公开版本4.1.13
		//  */
		//  public static String getName(String filePath) {

		//1 初始处理 去除干扰信息
		//1.1 只保留文本中最后一个;到结尾的文本 去除定义的包含{的枚举 字典 别名等结构  带来的干扰
		if (methodStr.lastIndexOf(F_F) > 0) {
			methodStr = methodStr.substring(methodStr.lastIndexOf(F_F) + 1).trim();
		}
		//1.2 去除首尾的}{号
		methodStr = methodStr.substring(1, methodStr.length() - 1).trim();
		//定义返回的结果文本
		StringBuilder methodMark = new StringBuilder();

		//2 获取方法部分信息

		//转换为方法的字符串行数组
		String[] methodArray = methodStr.split("\r\n");

		//2.1 取最后一行字符串 即方法名称相关信息
		//public static String getName(String filePath, String filePath1)
		//private static String getName1(String filePath, String filePath1)
		String st = methodArray[methodArray.length - 1].trim();
		//2.2 方法名称属性部分字符串  取(前的部分
		//+2.3 方法名称行必然包含(
		if (!st.contains(F_X_Z)) {
			return "";
		}
		String methodHead = st.substring(0, st.indexOf(F_X_Z));
		//2.3 去除非方法文本  类似方法结构的文本干扰
		//方法的(前一个字符必然不为" " 其它逻辑必为" "
		if (" ".equals(methodHead.substring(methodHead.length() - 1))) {
			return "";
		}
		//方法的
		//2.4 拆分为数组 数组最后一个成员 必为方法名
		String[] methodHeadArray = methodHead.split(" ");
		String method = methodHeadArray[methodHeadArray.length - 1];

		//方法名必然是在文件名后的二级
		methodMark.append("  - ");
		methodMark.append(method);
		methodMark.append("\r\n");

		// 2.5 获取方法的注释
		methodMark.append(getMethodsAnnotation(STR, methodStr));

		//2,6 获取返回信息
		methodMark.append(getReturnStr(methodArray));

		//3 解析处理参数部分
		//3.1 方法的参数部分  取{}中间的部分
		String methodBody = st.substring(st.indexOf(F_X_Z) + 1, st.indexOf(")"));
		//参数不是无参数结构的空字符串
		if (StrUtil.isNotBlank(methodBody)) {
			//3.2 方法参数数组
			String[] methodBodyArray = methodBody.split(", ");
			//3.3 如果有参数
			if (methodBodyArray.length > 0) {
				for (String s : methodBodyArray) {
					//获取方法参数相关的标准注释
					methodMark.append(methodBodyNote(s, methodArray));
				}
			}
		}
		return methodMark.toString();
	}

	/**
	 * 返回@return 返回文本
	 *
	 * @param methodArray 文本组
	 * @return 文本
	 */
	private static String getReturnStr(String[] methodArray) {
		for (String s1 : methodArray) {
			//在文本数组中寻找参数名称
			int intIndex = s1.indexOf("@return");
			if (intIndex != -1 && intIndex + 7 < s1.length()) {
				//定义返回的结果文本
				StringBuilder methodMark = new StringBuilder();
				methodMark.append(STR);
				methodMark.append(s1.substring(intIndex).trim());
				methodMark.append("\r\n");
				return methodMark.toString();
			}
		}
		return "";
	}


	/**
	 * 获取方法参数 + 参数注释  的文本
	 *
	 * @param methodBody  参数名称
	 * @param methodArray 方法全部文本数组
	 * @return 文本
	 */
	private static String methodBodyNote(String methodBody, String[] methodArray) {
		//参数名称数组 空格划分
		String[] methodBodyArray = methodBody.split(" ");
		//参数的名称
		String methodBodyArrayName = methodBodyArray[methodBodyArray.length - 1];

		//返回的字符串
		StringBuilder methodBodyString = new StringBuilder();

		//参数信息必然是在文件名后的三级
		methodBodyString.append("    - @param ");
		methodBodyString.append(methodBody);
		methodBodyString.append("\r\n");

		//循环查找 不查找最后一行
		for (int i = 0; i < methodArray.length - 1; i++) {
			//在文本数组中寻找参数名称
			int intIndex = methodArray[i].indexOf(methodBodyArrayName);
			//如果参数名称存在
			//且 查询到的字符串后 有注释文本
			if (intIndex > 0 && intIndex + methodBodyArrayName.length() < methodArray[i].length()) {
				//参数注释 本处取行文本后面的部分
				//参数注释 必然是在文件名后的四级
				methodBodyString.append("      - ");
				methodBodyString.append(methodArray[i].substring(intIndex + methodBodyArrayName.length() + 1).trim());
				methodBodyString.append("\r\n");
				break;
			}
		}

		return methodBodyString.toString();
	}


	/**
	 * 解析包含文件主方法 以及注释的文本
	 * <p>
	 * 解析为 markdown 无序文本
	 *
	 * @param methodStr class的头信息字符串
	 * @return 符合markdown  无序列表格式的字符串 文件名称为一级- 文件名
	 */
	private static String headParse(String methodStr) {
		StringBuilder methodMark = new StringBuilder();
		//转换为字符串行数组
		String[] headStrArray = methodStr.split("\r\n");
		//1 提取主方法信息
		//取最后一行字符串  为文件主方法
		String st = headStrArray[headStrArray.length - 1].trim();
		methodMark.append("- ");
		methodMark.append(st);
		methodMark.append("\r\n");

		//2 提取方法注释
		//2021年4月4日 修改注释级别与方法名称等级平级 方法相关信息在最上面
		methodMark.append(getMethodsAnnotation("- ", methodStr));
		return methodMark.toString();
	}

	/**
	 * 提取方法文本多行注释
	 *
	 * @param levelStr  注释的前缀文本
	 * @param methodStr 全部的文本
	 * @return 注释文本
	 */
	static String getMethodsAnnotation(String levelStr, String methodStr) {
		StringBuilder methodMark = new StringBuilder();
		//2 提取方法注释
		//用/**分隔 主方法注释必然在/**中
		String[] headStrArray1 = methodStr.split("/\\*\\*");

		//2.1 如果分隔的数组成员等于2 正常标准方法注释 提取方法注释
		if (headStrArray1.length == 2) {
			//将方法注释文本拆分为行文本
			String[] headStrArray2 = headStrArray1[headStrArray1.length - 1].split("\\*");

			//合并多行注释
			for (String s : headStrArray2) {
				//去除数组行首尾空格
				s = s.trim();
				//排除空行

				//排除开头为@
				if (StrUtil.isBlank(s) || "@".equals(s.substring(0, 1))) {
					continue;
				}
				//排除开头为/\r\n
				if (s.length() > 3 && "/\r\n".equals(s.substring(0, 3))) {
					continue;
				}

				methodMark.append(levelStr);
				methodMark.append(s);
				methodMark.append("\r\n");
			}
		}
		return methodMark.toString();

	}


	/**
	 * 获取符合规则的方法参数 + 参数注释  的文本
	 *
	 * @param methodBody  参数名称
	 * @param methodArray 方法全部文本数组
	 * @return 文本
	 */
	private static String methodHeadNote(String methodBody, String[] methodArray) {
		//参数名称数组 空格划分
		String[] methodBodyArray = methodBody.split(" ");
		//参数的名称
		String methodBodyArrayName = methodBodyArray[methodBodyArray.length - 1];

		//返回的字符串
		StringBuilder methodBodyString = new StringBuilder();

		//参数信息必然是在文件名后的三级
		methodBodyString.append(STR);
		methodBodyString.append(methodBody);
		methodBodyString.append("\r\n");

		for (String s : methodArray) {
			//在文本数组中寻找参数名称
			int intIndex = s.indexOf(methodBodyArrayName);
			//如果参数名称存在
			if (intIndex > 0) {
				//参数注释 本处取行文本后面的部分
				//参数注释 必然是在文件名后的四级
				methodBodyString.append("      - ");
				methodBodyString.append(s.substring(intIndex + methodBodyArrayName.length() + 1));
				methodBodyString.append("\r\n");
				break;
			}
		}

		return methodBodyString.toString();
	}


}
