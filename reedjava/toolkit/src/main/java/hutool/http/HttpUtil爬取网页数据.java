/*
 * FileName: HttpUtil爬取网页数据
 * Author:   reedsource
 */
package hutool.http;


import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import top.ireed.deal.DealLog;

import java.util.List;

/**
 * 功能简述:
 * 〈爬取网页数据 正则获取额定数据〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/19 16:58
 * reedsource@189.cn
 */
public class HttpUtil爬取网页数据 {
	public static void main(String[] args) {
		//文件保存路径
		FileWriter fileWriter = new FileWriter("D:\\cache\\nihaowua.txt");

		StringBuilder stringBuilder = new StringBuilder();
		for (int j = 0; j < 10; j++) {
			//请求列表页
			String listContent;
			try {
				listContent = HttpUtil.get("https://www.nihaowua.com/");
			} catch (Exception e) {
				//请求失败 尝试延时 重新访问
				DealLog.log("访问失败,将继续尝试");
				continue;
			}
			//使用正则获取所有标题
			//List<String> titles = ReUtil.findAll("<span class=\"text-ellipsis\">(.*?)</span>", listContent, 1);
			//<section> <div id="post59" class="post59"> <span>少女情怀总是湿</span> </div> </section>
			//<section> <div id="post66" class="post66"> <p>我爱你 没有技巧 真诚野蛮</p> </div> </section>
			//<section> <div id="post69" class="post69"> <div>你不要脸的样子真可爱</div> </div> </section>
			//以上 发现规律
			List<String> titles2 = ReUtil.findAll("<section>(.*?)</section>", listContent, 1);

			for (String title : titles2) {
				//打印标题 第二个> 与 第三个< 之间的内容 就是需要的内容
				String a = StrUtil.subByCodePoint(title, StrUtil.ordinalIndexOf(title, ">", 2) + 1, StrUtil.ordinalIndexOf(title, "<", 3));
				stringBuilder.append(a);
				stringBuilder.append("\n");
				DealLog.log(a);
			}
		}
		//追加写到文件
		fileWriter.append(stringBuilder.toString());
	}
}
