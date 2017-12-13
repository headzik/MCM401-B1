package at.fhooe.mcm.nodes;

import at.fhooe.mcm.context.elements.ContextElement;

/**
 * Treenode for a digit.
 * @author ifumi
 *
 */
public class TreeNodeDigit extends TreeNode {

  private int mValue;

  public TreeNodeDigit(String _image){
    mValue = Integer.parseInt(_image);
  }

  @Override
  public Object calculate() throws NodeError {
      return mValue;
  }

  @Override
  public void setVariableParameters(ContextElement[] _contextElements) {
    // No context element needed
  }

  @Override
  public void clear() {
    mValue = -1;
  }
}
