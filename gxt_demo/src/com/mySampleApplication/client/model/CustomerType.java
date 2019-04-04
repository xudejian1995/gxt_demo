package com.mySampleApplication.client.model;

/**
 * @Author: nhsoft.xdj
 * @Description:
 * @Date:Create：2019/4/3 17:48
 * @Modified By：
 */
public class CustomerType {
	private String name;

	public CustomerType() {
	}

	public CustomerType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CustomerType{" +
				"name='" + name + '\'' +
				'}';
	}
}
