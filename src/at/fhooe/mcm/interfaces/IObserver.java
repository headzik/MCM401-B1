package at.fhooe.mcm.interfaces;

import at.fhooe.mcm.objects.Observable.ObserverType;

public interface IObserver {
	
	void update(Object _o); // Object can be GeoObject, ContextElement, ContextSituation...

}
