/*
 * Author:   reedsource
 */
package top.ireed.found.automation.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 功能简述:
 * 〈描述工具单元的实体类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/9/16 14:25
 * reedsource@189.cn
 */
public class ToolUnit {

	/**
	 * 工具单元名称
	 */
	private String toolName;
	/**
	 * 工具单元返回参数名称
	 */
	private String outName;
	/**
	 * 入参数组
	 */
	private final List<String> args;

	public ToolUnit(String toolName, String outName, String... args) {
		this.toolName = toolName;
		this.outName = outName;
		List<String> list = new ArrayList<>();
		if (args.length != 0) {
			list.addAll(Arrays.asList(args));
		}
		this.args = list;

	}

	public String getToolName() {
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	public String getOutName() {
		return outName;
	}

	public void setOutName(String outName) {
		this.outName = outName;
	}

	public List<String> getArgs() {
		return args;
	}
}
