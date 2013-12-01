package com.cheetah.core.server.guice;

import net.customware.gwt.dispatch.server.guice.GuiceStandardDispatchServlet;

import com.cheetah.core.shared.Const;
import com.google.inject.servlet.ServletModule;

/**
 * Dispatch Configuration class of GuiceServlet
 * 
 * @author Kobe Sun
 * 
 */
public class DispatchServletModule extends ServletModule {
	@Override
	protected void configureServlets() {
		serve("/" + Const.CHEETAH + Const.SERVLET_DISPATCH).with(
				GuiceStandardDispatchServlet.class);
	}
}
