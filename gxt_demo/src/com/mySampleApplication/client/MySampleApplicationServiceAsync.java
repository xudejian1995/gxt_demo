package com.mySampleApplication.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mySampleApplication.client.dto.*;
import com.mySampleApplication.client.model.CustomerInfo;

import java.util.List;

public interface MySampleApplicationServiceAsync {
	void deleteCustomerInfo(CustomerInfoDelete request, AsyncCallback<String> async);

	void updateCustomerInfo(CustomerInfoUpdate request, AsyncCallback<String> async);

	void listCustomerInfo(CustomerInfoQuery request, AsyncCallback<List<CustomerInfoQueryDTO>> async);

	void saveCustomerInfo(CustomerInfoSave request, AsyncCallback<CustomerInfoSaveDTO> async);
}
