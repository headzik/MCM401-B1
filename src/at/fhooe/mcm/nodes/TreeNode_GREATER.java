package at.fhooe.mcm.nodes;


import java.time.LocalTime;

import at.fhooe.mcm.context.elements.ContextElement;
import at.fhooe.mcm.nodes.TreeNodeContextVar.ContextType;

/**
 * Treenode for GREATER condition.
 * @author ifumi
 *
 */
public class TreeNode_GREATER extends TreeNode {
	@Override
	public Object calculate() throws NodeError {
		TreeNode firstChild = getChilds()[0];	
		TreeNode secondChild = getChilds()[1];
		
		if (firstChild instanceof TreeNodeContextVar 
				&& ((TreeNodeContextVar)firstChild).getType() == ContextType.TIME) {
			if (((LocalTime) firstChild.calculate()).isAfter((LocalTime) secondChild.calculate())) {
				return true;
			} else {
				return false;
			}
		} else {
		    if (((int) firstChild.calculate()) > ((int) secondChild.calculate()))
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
