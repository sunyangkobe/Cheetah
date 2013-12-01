package com.cheetah.core.client.places;

import net.customware.gwt.presenter.client.gin.ProvidedPresenterPlace;

import com.cheetah.core.client.mvp.LoginPresenter;
import com.cheetah.core.client.mvp.LoginView;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * The place corresponds to {@link LoginPresenter} and {@link LoginView}
 * 
 * @author Kobe Sun
 * 
 */

public class LoginPlace extends ProvidedPresenterPlace<LoginPresenter> {

	public static final String NAME = "login";

	@Inject
	public LoginPlace(Provider<LoginPresenter> presenter) {
		super(presenter);
	}

	@Override
	public String getName() {
		return NAME;
	}

}
