package com.cheetah.core.client.gin;

import net.customware.gwt.dispatch.client.gin.StandardDispatchModule;
import net.customware.gwt.presenter.client.EventBus;

import com.cheetah.core.client.mvp.BasePresenter;
import com.cheetah.core.client.rpc.CachingDispatchAsync;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

/**
 * Dependency Injection interface
 * 
 * @author Kobe Sun
 * 
 */

@GinModules({ Module.class, StandardDispatchModule.class })
public interface Injector extends Ginjector {

	EventBus getEventBus();

	BasePresenter getBasePresenter();

	// temporarily put it here in case of calling login service in advance
	// remove it if not needed.
	CachingDispatchAsync getDispatchAsync();

}
