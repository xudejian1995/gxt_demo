package com.mySampleApplication.client.dto;

import java.io.Serializable;

/**
 * @Author: nhsoft.xdj
 * @Description:
 * @Date:Create：2019/4/2 11:44
 * @Modified By：
 */
public class CustomerInfoQuery implements Serializable {
	private String customerCode;// 客户代码
	private String customerName;// 客户名称
	private String mnemonicCode;// 助记码

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMnemonicCode() {
		return mnemonicCode;
	}

	public void setMnemonicCode(String mnemonicCode) {
		this.mnemonicCode = mnemonicCode;
	}

	@Override
	public String toString() {
		return "CustomerInfoQuery{" +
				"customerCode='" + customerCode + '\'' +
				", customerName='" + customerName + '\'' +
				", mnemonicCode='" + mnemonicCode + '\'' +
				'}';
	}
}
