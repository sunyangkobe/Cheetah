package com.cheetah.core.client.events;

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler of {@link LoginEvent} Implement this interface if presenter should
 * take actions on user login.
 * 
 * @author Kobe Sun
 * 
 */

public interface LoginHandler extends EventHandler {

	void onLogin(LoginEvent event);
}
