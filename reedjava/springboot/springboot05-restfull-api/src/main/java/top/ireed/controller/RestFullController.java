/*
 * FileName: RestFullController
 * Author:   ireed
 */
package top.ireed.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能简述:
 * 〈实现restFull地址命名规则〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 */

@RestController
public class RestFullController {

	/**
	 * @param id   id
	 * @param name name
	 * @return 注意 本地属性之间需要有/分隔
	 */
	@GetMapping("/boot/test/{id}/{name}")
	public String test(@PathVariable Long id, @PathVariable String name) {
		return "id=" + id + "  name= " + name;
	}
}
