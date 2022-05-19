/*
 * FileName: 正则替换字符串replaceFirst和replaceAll
 * Author:   reedsource
 */
package k18字符串.正则表达式;

import org.junit.Test;
import top.ireed.deal.DealLog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/19 13:27
 * reedsource@189.cn
 */
public class 正则替换字符串replaceFirst和replaceAll {
	/**
	 * replaceFirst 和 replaceAll 方法
	 * <p>
	 * replaceFirst 和 replaceAll 方法用来替换匹配正则表达式的文本。
	 * 不同的是，replaceFirst 替换首次匹配，replaceAll 替换所有匹配。
	 */
	@Test
	public void 正则替换字符串replaceFirst和replaceAll_Test(){
		String REGEX = "dog";
		String INPUT = "The dog says meow. " + "All dogs say meow.";
		String REPLACE = "cat";

		Pattern p = Pattern.compile(REGEX);
		// get a matcher object
		Matcher m = p.matcher(INPUT);
		INPUT = m.replaceAll(REPLACE);
		DealLog.log(INPUT);

		//The cat says meow. All cats say meow.



	}

}
