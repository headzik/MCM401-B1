package at.fhooe.mcm.context.elements;

import java.io.Serializable;

public class ContextSituation implements Serializable {

    private PositionContext mPositionContext;
    private SpeedContext mSpeedContext;
    private TemperatureContext mTemperatureContext;
    private TimeContext mTimeContext;
    private AirQualityContext mAirQualityContext;
    private DensityContext mDensityContext;
    private UltravioletRadiationContext mUltravioletRadiationContext;
    private VehicleContext mVehicleContext;
    private WeatherContext mWeatherContext;
    private FuelContext mFuelContext;

    public PositionContext getPositionContext() {
        return mPositionContext;
    }

    public void setPositionContext(PositionContext mPositionContext) {
        this.mPositionContext = mPositionContext;
    }

    public SpeedContext getSpeedContext() {
        return mSpeedContext;
    }

    public void setSpeedContext(SpeedContext mSpeedContext) {
        this.mSpeedContext = mSpeedContext;
    }

    public TemperatureContext getTemperatureContext() {
        return mTemperatureContext;
    }

    public void setTemperatureContext(TemperatureContext mTemperatureContext) {
        this.mTemperatureContext = mTemperatureContext;
    }

    public TimeContext getTimeContext() {
        return mTimeContext;
    }

    public void setTimeContext(TimeContext mTimeContext) {
        this.mTimeContext = mTimeContext;
    }

    public void setAirQualityContext(AirQualityContext airQualityContext) {
        mAirQualityContext = airQualityContext;
    }

    public void setDensityContext(DensityContext densityContext) {
        mDensityContext = densityContext;
    }

    public void setUltravioletRadiationContext(UltravioletRadiationContext ultravioletRadiationContext) {
        mUltravioletRadiationContext = ultravioletRadiationContext;
    }

    public void setVehicleContext(VehicleContext vehicleContext) {
        mVehicleContext = vehicleContext;
    }

    public void setWeatherContext(WeatherContext weatherContext) {
        mWeatherContext = weatherContext;
    }

    public AirQualityContext getAirQualityContext() {
        return mAirQualityContext;
    }

    public DensityContext getDensityContext() {
        return mDensityContext;
    }

    public UltravioletRadiationContext getUltravioletRadiationContext() {
        return mUltravioletRadiationContext;
    }

    public VehicleContext getVehicleContext() {
        return mVehicleContext;
    }

    public WeatherContext getWeatherContext() {
        return mWeatherContext;
    }

	public FuelContext getFuelContext() {
		return mFuelContext;
	}
	
	public void setFuelContext(FuelContext _fuelContext) {
		mFuelContext = _fuelContext;
	}

	public ContextElement[] getAllElements() {
		return new ContextElement[] {mPositionContext,                  
		 mSpeedContext,                           
		 mTemperatureContext,                   
		 mTimeContext,                                
		 mAirQualityContext,                     
		 mDensityContext,                          
		 mUltravioletRadiationContext, 
		 mVehicleContext,                           
		 mWeatherContext,
		 mFuelContext
		};
	}
}
