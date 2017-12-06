package at.fhooe.mcm.nodes;

import at.fhooe.mcm.context.elements.ContextElement;
import at.fhooe.mcm.context.elements.DensityContext;
import at.fhooe.mcm.context.elements.SpeedContext;
import at.fhooe.mcm.context.elements.TemperatureContext;

public class TreeNodeContextVar extends TreeNode {

  public enum ContextType {
    SPEED, TEMPERATURE, DENSITY
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
      }
    }
  }

  @Override
  public void clear() {
    mType = null;
    mContextElement = null;
  }

}
