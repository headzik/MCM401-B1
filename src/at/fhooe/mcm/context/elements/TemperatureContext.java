package at.fhooe.mcm.context.elements;

public class TemperatureContext extends ContextElement {

	public enum TemperatureType {
		CELSIUS, FAHRENHEIT
	}
	
	private TemperatureType mType;
	private int mTemperature;
	
	public TemperatureContext(int _id, String _key, TemperatureType _type, int _val) {
		super(_id, _key);
		mType = _type;
		mTemperature = _val;
	}
	
	public TemperatureType getType() {
		return mType;
	}
	
	public int getTemperature() {
		return mTemperature;
	}

	@Override
	public String toString() {
		return mTemperature+"";
	}
}
