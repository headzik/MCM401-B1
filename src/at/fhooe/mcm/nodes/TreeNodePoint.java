package at.fhooe.mcm.nodes;

import org.postgis.Point;

import at.fhooe.mcm.context.elements.ContextElement;

/**
 * Treenode for a point (used for position).
 * @author ifumi
 *
 */
public class TreeNodePoint extends TreeNode {

  private Point mPoint;

  public TreeNodePoint(String _image){
	  int index =  _image.indexOf(",");
	  int x = Integer.parseInt(_image.substring(0,index));
	  int y = Integer.parseInt(_image.substring(index+1, _image.length()));
	  mPoint = new Point(x, y);
  }

  @Override
  public Object calculate() throws NodeError {
      return mPoint;
  }

  @Override
  public void setVariableParameters(ContextElement[] _contextElements) {
    // No context element needed
  }

  @Override
  public void clear() {
	  mPoint = new Point();
  }
}
