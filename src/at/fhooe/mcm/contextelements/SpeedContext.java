package at.fhooe.mcm.contextelements;

public class SpeedContext extends ContextElement {

	public enum SpeedType {
		KMH, MPH
	}
	
	private SpeedType mType;
	private int mSpeed;
	
	public SpeedContext(int _id, String _key, SpeedType _type, int _speed) {
		super(_id, _key);
		mType = _type;
		mSpeed = _speed;
	}
	
	public SpeedType getType() {
		return mType;
	}
	
	public int getSpeed() {
		return mSpeed;
	}
}
