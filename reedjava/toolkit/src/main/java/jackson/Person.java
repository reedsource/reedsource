/*
 * FileName: Person
 * Author:   reedsource
 */
package jackson;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/19 16:58
 * reedsource@189.cn
 */
public class Person {
	private String name;
	private int age;
	private String site;

	public Person() {
	}

	Person(String name, int age, String site) {
		this.name = name;
		this.age = age;
		this.site = site;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", age=" + age +
				", site='" + site + '\'' +
				'}';
	}
}
