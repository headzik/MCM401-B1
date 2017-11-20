package at.fhooe.mcm.contextelements;

public abstract class ContextElement {

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
