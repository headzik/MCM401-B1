package at.fhooe.mcm.nodes;

import org.postgis.Point;

import at.fhooe.mcm.context.elements.ContextElement;
import at.fhooe.mcm.nodes.TreeNodeContextVar.ContextType;

public class TreeNode_DISTANCE extends TreeNode {
	
	@Override
    public Object calculate() throws NodeError {
		boolean canCaluclateDistance = false;
		TreeNode firstChild = getChilds()[0];		
		
		if(firstChild instanceof TreeNodePoint) {
			canCaluclateDistance = true;
    	} else if (firstChild instanceof TreeNodeContextVar) {
    		if (((TreeNodeContextVar)firstChild).getType() == ContextType.POSITION) {
    			canCaluclateDistance = true;
	    	} 
    	}

		if(canCaluclateDistance) {
			return (int)((Point)firstChild.calculate()).distance((Point)getChilds()[1].calculate());
		} else {
			return 0; // or something else?
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

    }
}
