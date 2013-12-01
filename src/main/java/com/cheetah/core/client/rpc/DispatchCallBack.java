package com.cheetah.core.client.rpc;

import net.customware.gwt.presenter.client.Display;
import net.customware.gwt.presenter.client.EventBus;

import com.cheetah.core.client.events.ServerStatusEvent;
import com.cheetah.core.client.events.ServerStatusEvent.ServerStatus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

/**
 * AsyncCallback class used for GWT RPC communication
 * 
 * @author Kobe Sun
 * 
 * @param <T>
 *            result
 */

public abstract class DispatchCallBack<T> implements AsyncCallback<T> {

	private EventBus eventBus = null;
	private ServerStatusEvent available = new ServerStatusEvent(
			ServerStatus.Available);
	private ServerStatusEvent unavailable = new ServerStatusEvent(
			ServerStatus.Unavailable);

	@SuppressWarnings("unused")
	private Display display;

	@Inject
	public DispatchCallBack(EventBus eventBus, Display display) {
		this(eventBus);
		this.display = display;
	}

	@Inject
	public DispatchCallBack(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	/**
	 * If you override this method, remember to call super.onFailure()
	 */
	public void onFailure(final Throwable originalCaught) {
		// Server's response is invalid due to:
		// server unaccessible, session error or server exception
		eventBus.fireEvent(unavailable);
		callbackError(originalCaught);
	}

	/**
	 * If you override this method, remember to call super.onSuccess()
	 */
	public void onSuccess(T result) {
		// Server's response is valid,
		eventBus.fireEvent(available);
		// Execute the original method
		callback(result);
	}

	/**
	 * The callback code which the user has to implement
	 * 
	 * @param result
	 */
	public abstract void callback(T result);

	/**
	 * The callback code in the case of error Override this method, if you need
	 * this feature.
	 * 
	 * @param result
	 */
	public void callbackError(Throwable caught) {
		caught.printStackTrace();
		Window.alert("RPC failed: " + caught.toString());
	}
}
