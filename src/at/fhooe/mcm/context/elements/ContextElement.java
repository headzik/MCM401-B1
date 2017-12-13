package at.fhooe.mcm.context.elements;

import java.io.Serializable;

/**
 * Context element super class.
 * @author ifumi
 *
 */
public abstract class ContextElement implements Serializable {

	private int mID;
	private String mKey;
	
	public ContextElement(int _id, String _key) {
		mID = _id;
		mKey = _key;
	}
	
	public int getID() {
		return mID;
	}
	
	public String getKey() {
		return mKey;
	}
	
	
}
