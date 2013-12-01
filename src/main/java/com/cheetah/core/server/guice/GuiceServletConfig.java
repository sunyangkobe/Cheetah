package com.cheetah.core.server.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * Configuration class of GuiceServlet
 * 
 * @author Kobe Sun
 * 
 */
public class GuiceServletConfig extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new GuiceServerHandlerModule(),
				new DispatchServletModule());
	}

}
