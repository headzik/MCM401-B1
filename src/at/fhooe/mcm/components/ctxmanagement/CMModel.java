package at.fhooe.mcm.components.ctxmanagement;

import at.fhooe.mcm.context.elements.*;
import at.fhooe.mcm.objects.Observable;

/**
 * The model for the CM Component. Holding all data.
 * @author ifumi
 *
 */
public class CMModel extends Observable {

    private ContextSituation mContextSituation = new ContextSituation();

    /**
     * Getter for the current context situation.
     * @return
     */
    public ContextSituation getContextSituation() {
        return mContextSituation;
    }

    /**
     * Setter for the current context situation.
     * @param _cs The context situation to set.
     */
    public void setContextSituation(ContextSituation _cs) {
        mContextSituation = _cs;
    }

    /**
     * Setter for a context element. Sets the corresponding context element in the context situation.
     * @param _contextElement The element to set.
     */
    public void setContextElement(ContextElement _contextElement) {
        switch (_contextElement.getKey()) {
            case "position":
                mContextSituation.setPositionContext((PositionContext) _contextElement);
                break;
            case "fuel":
                mContextSituation.setFuelContext((FuelContext) _contextElement);
                break;
            case "speed":
                mContextSituation.setSpeedContext((SpeedContext) _contextElement);
                break;
            case "temperature":
                mContextSituation.setTemperatureContext((TemperatureContext) _contextElement);
                break;
            case "time":
                mContextSituation.setTimeContext((TimeContext) _contextElement);
                break;
            case "air":
                mContextSituation.setAirQualityContext((AirQualityContext) _contextElement);
                break;
            case "density":
                mContextSituation.setDensityContext((DensityContext) _contextElement);
                break;
            case "uv":
                mContextSituation.setUltravioletRadiationContext((UltravioletRadiationContext) _contextElement);
                break;
            case "vehicle":
                mContextSituation.setVehicleContext((VehicleContext) _contextElement);
                break;
            case "weather":
                mContextSituation.setWeatherContext((WeatherContext) _contextElement);
                break;
            default:
                break;

        }
    }
}
