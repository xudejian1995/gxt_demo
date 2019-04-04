package com.mySampleApplication.client.view.customer.save;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mySampleApplication.client.MySampleApplication;
import com.mySampleApplication.client.MySampleApplicationService;
import com.mySampleApplication.client.dto.CustomerInfoSave;
import com.mySampleApplication.client.dto.CustomerInfoSaveDTO;
import com.mySampleApplication.client.model.CustomerType;
import com.mySampleApplication.client.model.CustomerTypeProperties;
import com.mySampleApplication.client.model.SettlementMethod;
import com.mySampleApplication.client.model.SettlementMethodProperties;
import com.mySampleApplication.client.model.data.CustomerTypeData;
import com.mySampleApplication.client.model.data.SettlementMethodData;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.*;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.*;
import com.sencha.gxt.widget.core.client.form.validator.MaxDateValidator;
import com.sencha.gxt.widget.core.client.form.validator.MinDateValidator;
import com.sencha.gxt.widget.core.client.info.Info;

import java.util.Date;

/**
 * @Author: nhsoft.xdj
 * @Description:
 * @Date:Create：2019/3/27 19:48
 * @Modified By：
 */
public class SaveCustomerInfoView {
	private VerticalLayoutContainer verticalLtWidget;
	final TextField tdCustomerCode = new TextField(); // 客户代码
	final TextField tdCustomerName = new TextField(); // 客户名称
	final TextField tdMnemonicCode = new TextField(); // 助记码
	// 客户类型下拉框值
	CustomerTypeProperties customerTypeProperties = GWT.create(CustomerTypeProperties.class);
	ListStore<CustomerType> listStoreCustomerType = new ListStore<>(customerTypeProperties.key());
	ComboBox<CustomerType> cbbCustomerType = new ComboBox<>(listStoreCustomerType,customerTypeProperties.nameLabel()); // 客户类型
	final TextField tdPhone = new TextField(); // 电话
	final TextField tdFax= new TextField(); // 传真
	final TextField tdEmail = new TextField(); // 邮箱
	final TextField tdAddress = new TextField(); // 联系地址
	CheckBox chkEnableTag = new CheckBox();
	final TextField tdCompany = new TextField(); // 工作单位
	final DateTimeWidget dtBirth = new DateTimeWidget(); // 出生日期
	final TextField tdPostCode = new TextField(); // 邮编
	final TextField tdBankAcount = new TextField();// 银行账号
	final TextField tdBank = new TextField(); // 银行
	// 结算方式
	SettlementMethodProperties settlementMethodProperties = GWT.create(SettlementMethodProperties.class);
	ListStore<SettlementMethod> listStoreSettlementMethod = new ListStore<>(settlementMethodProperties.key());
	ComboBox<SettlementMethod> cbbSettlementMethod = new ComboBox<SettlementMethod>(listStoreSettlementMethod, settlementMethodProperties.nameLabel());
	final DateTimeWidget dtSettlementDate = new DateTimeWidget(); // 结算日期
	final TextField tdMonthlyDate = new TextField(); // 月结日期
	TextArea taRemark = new TextArea(); // 备注

	public VerticalLayoutContainer getVerticalLtWidget() {
		if (verticalLtWidget == null){
			verticalLtWidget = new VerticalLayoutContainer();
			// 基础信息部分
			ContentPanel cpBasicInfo =  createCustomerBasicInfoFields();
			// 详细信息部分
			ContentPanel cpDetailInfo =  createCustomerDetailInfoFields();
			// 顶部按钮
			TextButton buttonSave = new TextButton("保存");
			buttonSave.setSize("100","30");
			buttonSave.addSelectHandler(new SelectEvent.SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					CustomerInfoSave request = validate();
					if (request != null){
						MySampleApplicationService.App.getInstance().saveCustomerInfo(request,new CustomerSaveAsyncCallback());
					}
				}
			});
			TextButton buttonBack = new TextButton("返回");
			buttonBack.setSize("100","30");
			buttonBack.addSelectHandler(new SelectEvent.SelectHandler() {

				@Override
				public void onSelect(SelectEvent event) {
					MySampleApplication.back();
				}
			});
			// 按钮需要平行布局
			HBoxLayoutContainer hboxLtBtn = new HBoxLayoutContainer();
			hboxLtBtn.add(buttonSave,new BoxLayoutContainer.BoxLayoutData(new Margins(10, 0, 0, 0)));
			hboxLtBtn.add(buttonBack,new BoxLayoutContainer.BoxLayoutData(new Margins(10, 0, 0, 50)));
			verticalLtWidget.add(hboxLtBtn,new VerticalLayoutContainer.VerticalLayoutData(500,50,new Margins(0, 0, 0, 20)));
			verticalLtWidget.add(cpBasicInfo);
			verticalLtWidget.add(cpDetailInfo);
		}
		return verticalLtWidget;
	}

	/**
	 * 日期控件
	 */
	public class DateTimeWidget extends Composite {
		private DateField dateField;

		public DateTimeWidget() {
			dateField = new DateField();
			dateField.setWidth(178);
			HBoxLayoutContainer hbokLtDate = new HBoxLayoutContainer(HBoxLayoutContainer.HBoxLayoutAlign.MIDDLE);
			hbokLtDate.add(dateField, new BoxLayoutContainer.BoxLayoutData(new Margins(0, 20, 0, 0)));
			initWidget(hbokLtDate);
		}

		public DateField getDateField() {
			return dateField;
		}

		public void setDateField(DateField dateField) {
			this.dateField = dateField;
		}
	}

	private static class CustomerAddAsyncCallback implements AsyncCallback<String> {


		CustomerAddAsyncCallback() {
		}

		@Override
		public void onFailure(Throwable caught) {
			Info.display("新建失败", caught.getMessage());
		}

		@Override
		public void onSuccess(String result) {
			Info.display("新建成功", result);
		}
	}

	private ContentPanel createCustomerBasicInfoFields(){
		// 基本信息
		tdCustomerCode.setWidth(178);
		tdCustomerName.setWidth(178);
		tdMnemonicCode.setWidth(178);
		listStoreCustomerType.addAll(CustomerTypeData.getCustomerTypes());
		cbbCustomerType.setWidth(178);
		tdPhone.setWidth(178);
		tdFax.setWidth(178);
		tdEmail.setWidth(178);
		tdAddress.setWidth(178);
		chkEnableTag.setBoxLabel("启用标记");

		ContentPanel cpBasicInfo =  new ContentPanel();
		cpBasicInfo.setHeading("基本信息");
		cpBasicInfo.setHeight(230);
		HorizontalLayoutContainer horizonalLt = new HorizontalLayoutContainer();
		// 左侧
		VerticalLayoutContainer verticalLtLeft = new VerticalLayoutContainer();

		// 右侧
		VerticalLayoutContainer verticalLtRight = new VerticalLayoutContainer();
		FieldLabel flbCustomerCode = new FieldLabel(tdCustomerCode, "客户代码");
		FieldLabel flbCustomerName = new FieldLabel(tdCustomerName, "客户名称");
		FieldLabel flbMnemonicCode = new FieldLabel(tdMnemonicCode, "助记码");
		FieldLabel flbCustomerType = new FieldLabel(cbbCustomerType, "客户类型");
		FieldLabel flbPhone = new FieldLabel(tdPhone, "电话");
		FieldLabel flbFax = new FieldLabel(tdFax, "传真");
		FieldLabel flbEmail = new FieldLabel(tdEmail, "邮箱");
		FieldLabel flbAddress = new FieldLabel(tdAddress, "联系地址");
		FieldLabel fldEnableTag = new FieldLabel(chkEnableTag, "启用标记");
		verticalLtLeft.add(flbCustomerCode);
		verticalLtLeft.add(flbMnemonicCode);
		verticalLtLeft.add(flbPhone);
		verticalLtLeft.add(flbEmail);
		verticalLtLeft.add(fldEnableTag);
		verticalLtRight.add(flbCustomerName);
		verticalLtRight.add(flbCustomerType);
		verticalLtRight.add(flbFax);
		verticalLtRight.add(flbAddress);
		horizonalLt.add(verticalLtLeft,new HorizontalLayoutContainer.HorizontalLayoutData(-1,1,new Margins(0,0,0,20)));
		horizonalLt.add(verticalLtRight,new HorizontalLayoutContainer.HorizontalLayoutData(-1,1,new Margins(0,0,0,20)));
		cpBasicInfo.add(horizonalLt,new MarginData(20,0,0,20));
		return cpBasicInfo;
	}

	private ContentPanel createCustomerDetailInfoFields(){
		// 详细信息
		tdCompany.setWidth(178);
		tdPostCode.setWidth(178);
		tdBankAcount.setWidth(178);
		tdBank.setWidth(178);
		listStoreSettlementMethod.addAll(SettlementMethodData.getSettlementMethods());
		cbbSettlementMethod.setWidth(178);
		tdMonthlyDate.setWidth(178);
		taRemark.setSize("200", "120");

		ContentPanel cpBasicInfo =  new ContentPanel();
		cpBasicInfo.setHeight(350);
		cpBasicInfo.setHeading("详细信息");
		HorizontalPanel hrtPanel = new HorizontalPanel();
		HorizontalLayoutContainer horizonalLt = new HorizontalLayoutContainer();
		// 左侧
		VerticalPanel verticalPanelLeft = new VerticalPanel();
		VerticalLayoutContainer verticalLtLeft = new VerticalLayoutContainer();
		// 右侧
		VerticalPanel verticalPanelRight = new VerticalPanel();
		VerticalLayoutContainer verticalLtRight = new VerticalLayoutContainer();
		FieldLabel flbCompany = new FieldLabel(tdCompany, "工作单位");
		FieldLabel flbBirth = new FieldLabel(dtBirth, "出生日期");
		FieldLabel flbPostCode = new FieldLabel(tdPostCode, "邮编");
		FieldLabel flbBankAcount = new FieldLabel(tdBankAcount, "银行账号");
		FieldLabel flbBank = new FieldLabel(tdBank, "银行");
		FieldLabel flbSettlementMethod = new FieldLabel(cbbSettlementMethod, "结算方式");
		FieldLabel flbSettlementDate = new FieldLabel(dtSettlementDate, "结算日期");
		FieldLabel flbMonthlyDate = new FieldLabel(tdMonthlyDate, "月结日期");
		FieldLabel flbRemark = new FieldLabel(taRemark, "备注");
		verticalLtLeft.add(flbCompany);
		verticalLtLeft.add(flbPostCode);
		verticalLtLeft.add(flbBank);
		verticalLtLeft.add(flbSettlementDate);
		verticalLtLeft.add(flbRemark);
		verticalLtRight.add(flbBirth);
		verticalLtRight.add(flbBankAcount);
		verticalLtRight.add(flbSettlementMethod);
		verticalLtRight.add(flbMonthlyDate);
		horizonalLt.add(verticalLtLeft,new HorizontalLayoutContainer.HorizontalLayoutData(-1,1,new Margins(0,0,0,20)));
		horizonalLt.add(verticalLtRight,new HorizontalLayoutContainer.HorizontalLayoutData(-1,1,new Margins(0,0,0,0)));
		cpBasicInfo.add(horizonalLt,new MarginData(20,0,0,20));
		return cpBasicInfo;
	}

	private CustomerInfoSave validate(){
		CustomerInfoSave request = new CustomerInfoSave();
		if (tdCustomerCode.getValue() == null || tdCustomerCode.getValue().isEmpty()){
			Info.display("提示", "请填写客户代码");
			return null;
		} else {
			request.setCustomerCode(tdCustomerCode.getValue());
		}
		if (tdCustomerName.getValue() == null || tdCustomerName.getValue().isEmpty()){
			Info.display("提示", "请填写客户名称");
			return null;
		} else {
			request.setCustomerName(tdCustomerName.getValue());
		}
		if (tdMnemonicCode.getValue() == null || tdMnemonicCode.getValue().isEmpty()){
			Info.display("提示", "请填写助记码");
			return null;
		} else {
			request.setMnemonicCode(tdMnemonicCode.getValue());
		}
		if (cbbCustomerType.getValue() == null || cbbCustomerType.getValue().getName() == null || cbbCustomerType.getValue().getName().isEmpty()){
			Info.display("提示", "请填写客户类型");
			return null;
		} else {
			request.setCustomerType(cbbCustomerType.getValue().getName());
		}
		if (tdPhone.getValue() == null || tdPhone.getValue().isEmpty()){
			Info.display("提示", "请填写电话");
			return null;
		} else {
			request.setPhone(tdPhone.getValue());
		}
		if (tdAddress.getValue() == null || tdAddress.getValue().isEmpty()){
			Info.display("提示", "请填写联系地址");
			return null;
		} else {
			request.setAddress(tdAddress.getValue());
		}
		if (tdCompany.getValue() == null || tdCompany.getValue().isEmpty()){
			Info.display("提示", "请填写工作单位");
			return null;
		} else {
			request.setCompany(tdCompany.getValue());
		}
		if (tdBankAcount.getValue() == null || tdBankAcount.getValue().isEmpty()){
			Info.display("提示", "请填写银行账号");
			return null;
		} else {
			request.setBankAcount(tdBankAcount.getValue());
		}
		if (tdBank.getValue() == null || tdBank.getValue().isEmpty()){
			Info.display("提示", "请填写银行");
			return null;
		} else {
			request.setBank(tdBank.getValue());
		}
		if (cbbSettlementMethod.getValue() == null || cbbSettlementMethod.getValue().getName() == null || cbbSettlementMethod.getValue().getName().isEmpty()){
			Info.display("提示", "请填写结算方式");
			return null;
		} else {
			request.setSettlementMethod(cbbSettlementMethod.getValue().getName());
		}
		request.setEnableTag(chkEnableTag.getValue());
		request.setFax(tdFax.getValue());
		request.setEmail(tdEmail.getValue());
		request.setBirth(dtBirth.getDateField().getValue());
		request.setPostCode(tdPostCode.getValue());
		request.setSettlementDate(dtSettlementDate.getDateField().getValue());
		request.setMonthlyDate(tdMonthlyDate.getValue());
		request.setRemark(taRemark.getValue());
		return request;
	}

	private class CustomerSaveAsyncCallback implements AsyncCallback<CustomerInfoSaveDTO>{

		@Override
		public void onFailure(Throwable caught) {
			Info.display("提示", caught.getMessage());
		}

		@Override
		public void onSuccess(CustomerInfoSaveDTO result) {
			if (result != null){
				Info.display("提示", "新增成功");
			}
		}
	}
}
