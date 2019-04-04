package com.mySampleApplication.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mySampleApplication.client.MySampleApplicationService;
import com.mySampleApplication.client.dto.*;
import com.mySampleApplication.server.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.util.List;

public class MySampleApplicationServiceImpl extends RemoteServiceServlet implements MySampleApplicationService {
	private ApplicationContext applicationContext;
	private CustomerService customerService;

	@Override
	public void init(ServletConfig Config) throws ServletException {
		super.init(Config);
		applicationContext = new FileSystemXmlApplicationContext("WEB-INF/spring/applicationContext.xml");
		if (applicationContext == null) {
			throw new RuntimeException("Check Your Web.Xml Setting, No Spring Context Configured");
		} else {
			customerService = applicationContext.getBean(CustomerService.class);
		}
	}

	@Override
	public String deleteCustomerInfo(CustomerInfoDelete request) {
		customerService.deleteCustomerInfo(request);
		return "success";
	}

	@Override
	public String updateCustomerInfo(CustomerInfoUpdate request) {
		return customerService.updateCustomerInfo(request);
	}

	@Override
	public List<CustomerInfoQueryDTO> listCustomerInfo(CustomerInfoQuery request) {
		return customerService.listCustomerInfo(request);
	}

	@Override
	public CustomerInfoSaveDTO saveCustomerInfo(CustomerInfoSave request) {
		return customerService.saveCustomerInfo(request);
	}

}