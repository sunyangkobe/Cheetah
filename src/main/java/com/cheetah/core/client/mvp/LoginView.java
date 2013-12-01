package com.cheetah.core.client.mvp;

import com.cheetah.core.client.messages.AppMessages;
import com.cheetah.core.client.places.LoginPlace;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Layout;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;

/**
 * This class contains all UI stuff in {@link LoginPlace}, correspond to
 * {@link LoginPresenter}
 * 
 * @author Kobe Sun
 * 
 */

public class LoginView extends Dialog implements LoginPresenter.Display {

	private final TextField<String> userName;
	private final TextField<String> passWord;
	private final FormLayout formLayout;
	private CenterLayout centerLayout;

	public LoginView() {
		formLayout = new FormLayout();
		formLayout.setLabelWidth(90);
		formLayout.setDefaultWidth(155);

		userName = new TextField<String>();
		userName.setMinLength(4);
		userName.setFieldLabel(AppMessages.INSTANCE.Username());

		passWord = new TextField<String>();
		passWord.setMinLength(4);
		passWord.setPassword(true);
		passWord.setFieldLabel(AppMessages.INSTANCE.Password());

		setLayout(formLayout);
		setHeading(AppMessages.INSTANCE.Login());
		setBodyBorder(true);
		setBodyStyle("padding: 8px;background: none");
		setWidth(300);
		setResizable(false);
		setClosable(false);
		
		setButtons(Dialog.OKCANCEL);
		getButtonById(Dialog.OK).setText(AppMessages.INSTANCE.Login());
		getButtonById(Dialog.OK).setToolTip(AppMessages.INSTANCE.Login());
		getButtonById(Dialog.CANCEL).setText(AppMessages.INSTANCE.Reset());
		getButtonById(Dialog.CANCEL).setToolTip(AppMessages.INSTANCE.Reset());

		add(userName);
		add(passWord);

		setFocusWidget(userName);
	}

	@Override
	public final TextField<String> getUserField() {
		return userName;
	}

	@Override
	public TextField<String> getPWDField() {
		return passWord;
	}

	@Override
	public Layout RequestBaseLayout() {
		if (centerLayout == null)
			centerLayout = new CenterLayout();
		return centerLayout;
	}

	@Override
	public Button getLoginButton() {
		return getButtonById(Dialog.OK);
	}

	public Button getResetButton() {
		return getButtonById(Dialog.CANCEL);

	}
}
