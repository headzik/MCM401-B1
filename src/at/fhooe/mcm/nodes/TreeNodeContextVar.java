package at.fhooe.mcm.nodes;

import at.fhooe.mcm.context.elements.ContextElement;
import at.fhooe.mcm.context.elements.DensityContext;
import at.fhooe.mcm.context.elements.FuelContext;
import at.fhooe.mcm.context.elements.PositionContext;
import at.fhooe.mcm.context.elements.SpeedContext;
import at.fhooe.mcm.context.elements.TemperatureContext;
import at.fhooe.mcm.context.elements.TimeContext;

public class TreeNodeContextVar extends TreeNode {

  public enum ContextType {
    TIME, FUELSTATUS, SPEED, TEMPERATURE, DENSITY, POSITION
  }

  private ContextType mType;
  private ContextElement mContextElement;

  public TreeNodeContextVar (ContextType _obj) {
    mType = _obj;
  }

  public ContextType getType() {
    return mType;
  }

  @Override
  public Object calculate() throws NodeError {
    switch (mType) {
      case SPEED:
        return ((SpeedContext) mContextElement).getSpeed();
      case TEMPERATURE:
        return ((TemperatureContext) mContextElement).getTemperature();
      case DENSITY:
        return ((DensityContext) mContextElement).getDensity();
      case FUELSTATUS:
    	  return ((FuelContext) mContextElement).getStatus();
      case TIME:
    	  return ((TimeContext) mContextElement).getTime();
      case POSITION:
    	  return ((PositionContext) mContextElement).getPosition();
    }
    return null;
  }

  @Override
  public void setVariableParameters(ContextElement[] _contextElements) {
    for (ContextElement ce : _contextElements) {
      switch (mType) {
        case SPEED:
          if (ce instanceof SpeedContext)
            mContextElement = ce;
          break;
        case TEMPERATURE:
          if (ce instanceof TemperatureContext)
            mContextElement = ce;
          break;
        case DENSITY:
          if (ce instanceof DensityContext)
            mContextElement = ce;
          break;
        case FUELSTATUS:
          if (ce instanceof FuelContext)
            mContextElement = ce;
          break;
        case TIME:
            if (ce instanceof TimeContext)
              mContextElement = ce;
            break;
        case POSITION:
            if (ce instanceof PositionContext)
              mContextElement = ce;
            break;
      }
    }
  }

  @Override
  public void clear() {
    mType = null;
    mContextElement = null;
  }

}
