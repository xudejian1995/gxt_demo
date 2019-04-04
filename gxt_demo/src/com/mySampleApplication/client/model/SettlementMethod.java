package com.mySampleApplication.client.model;

/**
 * @Author: nhsoft.xdj
 * @Description:
 * @Date:Create：2019/4/3 12:05
 * @Modified By：
 */
public class SettlementMethod {
	private String name;

	public SettlementMethod() {
	}

	public SettlementMethod(String name) {
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
		return "SettlementMethod{" +
				"name='" + name + '\'' +
				'}';
	}
}
