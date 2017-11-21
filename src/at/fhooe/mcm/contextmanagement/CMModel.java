package at.fhooe.mcm.contextmanagement;

import at.fhooe.mcm.contextelements.*;
import at.fhooe.mcm.objects.Observable;

public class CMModel extends Observable {
    private ContextSituation mContextSituation = new ContextSituation();

    public void setContextElement(ContextElement _contextElement) {
        switch (_contextElement.getKey()){
            case "position":
                mContextSituation.setPositionContext((PositionContext) _contextElement);
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
            default:
                break;

        }
    }
}
