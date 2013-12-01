package com.cheetah.core.client.events;

import com.google.gwt.event.shared.GwtEvent;

/**
 * This event should get fired on GWT RPC communication.
 * 
 * @author Kobe Sun
 * 
 */

public class ServerStatusEvent extends GwtEvent<ServerStatusEventHandler> {
	public static Type<ServerStatusEventHandler> TYPE = new Type<ServerStatusEventHandler>();

	public enum ServerStatus {
		Unknown, Available, Unavailable, Error
	}

	private ServerStatus status = ServerStatus.Unknown;

	public ServerStatusEvent(ServerStatus status) {
		this.status = status;
	}

	public ServerStatus getStatus() {
		return status;
	}

	@Override
	protected void dispatch(ServerStatusEventHandler handler) {
		handler.onServerStatusChange(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ServerStatusEventHandler> getAssociatedType() {
		return TYPE;
	}

}
