package at.fhooe.mcm.components.gis;

import at.fhooe.mcm.interfaces.IDataObserver;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DataObservable {

	private ArrayList<IDataObserver> mObservers;
	
	/**
	 * Constructor initializing mObservers list.
	 */
	public DataObservable() {
		mObservers = new ArrayList<IDataObserver>();
	}
	
	/**
	 * Adds an observer to the list.
	 * @param _obs Observer
	 */
	public void addObserver(IDataObserver _obs) {
		if (_obs != null) {
			mObservers.add(_obs);
		}
	}
	
	/**
	 * Notifies all observers in list that data has changed.
	 * @param _data Changed data
	 */
	public void notifyObservers(BufferedImage _data) {
		for (IDataObserver obs : mObservers) {
			obs.update(_data);
		}
	}
	
}
