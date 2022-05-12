/*
 * FileName: JavaEnum
 * Author:   reedsource
 */
package j01枚举;

import org.junit.Test;
import top.ireed.deal.DealLog;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * 功能简述:
 * 〈enum枚举类解析〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/12 23:41
 * reedsource@189.cn
 */
public class JavaEnum {
	/**
	 * 颜色枚举
	 */
	public enum Color {
		/**
		 * RED 红色
		 */
		RED, GREEN, BLANK, YELLOW
	}

	/**
	 * 枚举常量
	 */
	@Test
	public void test() {
		DealLog.log(Color.BLANK);
	}

	private Color color = Color.RED;

	/**
	 * 增强可读性
	 */
	@Test
	public void test1() {
		switch (color) {
			case RED:
				color = Color.GREEN;
				break;
			case YELLOW:
				color = Color.RED;
				break;
			case GREEN:
				color = Color.YELLOW;
				break;
			default:
				color = Color.RED;
		}
	}

	/**
	 * 颜色枚举
	 */
	public enum Color1 {
		/**
		 * 颜色
		 */
		RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);
		/**
		 * 成员变量
		 */
		private String name;
		private int index;

		/**
		 * 设定构造方法
		 */
		Color1(String name, int index) {
			this.name = name;
			this.index = index;
		}

		/**
		 * 普通方法
		 */
		public static String getName(int index) {
			for (Color1 c : Color1.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		/**
		 * get set 方法
		 */
		public String getName() {
			return name;
		}


		public int getIndex() {
			return index;
		}

		/**
		 * 覆盖方法
		 */
		@Override
		public String toString() {
			return this.index + "_" + this.name;
		}
	}

	/**
	 * 自定义枚举方法
	 * <p>
	 * 枚举类型相当于静态类型, 只能在构建枚举类的时候初始化
	 * 不能用 new Color1("红色", 1) 创造
	 */
	@Test
	public void test3() {
		Color1 colo = Color1.GREEN;
		DealLog.log(colo.getName());
		DealLog.log(colo.getIndex());
	}


	/**
	 * 枚举类型覆盖父类方法
	 * toString
	 */
	@Test
	public void test4() {
		DealLog.log(Color1.RED.toString());
	}

	public interface Behaviour {
		/**
		 * 接口方法
		 */
		void print();

		/**
		 * 接口方法
		 *
		 * @return 属性
		 */
		String getInfo();
	}

	/**
	 * 颜色枚举
	 */
	public enum Color2 implements Behaviour {
		/**
		 * 颜色
		 */
		RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);
		/**
		 * 成员变量
		 */
		private String name;
		private int index;

		/**
		 * 构造方法
		 */
		private Color2(String name, int index) {
			this.name = name;
			this.index = index;
		}

		//

		/**
		 * 接口方法
		 *
		 * @return 属性
		 */
		@Override
		public String getInfo() {
			return this.name;
		}

		/**
		 * 接口方法
		 */
		@Override
		public void print() {
			DealLog.log(this.index + ":" + this.name);
		}
	}

	/**
	 * 枚举类实现接口
	 */
	@Test
	public void test5() {
		Color2 color1 = Color2.GREEN;
		DealLog.log(color1.getInfo());
		color1.print();
	}

	public interface Food {
		enum Coffee implements Food {
			BLACK_COFFEE, DECAF_COFFEE, LATTE, CAPPUCCINO
		}

		enum Dessert implements Food {
			FRUIT, CAKE, GELATO
		}
	}

	/**
	 * 接口组织枚举
	 * 类似分组
	 */
	@Test
	public void test6() {
		DealLog.log(Food.Coffee.BLACK_COFFEE);
		DealLog.log(Food.Dessert.FRUIT);
	}

	/**
	 * 1.定义枚举类型
	 */
	public enum Color3 {

		// 利用构造函数传参
		RED(1), GREEN(3), YELLOW(2);

		/**
		 * 定义私有变量
		 */
		private int nCode;

		/**
		 * 构造函数，枚举类型只能为私有
		 */
		private Color3(int nCode) {
			this.nCode = nCode;
		}

		@Override
		public String toString() {
			return String.valueOf(this.nCode);
		}
	}


	/**
	 * 演示EnumMap的使用，
	 * EnumMap跟HashMap的使用差不多，只不过key要是枚举类型
	 */
	@Test
	public void testEnumMap() {
		EnumMap<Color3, String> currEnumMap = new EnumMap<>(Color3.class);
		currEnumMap.put(Color3.RED, "红灯");
		currEnumMap.put(Color3.GREEN, "绿灯");
		currEnumMap.put(Color3.YELLOW, "黄灯");

		// 2.遍历对象
		for (Color3 color3 : Color3.values()) {
			DealLog.log("[key=" + color3.name() + ",value=" + currEnumMap.get(color3) + "]");
		}
	}

	/**
	 * 演示EnumSet如何使用，EnumSet是一个抽象类，获取一个类型的枚举类型内容
	 */
	@Test
	public void testEnumSet() {
		EnumSet<Color3> currEnumSet = EnumSet.allOf(Color3.class);
		for (Color3 aLightSetElement : currEnumSet) {
			DealLog.log("当前EnumSet中数据为", aLightSetElement);
		}
	}

	/**
	 * 遍历枚举
	 */
	@Test
	public void testTraversalEnum() {
		Color3[] color3s = Color3.values();
		for (Color3 aLight : color3s) {
			DealLog.log("当前枚举名称", aLight.name());
			DealLog.log("当前枚举序号", aLight.ordinal());
			DealLog.log("当前枚举值", aLight);
		}
	}
}
