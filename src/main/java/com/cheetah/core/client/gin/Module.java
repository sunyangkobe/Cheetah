package com.cheetah.core.client.gin;

import net.customware.gwt.presenter.client.DefaultEventBus;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.gin.AbstractPresenterModule;
import net.customware.gwt.presenter.client.place.ParameterTokenFormatter;
import net.customware.gwt.presenter.client.place.PlaceManager;
import net.customware.gwt.presenter.client.place.TokenFormatter;

import com.cheetah.core.client.mvp.BasePresenter;
import com.cheetah.core.client.mvp.BaseView;
import com.cheetah.core.client.mvp.LoginPresenter;
import com.cheetah.core.client.mvp.LoginView;
import com.cheetah.core.client.places.BasicPlaceManager;
import com.cheetah.core.client.rpc.CachingDispatchAsync;

/**
 * Dependency Injection binding Module. All possible use of classes should be
 * binded here, with dependency injection pattern.
 * 
 * @author Kobe Sun
 * 
 */

public class Module extends AbstractPresenterModule {

	@Override
	protected void configure() {

		// Mechanism components bindings
		bind(EventBus.class).to(DefaultEventBus.class);
		bind(TokenFormatter.class).to(ParameterTokenFormatter.class);
		bind(PlaceManager.class).to(BasicPlaceManager.class).asEagerSingleton();
		bind(CachingDispatchAsync.class);

		// MVP bindings
		bindPresenter(BasePresenter.class, BasePresenter.Display.class,
				BaseView.class);
		bindPresenter(LoginPresenter.class, LoginPresenter.Display.class,
				LoginView.class);
	}

}
