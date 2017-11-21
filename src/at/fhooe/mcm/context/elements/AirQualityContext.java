package at.fhooe.mcm.context.elements;

import java.io.Serializable;

public class AirQualityContext extends ContextElement implements Serializable {

    public enum AirQualityType{
        COLD, WARM
    }

    public enum AirQualityValue{
        VERY_GOOD, GOOD, NORMAL, BAD, VERY_BAD;
    }

    private AirQualityType mType;
    private AirQualityValue mAirQuality;

    public AirQualityContext(int _id, String _key,AirQualityType _tpye, AirQualityValue _airQuality) {
        super(_id, _key);
        mType = _tpye;
        mAirQuality = _airQuality;
    }

    public AirQualityType getType() {
        return mType;
    }

    public AirQualityValue getAirQuality() {
        return mAirQuality;
    }
}
