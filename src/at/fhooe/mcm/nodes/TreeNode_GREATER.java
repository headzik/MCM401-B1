package at.fhooe.mcm.nodes;

import at.fhooe.mcm.context.elements.ContextElement;

public class TreeNode_GREATER extends TreeNode{
  @Override
  public Object calculate() throws NodeError {
    if (((int) getChilds()[0].calculate()) > ((int) getChilds()[1].calculate()))
      return true;
    else
      return false;
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

  }
}
