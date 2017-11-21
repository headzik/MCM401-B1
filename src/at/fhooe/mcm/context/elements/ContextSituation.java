package at.fhooe.mcm.context.elements;

public class ContextSituation {

    private PositionContext mPositionContext;
    private SpeedContext mSpeedContext;
    private TemperatureContext mTemperatureContext;
    private TimeContext mTimeContext;

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
}
