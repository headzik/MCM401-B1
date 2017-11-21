package at.fhooe.mcm.objects;
import java.util.ArrayList;
import java.util.List;

import at.fhooe.mcm.components.AALComponent;
import at.fhooe.mcm.contextelements.ContextSituation;
import at.fhooe.mcm.interfaces.*;

public class Observable {

	public enum ObserverType {
		GIS, AAL, CM, MED
	}

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
	
	public void addObserver(IObserver _observer, ObserverType _type) {
		mObservers.add(new ObserverWrapper(_observer, _type));
	}

	public void notifyObservers(Object _o, ObserverType _type) { // Object can be GeoObject, ContextElement, ContextSituation...
		for(ObserverWrapper obs : mObservers) {
			if (obs.getType().equals(_type))
				obs.getObserver().update(_o);
		}
	}

	public void notifyObservers(Object _o) {
		for(ObserverWrapper obs : mObservers) {
				obs.getObserver().update(_o);
		}
	}
}
