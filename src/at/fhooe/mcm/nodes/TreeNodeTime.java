package at.fhooe.mcm.nodes;

import java.time.LocalTime;

import at.fhooe.mcm.context.elements.ContextElement;

/**
 * Treenode for time.
 * @author ifumi
 *
 */
public class TreeNodeTime extends TreeNode {
	
	private LocalTime mTime;
	
	public TreeNodeTime (String _image) {
		mTime = LocalTime.parse(_image);
	}

	@Override
	public Object calculate() throws NodeError {
		return mTime;
	}

	@Override
	public void setVariableParameters(ContextElement[] _contextElements) {
		// No context needed
	}

	@Override
	public void clear() {
		mTime = null;
	}

}
