package com.cheetah.core.client;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.place.PlaceRequestEvent;
import net.customware.gwt.presenter.client.place.PlaceRequestHandler;

import com.allen_sauer.gwt.log.client.Log;
import com.cheetah.core.client.events.LoginEvent;
import com.cheetah.core.client.events.LoginHandler;
import com.cheetah.core.client.gin.Injector;
import com.cheetah.core.client.mvp.BasePresenter;
import com.cheetah.core.client.places.LoginPlace;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 * @author Kobe Sun
 */
public class Cheetah implements EntryPoint {

	/**
	 * This is the entry point method.
	 */

	public void onModuleLoad() {
		// Create singleton Injector instance and log for debug
		final Injector injector = GWT.create(Injector.class);
		logEvent(injector.getEventBus());

		// Prepare the first presenter
		final BasePresenter basePresenter = injector.getBasePresenter();
		basePresenter.bind(); // Call bind() to register handlers

		// Prepare the UI
		RootPanel.get().add(basePresenter.getDisplay().asWidget());

		// Call to change place and invoke corresponding presenter
		injector.getEventBus().fireEvent(
				new PlaceRequestEvent(new PlaceRequest(LoginPlace.NAME)));

	}

	private final void logEvent(EventBus eventBus) {
		// HashMap<EventType, Handler> is necessary if there are more events.
		eventBus.addHandler(PlaceRequestEvent.getType(),
				new PlaceRequestHandler() {
					@Override
					public void onPlaceRequest(PlaceRequestEvent event) {
						Log.debug("[Cheetah]: " + event.toDebugString()
								+ event.getRequest().getName());
					}
				});

		eventBus.addHandler(LoginEvent.getType(), new LoginHandler() {
			@Override
			public void onLogin(LoginEvent event) {
				Log.debug("[Cheetah]: " + event.toDebugString());
			}
		});
	}
}
