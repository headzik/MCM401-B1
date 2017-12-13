package at.fhooe.mcm.context.elements;

/**
 * Context element for the weather.
 * @author ifumi
 *
 */
public class WeatherContext extends ContextElement {

    public enum WeatherType{
        TROPICAL, TEMPERATE, SUBTROPICS
    }

    public enum WeatherValue{
        SUNNY, RAINY, CLOUDY
    }

    private WeatherType mType;
    private WeatherValue mWeather;

    public WeatherContext(int _id, String _key, WeatherType _type, WeatherValue _weather) {
        super(_id, _key);

        mType = _type;
        mWeather = _weather;
    }

    public WeatherType getType() {
        return mType;
    }

    public WeatherValue getWeather() {
        return mWeather;
    }
}
