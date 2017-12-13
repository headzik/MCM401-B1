package at.fhooe.mcm.objects;
import java.util.ArrayList;
import java.util.List;

import at.fhooe.mcm.components.AALComponent;
import at.fhooe.mcm.context.elements.ContextSituation;
import at.fhooe.mcm.interfaces.*;

/**
 * Observable class
 * @author ifumi
 *
 */
public class Observable {

	/**
	 * Different types/channels to subscribe to.
	 * @author ifumi
	 *
	 */
	public enum ObserverType {
		GIS, AAL, CM, MED
	}

	/**
	 * Wrapper class for the observable so you can subscribe to just one type/channel.
	 * @author ifumi
	 *
	 */
	private class ObserverWrapper {

		private IObserver mObs;
		private ObserverType mType;

		public ObserverWrapper(IObserver _obs, ObserverType _type) {
			mObs = _obs;
			mType = _type;
		}

		public IObserver getObserver() {
			return mObs;
		}

		public ObserverType getType() {
			return mType;
		}
	}

	private List<ObserverWrapper> mObservers = new ArrayList<ObserverWrapper>();
	
	/**
	 * Adds an observer to the list.
	 * @param _observer The observer.
	 * @param _type The channel to add to.
	 */
	public void addObserver(IObserver _observer, ObserverType _type) {
		mObservers.add(new ObserverWrapper(_observer, _type));
	}

	/**
	 * Notifies all observers with the passed object.
	 * @param _o The object to pass.
	 * @param _type The type/channel to notify.
	 */
	public void notifyObservers(Object _o, ObserverType _type) { // Object can be GeoObject, ContextElement, ContextSituation...
		for(ObserverWrapper obs : mObservers) {
			if (obs.getType().equals(_type))
				obs.getObserver().update(_o);
		}
	}

	/**
	 * Notifies all observers with the passed object. 
	 * @param _o The object to pass.
	 */
	public void notifyObservers(Object _o) {
		for(ObserverWrapper obs : mObservers) {
				obs.getObserver().update(_o);
		}
	}
}
