package com.mySampleApplication.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.mySampleApplication.client.view.customer.save.SaveCustomerInfoView;
import com.mySampleApplication.client.view.customer.read.QueryCustomerInfoView;
import com.mySampleApplication.client.view.customer.update.UpdateCustomerInfoView;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.CardLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MySampleApplication implements EntryPoint {
	static CardLayoutContainer cardLayout ;
	private ContentPanel panel;
	static VerticalLayoutContainer verticalLtSaveCustomerInfoView;
	static VerticalLayoutContainer verticalLtUpdateCustomerInfoView;
	static BorderLayoutContainer borderLtQueryCustomerInfoView;
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		/*final Button button = new Button("Click me");
		final Label label = new Label();

		button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (label.getText().equals("")) {
					MySampleApplicationService.App.getInstance().getMessage( "Hello, World!", new MyAsyncCallback(label));
				}
				else {
					label.setText("");
				}
			}
		});
		RootPanel.get("slot1").save(button);
		RootPanel.get("slot2").save(label);*/

		cardLayout = new CardLayoutContainer();
		borderLtQueryCustomerInfoView = new QueryCustomerInfoView().getBorderLtWidget();
		verticalLtSaveCustomerInfoView = new SaveCustomerInfoView().getVerticalLtWidget();
		verticalLtUpdateCustomerInfoView = new UpdateCustomerInfoView().getVerticalLtWidget();
		cardLayout.add(borderLtQueryCustomerInfoView);
		cardLayout.add(verticalLtSaveCustomerInfoView);
		cardLayout.add(verticalLtUpdateCustomerInfoView);
		cardLayout.setHeight(700);
		TabPanel panel = new TabPanel();
		panel.add(cardLayout, new TabItemConfig("客户信息维护"));
		RootPanel.get().add(panel);
	}

	/*private static class MyAsyncCallback implements AsyncCallback<String> {
		private Label label;
		MyAsyncCallback(Label label) {
			this.label = label;
		}

		public void onSuccess(String result) {
			label.getElement().setInnerHTML(result);
		}

		public void onFailure(Throwable throwable) {
			label.setText("Failed to receive answer from server!");
		}
	}*/
	public static void back(){
		cardLayout.setActiveWidget(borderLtQueryCustomerInfoView);
	}
	public static void save(){
		cardLayout.setActiveWidget(verticalLtSaveCustomerInfoView);
	}
	public static void update(){
		cardLayout.setActiveWidget(verticalLtUpdateCustomerInfoView);
	}
}
