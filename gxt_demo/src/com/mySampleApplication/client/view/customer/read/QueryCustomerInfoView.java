package com.mySampleApplication.client.view.customer.read;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mySampleApplication.client.MySampleApplicationService;
import com.mySampleApplication.client.dto.CustomerInfoQueryDTO;
import com.mySampleApplication.client.model.CustomerInfo;
import com.mySampleApplication.client.dto.CustomerInfoQuery;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.state.client.BorderLayoutStateHandler;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: nhsoft.xdj
 * @Description:
 * @Date:Create：2019/4/1 10:43
 * @Modified By：
 */
public class QueryCustomerInfoView {

	private BorderLayoutContainer borderLtWidget;

	private BorderLayoutStateHandler borderLtStateHandler;

	public BorderLayoutContainer getBorderLtWidget() {
		if(borderLtWidget == null ){
			// center部分
			ContentPanel cpCenter = new ContentPanel();
			cpCenter.add(new QueryCustomerInfoCenterView().getVerticalLtCenter());
			cpCenter.setHeaderVisible(false);
			cpCenter.setBodyBorder(false);
			// north部分
			ContentPanel cpNorth = new ContentPanel();
			cpNorth.setHeading("查询条件");
			cpNorth.setBodyBorder(false);
			// 查询条件
			final TextField tdCondition = new TextField();
			tdCondition.setWidth(200);
			FieldLabel flbCondition = new FieldLabel(tdCondition, "输入客户代码、助记符、名称");
			flbCondition.setLabelWidth(180);
			// 查询按钮
			TextButton btnRead = new TextButton("查询");
			btnRead.addSelectHandler(new SelectEvent.SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					CustomerInfoQuery request = new CustomerInfoQuery();
					if (tdCondition.getValue() != null && !tdCondition.getValue().isEmpty()){
						request.setCustomerCode(tdCondition.getValue());
						request.setMnemonicCode(tdCondition.getValue());
						request.setCustomerName(tdCondition.getValue());
					}
						MySampleApplicationService.App.getInstance().listCustomerInfo(request, new CustomerQueryAsyncCallback());
				}
			});
			btnRead.setWidth(60);
			HBoxLayoutContainer hboxLtNorth = new HBoxLayoutContainer();
			hboxLtNorth.add(flbCondition);
			hboxLtNorth.add(btnRead,new BoxLayoutContainer.BoxLayoutData(new Margins(0,0,0,10)));
			hboxLtNorth.setPosition(20,10);
			cpNorth.add(hboxLtNorth);
			// west部分
			ContentPanel cpWest = new ContentPanel();
			cpWest.setHeading("客户类型");
			cpWest.setBodyBorder(false);

			BorderLayoutContainer.BorderLayoutData borderLtNorthData = new BorderLayoutContainer.BorderLayoutData(100);
			borderLtNorthData.setCollapsible(true);
			borderLtNorthData.setCollapseHeaderVisible(true);
			borderLtNorthData.setSplit(false);

			BorderLayoutContainer.BorderLayoutData borderLtWestData = new BorderLayoutContainer.BorderLayoutData(150);
			borderLtWestData.setCollapsible(true);
			borderLtWestData.setSplit(false);

			BorderLayoutContainer.BorderLayoutData borderLtCenterData = new BorderLayoutContainer.BorderLayoutData(100);
			borderLtWidget =  new BorderLayoutContainer();
			borderLtWidget.setNorthWidget(cpNorth, borderLtNorthData);
			borderLtWidget.setWestWidget(cpWest, borderLtWestData);
			borderLtWidget.setCenterWidget(cpCenter, borderLtCenterData);
			borderLtStateHandler = new BorderLayoutStateHandler(borderLtWidget, "blcId1");
			borderLtWidget.setStateful(true);
		}
		borderLtStateHandler.loadState();
		return borderLtWidget;
	}

	private class CustomerQueryAsyncCallback implements AsyncCallback<List<CustomerInfoQueryDTO>> {

		@Override
		public void onFailure(Throwable caught) {
			Info.display("查询失败", caught.getMessage());
		}

		@Override
		public void onSuccess(List<CustomerInfoQueryDTO> result) {
			QueryCustomerInfoCenterView.store.clear();
			List<CustomerInfo> customerInfoList = new ArrayList<>();
			if(result != null && !result.isEmpty()){
				for (CustomerInfoQueryDTO rsp : result){
					CustomerInfo customerInfo = new CustomerInfo();
					customerInfo.setAddress(rsp.getAddress());
					customerInfo.setPhone(rsp.getPhone());
					customerInfo.setId(rsp.getId());
					customerInfo.setCustomerCode(rsp.getCustomerCode());
					customerInfo.setCustomerName(rsp.getCustomerName());
					customerInfo.setCustomerType(rsp.getCustomerType());
					customerInfo.setEmail(rsp.getEmail());
					customerInfo.setEnableTag(rsp.getEnableTag());
					customerInfo.setFax(rsp.getFax());
					customerInfo.setBirth(rsp.getBirth());
					customerInfo.setMnemonicCode(rsp.getMnemonicCode());
					customerInfo.setBank(rsp.getBank());
					customerInfo.setBankAcount(rsp.getBankAcount());
					customerInfo.setCompany(rsp.getCompany());
					customerInfo.setMonthlyDate(rsp.getMonthlyDate());
					customerInfo.setPostCode(rsp.getPostCode());
					customerInfo.setRemark(rsp.getRemark());
					customerInfo.setSettlementDate(rsp.getSettlementDate());
					customerInfo.setSettlementMethod(rsp.getSettlementMethod());
					customerInfoList.add(customerInfo);
				}
			}
			QueryCustomerInfoCenterView.store.addAll(customerInfoList);
			//Info.display("查询成功", result == null ? "查询无数据" : result.toString());
		}
	}
}
