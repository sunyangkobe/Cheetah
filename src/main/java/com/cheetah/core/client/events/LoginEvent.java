package com.cheetah.core.client.events;

import com.google.gwt.event.shared.GwtEvent;

/**
 * This event should get fired on user login.
 * 
 * @author Kobe Sun
 * 
 */

public class LoginEvent extends GwtEvent<LoginHandler> {

	private static Type<LoginHandler> TYPE;

	public static Type<LoginHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<LoginHandler>());
	}

	public LoginEvent() {
	}

	@Override
	public final Type<LoginHandler> getAssociatedType() {
		return getType();
	}

	@Override
	protected void dispatch(LoginHandler handler) {
		handler.onLogin(this);
	}
}
