package com.cheetah.core.client.messages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

/**
 * Internationalization Interface Function name has to be the same as that in
 * java properties
 * 
 * @author Kobe Sun
 * 
 */
public interface AppMessages extends Messages {

	// This must be a singleton instance.
	static final AppMessages INSTANCE = GWT.create(AppMessages.class);

	@DefaultMessage("Login")
	String Login();

	@DefaultMessage("Username")
	String Username();

	@DefaultMessage("Password")
	String Password();

	@DefaultMessage("Reset")
	String Reset();

}
