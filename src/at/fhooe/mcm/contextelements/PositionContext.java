package at.fhooe.mcm.contextelements;

import org.postgis.Point;

public class PositionContext extends ContextElement {
	
	public enum PositionType {
		GAUSS_KRUEGER, WGS84, UTM
	}
	
	private int mXPos, mYPos;
	private PositionType mType;

	public PositionContext(int _id, String _key, PositionType _type, int _x, int _y) {
		super(_id, _key);
		mType = _type;
		mXPos = _x;
		mYPos = _y;
	}

	public PositionType getType() {
		return mType;
	}
	
	public Point getPosition() {
		return new Point (mXPos, mYPos);
	}
}
