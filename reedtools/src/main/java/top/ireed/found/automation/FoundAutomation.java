/*
 * Author:   reedsource
 */
package top.ireed.found.automation;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.http.HttpUtil;
import top.ireed.found.automation.entity.Modle;
import top.ireed.found.automation.entity.ToolUnit;
import top.ireed.found.automation.entity.Variable;
import top.ireed.general.TopException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能简述:
 * 〈自动化工具类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/9/16 12:14
 * reedsource@189.cn
 */
public class FoundAutomation {

	/**
	 * 是否打印日志
	 */
	private boolean debug = false;


	/**
	 * 自动化工具类全局变量集合
	 * <key Variable>格式
	 */
	public static final Map<String, Variable> keyVariableMap = new HashMap<>();

	static {
		//默认参数表
		for (int i = 0; i < 101; i++) {
			addKeyVariableMap(String.valueOf(i), String.valueOf(i), String.valueOf(i));
		}
		//调用返回值方法时,可以设定返回值为此
		addKeyVariableMap("null", "null", "null");

		//执行记录唯一参数
		//自动化执行的开始时间和结束时间
		addKeyVariableMap("startTime", "null", "null");
		addKeyVariableMap("endTime", "null", "null");
	}

	/**
	 * 初始化使用 静态初始化默认参数
	 *
	 * @param key   变量名称
	 * @param value 变量值
	 * @param msg   变量描述
	 */
	static void addKeyVariableMap(String key, Object value, String msg) {
		keyVariableMap.put(key, new Variable(key, value, msg));
	}


	/**
	 * 工具单元流集合
	 */
	private final List<ToolUnit> toolUnitList = new ArrayList<>();

	public FoundAutomation() {
		keyVariableMap.put("startTime", new Variable("startTime", System.currentTimeMillis(), "自动化执行的开始时间"));
	}

	/**
	 * 创建一个可以打印日志的自动化模式
	 *
	 * @param debug debug模式
	 */
	public FoundAutomation(boolean debug) {
		keyVariableMap.put("startTime", new Variable("startTime", System.currentTimeMillis(), "自动化执行的开始时间"));
		this.debug = debug;
	}


	/**
	 * 实例FoundSpider后需要设定全局变量
	 * 不存在的变量无法使用
	 *
	 * @param key   变量名称
	 * @param value 变量值
	 * @param msg   变量描述
	 */
	public void addVariables(String key, String value, String msg) {
		keyVariableMap.put(key, new Variable(key, value, msg));
	}


	/**
	 * @param toolModulesName 工具单元名称
	 * @param outName         工具单元返回参数名称
	 */
	public void addToolFlow(String toolModulesName, String outName, String... args) {
		this.toolUnitList.add(new ToolUnit(toolModulesName, outName, args));
	}

	public void SpiderStart() throws TopException {
		if (toolUnitList.size() > 0) {
			for (ToolUnit toolUnit : toolUnitList) {
				flow(toolUnit);
			}
		}

		keyVariableMap.put("endTime", new Variable("endTime", System.currentTimeMillis(), "自动化执行的结束时间"));

		if (debug) {
			System.out.println("执行完毕 共用时" + (Long.parseLong(keyVariableMap.get("endTime").getValue().toString()) - Long.parseLong(keyVariableMap.get("startTime").getValue().toString())) + "ms");
		}
	}

	/*===反射核心============================================================================================================================*/

	/**
	 * 通过反射执行方法名,执行Modules中的方法
	 *
	 * @param toolUnit 描述工具单元的实体类
	 */
	private void flow(ToolUnit toolUnit) throws TopException {
		String toolNme = toolUnit.getToolName();

		//执行方法是否在方法集合中注册
		if (modules.get(toolNme) == null) {
			//这里是异常
			throw new TopException("异常调用 FoundAutomation自动化工具方法", toolNme, "未查到注册信息");
		}
		//方法执行后返回的数据写入的变量名称
		String outName = toolUnit.getOutName();
		//根据名称获取保存在自动化工具类全局变量集合中的变量对象
		Variable variable = keyVariableMap.get(outName);

		//反射
		//不可以直接传入 String... args
		//多个参数时,方法会认为和反射方法的参数列表不一致,导致无法找到反射方法
		//这里传入参数用List包装一层,进入反射方法后再解包使用


		Object o = ReflectUtil.invoke(this, toolNme, toolUnit.getArgs());
		if (debug) {
			StringBuilder s = new StringBuilder("执行 ");
			s.append(modules.get(toolNme));
			s.append("入参列表如下\r\n");
			List<String> list = toolUnit.getArgs();
			for (int i = 0; i < list.size(); i++) {
				s.append("参数").append(i).append("\r\n").append(keyVariableMap.get(list.get(i)));
			}
			s.append("返回值\t: ").append(o).append("\r\n");
			s.append("写入变量\t: ").append(outName).append("\r\n");
			System.out.println(s);
		}
		variable.setValue(o);
		keyVariableMap.put(outName, variable);
	}

	/**
	 * @param args List包装的参数列表
	 * @param i    需要的入参序列号
	 * @return 入参序列号对应的入参 Object对象  使用时根据实际数据类型及需求转换
	 */
	private static Object get(Object args, int i) {
		return keyVariableMap.get(((ArrayList<?>) args).get(i)).getValue();
	}

	/**
	 * @param args List包装的参数列表
	 * @param i    需要的入参序列号
	 * @return 入参序列号对应的入参 返回为String类型
	 */
	private static String getString(Object args, int i) {
		return get(args, i).toString();
	}



	/*===模块部分============================================================================================================================*/

	/**
	 * 注册已有功能模块
	 * 调用flow执行时会先判断调用的功能模块是否已经注册,未注册的功能调用将提示并结束流程
	 */
	public static final Map<String, Modle> modules = new HashMap<>();

	/**
	 * 实例FoundSpider后需要设定全局变量
	 * 不存在的变量无法使用
	 *
	 * @param name  模块名称
	 * @param alias 模块别名
	 * @param msg   模块描述
	 * @param args  参数集
	 */
	private static void addModules(String name, String alias, String msg, String... args) {
		modules.put(name, new Modle(name, alias, msg, args));
	}

	static {
		addModules("getUrlText", "获取url源码字符串", "获取url源码字符串", "需要拉取数据的url地址");
		addModules("regularFindAll", "正则匹配字符串", "正则匹配字符串集  取得内容中匹配的所有结果", "正则表达式", "正则被匹配的字符串");
		addModules("getListTo", "取list的第n个值", "取list的第n个值", "list名称", "第n个");
		addModules("toString", "打印参数", "打印参数");
	}


	/**
	 * 获取url源码字符串
	 * args0 需要拉取数据的url地址
	 */
	private static String getUrlText(Object args) {
		return HttpUtil.get(getString(args, 0));
	}


	/**
	 * 正则匹配字符串  取得内容中匹配的所有结果集
	 * args0 正则表达式
	 * args1 正则被匹配的字符串
	 */
	private static List<String> regularFindAll(Object args) {
		return ReUtil.findAll(getString(args, 0), getString(args, 1), 1);
	}

	/**
	 * 取list的第n个值
	 * args0 list名称
	 * args1 第n个
	 */
	private static Object getListTo(Object args) {
		List list = (List) get(args, 0);
		return list.get(Integer.parseInt(getString(args, 1)));
	}

	/**
	 * 打印参数
	 * args0 需要打印的参数名称
	 */
	private static void toString(Object args) {
		System.out.println(getString(args, 0));
	}

}
