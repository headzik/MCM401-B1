package at.fhooe.mcm.objects;
import java.util.ArrayList;
import java.util.List;

import at.fhooe.mcm.interfaces.*;

public class Observable {
	
	private List<IObserver> mObservers = new ArrayList<IObserver>();
	
	public void addObserver(IObserver _observer) {
		mObservers.add(_observer);
	}

	public void notifyObservers(Object _o) { // Object can be GeoObject, ContextElement, ContextSituation...
		for(IObserver obs : mObservers) {
			obs.update(_o);
		}
	}
}
