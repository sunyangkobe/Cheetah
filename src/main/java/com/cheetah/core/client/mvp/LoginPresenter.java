package com.cheetah.core.client.mvp;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.allen_sauer.gwt.log.client.Log;
import com.cheetah.core.client.events.LoginEvent;
import com.cheetah.core.client.events.LoginHandler;
import com.cheetah.core.client.places.LoginPlace;
import com.cheetah.core.client.prototype.BaseWidgetDisplay;
import com.cheetah.core.client.rpc.CachingDispatchAsync;
import com.cheetah.core.client.rpc.DispatchCallBack;
import com.cheetah.core.shared.rpc.LoginAction;
import com.cheetah.core.shared.rpc.LoginResult;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.inject.Inject;

/**
 * Follow the MVP mechanism to implement {@link LoginPlace}
 * 
 * @author Kobe Sun
 * 
 */

public class LoginPresenter extends WidgetPresenter<LoginPresenter.Display>
		implements LoginHandler {

	/**
	 * Build as a bridge between {@link LoginPresenter} and {@link LoginView}
	 * 
	 */
	public interface Display extends BaseWidgetDisplay {
		public TextField<String> getUserField();

		public TextField<String> getPWDField();

		public Button getLoginButton();
		
		public Button getResetButton();
	}

	private final CachingDispatchAsync cachingDispatcher;

	@Inject
	public LoginPresenter(final Display display, EventBus eventbus,
			CachingDispatchAsync cachingDispatcher) {
		super(display, eventbus);
		this.cachingDispatcher = cachingDispatcher;
	}

	// Any handler registration should be located in the onBind() function,
	// other logical stuff should be in onRevealDisplay.
	// This is served as a callback function for upper level presenter
	@Override
	protected void onBind() {
		eventBus.addHandler(LoginEvent.getType(), this);
	}

	// This is served as a callback function for upper level presenter
	@Override
	protected void onUnbind() {
		// TODO Auto-generated method stub
	}

	// This is served as a callback function for upper level presenter
	@Override
	protected void onRevealDisplay() {
		display.getLoginButton().addSelectionListener(
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						onLoginClick();
					}
				});
		display.getResetButton().addSelectionListener(
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						onResetClick();
					}
		});
	}

	private final void onLoginClick() {
		cachingDispatcher.executeWithCache(new LoginAction(display
				.getUserField().getValue(), display.getPWDField().getValue()),
				new DispatchCallBack<LoginResult>(eventBus, display) {
					@Override
					public void callback(LoginResult result) {
						Log.debug("[LoginPresenter]: "
								+ result.getUser().getUsername());
						eventBus.fireEvent(new LoginEvent());
					}
				});
	}
	
	private final void onResetClick() {
		display.getUserField().setRawValue(null);
		display.getPWDField().setRawValue(null);
	}

	@Override
	public void onLogin(LoginEvent event) {
		// TODO Auto-generated method stub

	}

}
