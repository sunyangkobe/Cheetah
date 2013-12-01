package com.cheetah.core.client.events;

import com.google.gwt.event.shared.EventHandler;

/**
 * Implement this interface when communication with server happens.
 * 
 * @author Kobe Sun
 * 
 */

public interface ServerStatusEventHandler extends EventHandler {
	public void onServerStatusChange(ServerStatusEvent event);
}
