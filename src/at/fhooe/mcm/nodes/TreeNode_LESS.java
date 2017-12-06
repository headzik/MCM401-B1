package at.fhooe.mcm.nodes;

import java.time.LocalTime;

import at.fhooe.mcm.context.elements.ContextElement;
import at.fhooe.mcm.nodes.TreeNodeContextVar.ContextType;

public class TreeNode_LESS extends TreeNode{
  @Override
  public Object calculate() throws NodeError {
		if (((TreeNodeContextVar)getChilds()[0]).getType() == ContextType.TIME) {
			if (((LocalTime) getChilds()[0].calculate()).isBefore((LocalTime) getChilds()[1].calculate())) {
				return true;
			} else {
				return false;
			}
		} else {
		    if (((int) getChilds()[0].calculate()) < ((int) getChilds()[1].calculate()))
		    	return true;
		    else
		     	return false;
		}
  }

  @Override
  public void setVariableParameters(ContextElement[] _contextElements) {
    if (getChilds()[0] != null)
      getChilds()[0].setVariableParameters(_contextElements);

    if (getChilds()[1] != null)
      getChilds()[1].setVariableParameters(_contextElements);
  }

  @Override
  public void clear() {
	  // Nothing to clear
  }
}
