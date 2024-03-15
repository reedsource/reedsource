package top.ireed.modal;


public class User {
	private String name;
	private String address;
	private Integer age;
	private Integer sex;

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public User() {
	}

	public User(String name, String address, Integer age, Integer sex) {

		this.name = name;
		this.address = address;
		this.age = age;
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", address='" + address + '\'' +
				", age=" + age +
				", sex=" + sex +
				'}';
	}
}
