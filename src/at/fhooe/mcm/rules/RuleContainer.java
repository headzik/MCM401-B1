package at.fhooe.mcm.rules;

import at.fhooe.mcm.compiler.ParseException;
import at.fhooe.mcm.context.elements.ContextSituation;
import at.fhooe.mcm.nodes.TreeNode;

public class RuleContainer {

    private TreeNode mConditionRoot = null;
    private String mAction = null;

    public RuleContainer(String _condition, String _action) throws ParseException {      	
    	mAction = _action;
    	mConditionRoot = at.fhooe.mcm.compiler.Compiler.evaluate(_condition);
    }

    public boolean valid(ContextSituation _sit) {
        return false;
    }

    public void execute() {
    	
    }
}