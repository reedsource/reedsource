/*
 * Author:   reedsource
 */
package top.ireed.found.automation.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 功能简述:
 * 〈自动化功能模块说明〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/9/17 16:45
 * reedsource@189.cn
 */
public class Modle {

	/**
	 * 模块名称
	 */
	private String name;
	/**
	 * 模块别名
	 */
	private String alias;
	/**
	 * 模块描述
	 */
	private String msg;
	/**
	 * 参数集
	 */
	private final List<String> args;

	public Modle(String name, String alias, String msg, String... args) {
		this.name = name;
		this.alias = alias;
		this.msg = msg;
		List<String> list = new ArrayList<>();
		if (args.length != 0) {
			list.addAll(Arrays.asList(args));
		}
		this.args = list;
	}

	public String getName() {
		return name;
	}

	public String getAlias() {
		return alias;
	}

	public String getMsg() {
		return msg;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("模块名称 : ").append(name).append("    ").append("别名 : ").append(alias).append("\r\n").append("描述 : ").append(msg).append("\r\n").append("需求参数 : ").append("\r\n");

		for (int i = 0; i < args.size(); i++) {
			s.append("\t").append("参数").append(i).append(" ").append(args.get(i)).append("\r\n");
		}
		return s.toString();
	}
}
