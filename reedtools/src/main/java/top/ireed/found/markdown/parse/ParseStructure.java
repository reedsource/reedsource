/*
 * FileName: ParseStructure
 * Author:   reedsource
 */
package top.ireed.found.markdown.parse;

import top.ireed.found.markdown.MdEntity;
import top.ireed.general.TopException;

import java.util.List;

/**
 * 功能简述:
 * 〈文件解析器
 * 2022年4月24日 java解析器1.0
 * <p>
 * <p>
 * 〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/4/24 10:56
 * reedsource@189.cn
 */
public class ParseStructure {

	private ParseStructure() {
	}

	//当前解析器支持java
	public static final String JAVA = "java";

	public static String parse(MdEntity mdEntity, List<String> parseSuffixList) throws TopException {

		if (parseSuffixList.contains(JAVA) && JAVA.equals(mdEntity.getSuffix())) {
			return ParseJavaStructure.javaParse(mdEntity.getMdPath());
		}

		return "";
	}

}
