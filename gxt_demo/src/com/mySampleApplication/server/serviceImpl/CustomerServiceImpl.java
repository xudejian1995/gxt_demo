package com.mySampleApplication.server.serviceImpl;

import com.mySampleApplication.client.dto.*;
import com.mySampleApplication.server.dao.CustomerDao;
import com.mySampleApplication.server.model.CustomerInfo;
import com.mySampleApplication.server.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: nhsoft.xdj
 * @Description:
 * @Date:Create：2019/3/29 11:45
 * @Modified By：
 */
@Transactional
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao customerDao;

	@Override
	public String deleteCustomerInfo(CustomerInfoDelete request) {
		List<Integer> ids = request.getIds();
		customerDao.deleteByPrimarykey(ids);
		return "success";
	}

	@Override
	public String updateCustomerInfo(CustomerInfoUpdate request) {
		CustomerInfo customerInfo = new CustomerInfo();
		BeanUtils.copyProperties(request, customerInfo);
		customerDao.updateByPrimaryKey(customerInfo);
		return "success";
	}

	@Override
	public CustomerInfoSaveDTO saveCustomerInfo(CustomerInfoSave request) {
		CustomerInfo customerInfo = new CustomerInfo();
		BeanUtils.copyProperties(request, customerInfo);
		CustomerInfoSaveDTO response = new CustomerInfoSaveDTO();
		BeanUtils.copyProperties(customerDao.save(customerInfo), response);
		return response;
	}

	@Override
	public List<CustomerInfoQueryDTO> listCustomerInfo(CustomerInfoQuery request) {
		CustomerInfo customerInfo = new CustomerInfo();
		BeanUtils.copyProperties(request, customerInfo);
		List<CustomerInfoQueryDTO> response = new ArrayList<>();
		List<CustomerInfo> customerInfoList = customerDao.listByCustomerInfoOr(customerInfo);
		if (customerInfoList != null && !customerInfoList.isEmpty()){
			for (CustomerInfo tmp : customerInfoList){
				CustomerInfoQueryDTO customerInfoQueryDTO = new CustomerInfoQueryDTO();
				BeanUtils.copyProperties(tmp, customerInfoQueryDTO);
				response.add(customerInfoQueryDTO);
			}
			return response;
		} else {
			return null;
		}
	}
}
