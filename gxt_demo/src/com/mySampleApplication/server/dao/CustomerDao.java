package com.mySampleApplication.server.dao;

import com.mySampleApplication.server.model.CustomerInfo;

import java.util.List;

/**
 * @Author: nhsoft.xdj
 * @Description:
 * @Date:Create：2019/3/29 16:05
 * @Modified By：
 */
public interface CustomerDao {
	public void deleteByPrimarykey(List<Integer> ids);

	public void updateByPrimaryKey(CustomerInfo customerInfo);

	CustomerInfo save(CustomerInfo customerInfo);

	/**
	 * 客户代码or客户名称or助记符
	 * @param customerInfo
	 * @return
	 */
	List<CustomerInfo> listByCustomerInfoOr(CustomerInfo customerInfo);

}
