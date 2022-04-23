/*
 * FileName: DealMarkdownPathTest
 * Author:   reedsource
 */
package top.ireed.deal.markdown01;

import cn.hutool.core.io.IoUtil;
import org.junit.Assert;
import org.junit.Test;
import top.ireed.deal.DealLog;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能简述:
 * 〈
 * 将一个目录下的java项目
 * 解析后 导入到MindMaster中 形成导图的工具
 * <p>
 * 主要用来熟悉项目
 * <p>
 * 后续有进一步解析可能
 * 〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2021-03-21 12:30
 * reedsource@189.cn
 */
public class DealMarkdownPathTest {

	/*
	思路
	1 循环遍历目录 得到目录下全部文件属性的list
	2 循环处理 解析为md格式的字符串
	2.1 6层以内 解析为标题样式  另外如果没有内容 考虑将src/DealMarkdownPathTest/java/cn..缩进到标题
	2.2 6层以后 全部缩进到无序列表中 无序列表规则 第一层- 内容  第二层开始 两个空格一层
	2.3 将数据解析到字符串 并写入到txt中
	* */

	@Test
	public void test() throws IOException {
		//要遍历的路径
		String path = "D:\\codes\\reedsource\\reedtools\\";

		//要屏蔽的路径list  以根目录向后相对路径
		List<String> shieldingPathList = new ArrayList<>(16);
		shieldingPathList.add(".git");
		shieldingPathList.add(".idea");
		shieldingPathList.add(".gitignore");

		//要屏蔽的文件的后缀名称list 只判断后缀
		List<String> shieldingSuffixList = new ArrayList<>(16);
		shieldingSuffixList.add(".mvn");
		shieldingSuffixList.add(".iml");

		//要屏蔽的文件名称list   注意 会屏蔽全部符合名称的文件
		List<String> shieldingNameList = new ArrayList<>(16);
		shieldingNameList.add("target");

		//接收核心解析   返回的数据
		String coreString = DealMarkdownPath.core(new File(path), shieldingPathList, shieldingSuffixList, shieldingNameList);
		DealLog.log(coreString);

		File f = new File("D:\\cache\\b.md");

		// 先得到文件的上级目录，并创建上级目录，在创建文件
		if (!f.exists()) {
			DealLog.log(f.getParentFile().mkdir());
			DealLog.log(f.createNewFile());
		}

		// 构建FileOutputStream对象,文件不存在会自动新建--------------------------------
		FileOutputStream fop = new FileOutputStream(f);
		OutputStreamWriter outputStreamWriter = IoUtil.getUtf8Writer(fop);
		outputStreamWriter.write(coreString);
		// 关闭写入流,同时会把缓冲区内容写入文件,所以上面的注释掉
		outputStreamWriter.close();

		// 关闭输出流,释放系统资源
		fop.close();
		Assert.assertTrue(true);
	}


}