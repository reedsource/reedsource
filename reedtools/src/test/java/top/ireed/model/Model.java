/*
 * FileName: Model
 * Author:   reedsource
 */
package top.ireed.model;

import java.util.Date;

/**
 * 测试实体类
 */
public class Model extends DataBase{

	private String id;
	private String key;
	private String value;
	private Date date;



	public Model() {
	}

	public Model(String key, String value, Date date) {
		this.key = key;
		this.value = value;
		this.date = date;
	}

	public Model(String id, String key, String value, Date date) {
		this.id = id;
		this.key = key;
		this.value = value;
		this.date = date;
	}

	public Model(String id, String key, String value, Date date, String suS) {
		this.id = id;
		this.key = key;
		this.value = value;
		this.date = date;
		super.setSuS(suS);
	}

	public Model(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public Model(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Model{" +
				"id='" + id + '\'' +
				", key='" + key + '\'' +
				", value='" + value + '\'' +
				", date='" + date + '\'' +
				'}';
	}
}
