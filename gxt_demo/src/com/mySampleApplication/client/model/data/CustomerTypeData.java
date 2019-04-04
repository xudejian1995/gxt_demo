package com.mySampleApplication.client.model.data;

import com.mySampleApplication.client.model.CustomerType;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: nhsoft.xdj
 * @Description:
 * @Date:Create：2019/4/3 17:50
 * @Modified By：
 */
public class CustomerTypeData {
	public static List<CustomerType> getCustomerTypes(){
		List<CustomerType> customerTypes = new ArrayList<>();
		customerTypes.add(new CustomerType("普通客户"));
		customerTypes.add(new CustomerType("VIP客户"));
		return customerTypes;
	}
}
