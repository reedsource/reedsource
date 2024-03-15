/*
 * FileName: JspAnalysisController
 * Author:   reedsource
 */
package top.ireed.analysis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能简述:
 * 〈jsp原理控制器〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/17 23:15
 * reedsource@189.cn
 */
@Controller
@RequestMapping("analysis")
public class JspAnalysisController {

	/**
	 * 查看jsp Servlet生命周期
	 *
	 * @return 进入
	 */
	@GetMapping("vita")
	public String vita() {
		return "analysis/jspVita";
	}

}
