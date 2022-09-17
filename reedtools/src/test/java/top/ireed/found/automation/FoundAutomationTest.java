package top.ireed.found.automation;

import junit.framework.TestCase;
import org.junit.Test;
import top.ireed.general.TopException;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedbook
 * date 2022/9/16 12:35
 * mail reedsource@189.cn
 */
public class FoundAutomationTest extends TestCase {

	@Test
	public void test() throws TopException {
		FoundAutomation foundAutomation = new FoundAutomation(true);
		//参数表
		foundAutomation.addVariables("url", "https://www.nihaowua.com/", "返回的urlText");
		foundAutomation.addVariables("urlText", "", "返回的urlText");
		foundAutomation.addVariables("regex", "<section>(.*?)</section>", "返回的urlText");
		foundAutomation.addVariables("content", "", "regularFindAll必须");
		foundAutomation.addVariables("FindAllList", "", "返回的全部标题");
		foundAutomation.addVariables("listTo1", "", "list第一个值");
		foundAutomation.addVariables("regex1", ">(.*?)<", "匹配第二条件");
		foundAutomation.addVariables("FindAllList1", "", "返回的全部标题");
		foundAutomation.addVariables("listTo3", "", "list第3个值");


		//开始流程
		//取url全部数据
		foundAutomation.addToolFlow("getUrlText", "urlText", "url");
		//匹配符合的全部数据组
		foundAutomation.addToolFlow("regularFindAll", "FindAllList", "regex", "urlText");
		//取出一个数据组
		foundAutomation.addToolFlow("getListTo", "listTo1", "FindAllList", "0");
		//再次匹配
		foundAutomation.addToolFlow("regularFindAll", "FindAllList1", "regex1", "listTo1");
		//取出想要的数据组
		foundAutomation.addToolFlow("getListTo", "listTo3", "FindAllList1", "1");
		//打印结果
		foundAutomation.addToolFlow("toString", "null", "listTo3");

		//开始执行
		foundAutomation.SpiderStart();
	}
}