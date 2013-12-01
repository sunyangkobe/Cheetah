package com.cheetah.core.client.places;

import java.util.HashSet;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceChangedEvent;
import net.customware.gwt.presenter.client.place.PlaceChangedHandler;
import net.customware.gwt.presenter.client.place.PlaceManager;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.place.PlaceRequestEvent;
import net.customware.gwt.presenter.client.place.PlaceRequestHandler;
import net.customware.gwt.presenter.client.place.PlaceRevealedEvent;
import net.customware.gwt.presenter.client.place.PlaceRevealedHandler;
import net.customware.gwt.presenter.client.place.TokenFormatException;
import net.customware.gwt.presenter.client.place.TokenFormatter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;

/**
 * Wrapper class for PlaceManager, will see how the next release of
 * gwt-presenter changes. Can use DefaultPlaceManager if gwt-presenter folks fix
 * problems.
 * 
 * @author Kobe Sun
 * 
 */
public class BasicPlaceManager implements PlaceManager {
	private class PlaceEventHandler implements ValueChangeHandler<String>,
			PlaceRevealedHandler, PlaceChangedHandler, PlaceRequestHandler {

		public void onPlaceRevealed(PlaceRevealedEvent event) {
			updateHistory(event.getPlace());
		}

		public void onPlaceChanged(PlaceChangedEvent event) {
			Place place = event.getPlace();
			try {
				if (place.matchesRequest(tokenFormatter.toPlaceRequest(History
						.getToken()))) {
					// Only update if the change comes from a place that matches
					// the current location.
					updateHistory(event.getPlace());
				}
			} catch (TokenFormatException e) {
				// Do nothing...
			}
		}

		/**
		 * Handles change events from {@link History}.
		 */
		public void onValueChange(ValueChangeEvent<String> event) {
			// TODO: should pass in true as fromHistory when firing the event
			// I have opened a defect for this and gwt-presenter folks probably
			// will change the visibility of fire or constructor in the next
			// release.
			try {
				PlaceRequestEvent.fire(eventBus,
						tokenFormatter.toPlaceRequest(event.getValue()));
			} catch (TokenFormatException e) {
				GWT.log("Failure when firing a PlaceRequestEvent from "
						+ event.getValue() + " .", e);
			}
		}

		@Override
		public void onPlaceRequest(PlaceRequestEvent event) {
			if (!existPlace(event.getRequest().getName())) {
				Window.alert("Request Place: " + event.getRequest().getName()
						+ " does not exist!!");
			}
		}

		private final boolean existPlace(final String name) {
			for (final Place place : registeredPlaces) {
				if (place.getName().equals(name)) {
					return true;
				}
			}
			return false;
		}

	}

	private final EventBus eventBus;

	private final TokenFormatter tokenFormatter;

	private final HashSet<Place> registeredPlaces;

	@Inject
	public BasicPlaceManager(EventBus eventBus, TokenFormatter tokenFormatter,
			LoginPlace lp) {
		this.eventBus = eventBus;
		this.tokenFormatter = tokenFormatter;

		PlaceEventHandler handler = new PlaceEventHandler();

		// Register ourselves with the History API.
		History.addValueChangeHandler(handler);

		// Listen for manual place change events.
		eventBus.addHandler(PlaceChangedEvent.getType(), handler);

		// Listen for place revelation requests.
		eventBus.addHandler(PlaceRevealedEvent.getType(), handler);

		// Listen for place request event.
		eventBus.addHandler(PlaceRequestEvent.getType(), handler);

		registeredPlaces = new HashSet<Place>();

		registerPlaces(lp);

	}

	public void registerPlaces(Place... places) {
		if (places != null) {
			for (Place place : places) {
				registerPlace(place);
			}
		}
	}

	public void deregisterPlaces(Place... places) {
		if (places != null) {
			for (Place place : places) {
				deregisterPlace(place);
			}
		}
	}

	public boolean registerPlace(Place place) {
		if (!registeredPlaces.contains(place)) {
			place.addHandlers(eventBus);
			registeredPlaces.add(place);
			return true;
		}
		return false;
	}

	public boolean deregisterPlace(Place place) {
		if (registeredPlaces.contains(place)) {
			place.removeHandlers(eventBus);
			registeredPlaces.remove(place);
			return true;
		}
		return false;
	}

	private void updateHistory(Place place) {
		updateHistory(place.createRequest());
	}

	// Updates History if it has changed, without firing another
	// 'ValueChangeEvent'.
	private void updateHistory(PlaceRequest request) {
		try {
			String requestToken = tokenFormatter.toHistoryToken(request);
			String historyToken = History.getToken();
			if (historyToken == null || !historyToken.equals(requestToken))
				History.newItem(requestToken, false);
		} catch (TokenFormatException e) {
			// Do nothing.
		}
	}

	/**
	 * Fires a {@link PlaceRequestEvent} with the current history token, if
	 * present. If no history token is set, <code>false</code> is returned.
	 * 
	 * @return <code>true</code>
	 */
	public boolean fireCurrentPlace() {
		String current = History.getToken();
		if (current != null && current.trim().length() > 0) {
			History.fireCurrentHistoryState();
			return true;
		}
		return false;
	}

}
