package com.cheetah.core.client.mvp;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.PresenterRevealedEvent;
import net.customware.gwt.presenter.client.PresenterRevealedHandler;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.cheetah.core.client.prototype.BaseWidgetDisplay;
import com.extjs.gxt.ui.client.widget.Layout;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * This class provides support for widgets that contain other widgets. It will
 * listen for {@link PresenterRevealedEvent}s and reveal itself if the source
 * was a direct child of this presenter.
 * 
 * @author Kobe Sun
 */
public class BasePresenter extends WidgetPresenter<BasePresenter.Display> {

	/**
	 * An extension of {@link WidgetDisplay} for widgets that can contain other
	 * widgets.
	 * 
	 * @author Kobe Sun
	 */
	public interface Display extends BaseWidgetDisplay {
		void showWidget(Widget widget, Layout layout);
	}

	private final List<WidgetPresenter<? extends BaseWidgetDisplay>> presenters;

	private WidgetPresenter<? extends BaseWidgetDisplay> currentPresenter;

	@SuppressWarnings("unchecked")
	@Inject
	public BasePresenter(BasePresenter.Display display, EventBus eventBus,
			LoginPresenter lp) {
		super(display, eventBus);
		this.presenters = new ArrayList<WidgetPresenter<? extends BaseWidgetDisplay>>();
		addPresenters(lp);
	}

	/**
	 * Adds the list of presenters, if they are unbound. Bound presenters will
	 * simply be ignored, and <code>false</code> will be returned if any were
	 * ignored.
	 * 
	 * @param presenters
	 *            The list of presenters.
	 * @return <code>false</code> if any of the presenters were bound, and
	 *         therefor not added.
	 */
	protected boolean addPresenters(
			WidgetPresenter<? extends BaseWidgetDisplay>... presenters) {
		boolean allSuccess = true;
		for (WidgetPresenter<? extends BaseWidgetDisplay> presenter : presenters) {
			if (!addPresenter(presenter))
				allSuccess = false;
		}
		return allSuccess;

	}

	/**
	 * Adds the presenter, if the current presenter is unbound.
	 * 
	 * @param presenter
	 *            The presenter to add.
	 * @return If added, returns <code>true</code>.
	 */
	protected boolean addPresenter(
			WidgetPresenter<? extends BaseWidgetDisplay> presenter) {
		if (!isBound()) {
			presenters.add(presenter);
			return true;
		}
		return false;
	}

	@Override
	protected void onBind() {
		// Handle revelation events from children
		registerHandler(eventBus.addHandler(PresenterRevealedEvent.getType(),
				new PresenterRevealedHandler() {
					public void onPresenterRevealed(PresenterRevealedEvent event) {
						if (event.getPresenter() instanceof WidgetPresenter) {
							@SuppressWarnings("unchecked")
							WidgetPresenter<? extends BaseWidgetDisplay> presenter = (WidgetPresenter<? extends BaseWidgetDisplay>) event
									.getPresenter();
							if ((currentPresenter != presenter)
									&& presenters.contains(presenter)) {
								// Make this presenter the focus
								setCurrentPresenter(presenter);
								// Reveal ourselves so that the child will be
								// revealed.
								firePresenterRevealedEvent(false);
							}
						}
					}
				}));
	}

	@Override
	protected void onUnbind() {
		currentPresenter = null;
		for (WidgetPresenter<? extends BaseWidgetDisplay> presenter : presenters) {
			presenter.unbind();
		}
	}

	/**
	 * Sets the specified presenter to the be currently displaying presenter. If
	 * the presenter has not been added ({@see #addPresenter(WidgetPresenter<?
	 * extends BaseWidgetDisplay>)}), it will not be set as the current
	 * presenter.
	 * 
	 * @param presenter
	 *            The presenter.
	 * @return <code>true</code> if the presenter was successfully set as the
	 *         current presenter.
	 */
	protected boolean setCurrentPresenter(
			WidgetPresenter<? extends BaseWidgetDisplay> presenter) {
		if (presenters.contains(presenter)) {
			if (currentPresenter != null)
				currentPresenter.unbind();
			currentPresenter = presenter;
			currentPresenter.bind();
			display.showWidget(presenter.getDisplay().asWidget(), presenter
					.getDisplay().RequestBaseLayout());
			return true;
		}
		return false;
	}

	@Override
	protected void onRevealDisplay() {
		if (currentPresenter == null && presenters.size() > 0) {
			// only reveal display of this presenter, event handler
			// will set this presenter to be the current presenter
			// and as a result the view of this presenter will be shown
			presenters.get(0).revealDisplay();
		} else {
			currentPresenter.revealDisplay();
		}
	}
}
