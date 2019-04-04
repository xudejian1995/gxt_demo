package com.mySampleApplication.server.service;

import com.mySampleApplication.client.dto.*;

import java.util.List;

/**
 * @Author: nhsoft.xdj
 * @Description:
 * @Date:Create：2019/3/29 11:43
 * @Modified By：
 */
public interface CustomerService {
	String deleteCustomerInfo(CustomerInfoDelete request);

	String updateCustomerInfo(CustomerInfoUpdate customerInfo);

	CustomerInfoSaveDTO saveCustomerInfo(CustomerInfoSave request);

	List<CustomerInfoQueryDTO> listCustomerInfo(CustomerInfoQuery request);
}
