package at.fhooe.mcm.context.elements;

/**
 * Context element for fuel status.
 * @author ifumi
 *
 */
public class FuelContext extends ContextElement {

	private int mStatus; // 0- 100 %
	
	public FuelContext(int _id, String _key, int _status) {
		super(_id, _key);
		mStatus = _status;
	}

	public int getStatus() {
		return mStatus;
	}

}
