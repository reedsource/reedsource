package top.ireed.model;

/**
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 */
public class OneTable {
	/**
	 * 自增id
	 */
	private String id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 年龄
	 */
	private String age;

	public OneTable() {
	}

	public OneTable(String name, String age) {
		this.name = name;
		this.age = age;
	}

	/**
	 * id IN 后端拼接传入,数据库不存在
	 */
	private String typeIn;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getTypeIn() {
		return typeIn;
	}

	public void setTypeIn(String typeIn) {
		this.typeIn = typeIn;
	}

	@Override
	public String toString() {
		return "OneTable{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", age='" + age + '\'' +
				", typeIn='" + typeIn + '\'' +
				'}';
	}
}