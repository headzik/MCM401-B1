package at.fhooe.mcm.rules;

import at.fhooe.mcm.context.elements.ContextSituation;
import at.fhooe.mcm.nodes.TreeNode;

public class RuleContainer {

    private TreeNode mConditionRoot = null;
    private String mAction = null;

    public RuleContainer(String _condition, String _action) {

    }

    public boolean valid(ContextSituation _sit) {
        return false;}

    public void execute() {

    }
}