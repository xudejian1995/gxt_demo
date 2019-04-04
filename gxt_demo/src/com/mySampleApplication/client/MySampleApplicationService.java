package com.mySampleApplication.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mySampleApplication.client.dto.*;
import com.mySampleApplication.client.model.CustomerInfo;

import java.util.List;

@RemoteServiceRelativePath("MySampleApplicationService")
public interface MySampleApplicationService extends RemoteService {
	String deleteCustomerInfo(CustomerInfoDelete request);

	String updateCustomerInfo(CustomerInfoUpdate request);

	List<CustomerInfoQueryDTO> listCustomerInfo(CustomerInfoQuery request);

	CustomerInfoSaveDTO saveCustomerInfo(CustomerInfoSave request);


	/**
	 * Utility/Convenience class.
	 * Use MySampleApplicationService.App.getInstance() to access static instance of MySampleApplicationServiceAsync
	 */
	public static class App {
		private static MySampleApplicationServiceAsync ourInstance = GWT.create(MySampleApplicationService.class);

		public static synchronized MySampleApplicationServiceAsync getInstance() {
			return ourInstance;
		}
	}
}
