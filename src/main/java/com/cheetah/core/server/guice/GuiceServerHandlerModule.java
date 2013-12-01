package com.cheetah.core.server.guice;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;

import org.apache.commons.logging.Log;

import com.cheetah.core.server.handler.LoginHandler;
import com.cheetah.core.shared.rpc.LoginAction;
import com.google.inject.Singleton;

/**
 * Guice Dependency Injection binding Module. All possible handlers should be
 * binded here, with dependency injection pattern.
 * 
 * @author Kobe Sun
 * 
 */
public class GuiceServerHandlerModule extends ActionHandlerModule {
	@Override
	protected void configureHandlers() {
		bind(Log.class).toProvider(LogProvider.class).in(Singleton.class);
		bindHandler(LoginAction.class, LoginHandler.class);
	}

}
