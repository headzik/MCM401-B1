package at.fhooe.mcm.nodes;

import at.fhooe.mcm.context.elements.ContextElement;

public class TreeNode_AND extends TreeNode {
    @Override
    public Object calculate() throws NodeError {
        TreeNode[] children = getChilds();
        for (TreeNode child : children){
            if (!(boolean)child.calculate()){
                return false;
            }
        }
        return true;
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
