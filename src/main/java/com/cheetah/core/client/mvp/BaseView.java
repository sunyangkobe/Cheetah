package com.cheetah.core.client.mvp;

import com.extjs.gxt.ui.client.widget.Layout;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.google.gwt.user.client.ui.Widget;

/**
 * Hold all second level views for easy switch
 * 
 * @author Kobe Sun
 * 
 */
public class BaseView extends Viewport implements BasePresenter.Display {

	public BaseView() {
	}

	@Override
	public void showWidget(final Widget widget, final Layout layout) {
		if (this.getItemCount() > 0) {
			this.removeAll();
		}
		this.add(widget);
		if (layout != null)
			this.setLayout(layout);
		this.layout();
	}

	@Override
	public Layout RequestBaseLayout() {
		return null;
	}

}
