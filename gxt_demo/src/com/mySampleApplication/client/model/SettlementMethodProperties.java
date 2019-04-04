package com.mySampleApplication.client.model;

import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * @Author: nhsoft.xdj
 * @Description:
 * @Date:Create：2019/4/3 12:08
 * @Modified By：
 */
public interface SettlementMethodProperties extends PropertyAccess<SettlementMethod> {
	@Editor.Path("name")
	ModelKeyProvider<SettlementMethod> key();
	@Editor.Path("name")
	LabelProvider<SettlementMethod> nameLabel();
	ValueProvider<SettlementMethod, String> name();
}
