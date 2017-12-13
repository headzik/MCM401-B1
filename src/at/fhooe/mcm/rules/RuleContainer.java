package at.fhooe.mcm.rules;

import java.lang.reflect.Method;
import java.util.ArrayList;

import at.fhooe.mcm.Mediator;
import at.fhooe.mcm.compiler.ParseException;
import at.fhooe.mcm.context.elements.ContextSituation;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.nodes.NodeError;
import at.fhooe.mcm.nodes.TreeNode;

/**
 * The rule container class.
 * @author ifumi
 *
 */
public class RuleContainer {

    private TreeNode mConditionRoot = null;
    private String mAction = null;
    private String con = null;

    /**
     * Constructor.
     * @param _condition The condition to check.
     * @param _action The action to execute on positive condition.
     * @throws ParseException The thrown exception.
     */
    public RuleContainer(String _condition, String _action) throws ParseException {      	
    	mAction = _action;
    	mConditionRoot = at.fhooe.mcm.compiler.Compiler.evaluate(_condition);
    	con = _condition;
    }

    /**
     * Checks if the condition is valid.
     * @param _sit The Context situation to check against.
     * @return True if valid, false otherwise.
     */
    public boolean valid(ContextSituation _sit) {
    	mConditionRoot.setVariableParameters(_sit.getAllElements());
    	try {
			return (boolean) mConditionRoot.calculate();
		} catch (NodeError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return false;
    }

    /**
     * Executes the action.
     * @param _sit The context situation to use.
     * @param _med The mediator.
     */
    @SuppressWarnings("unchecked")
	public void execute(ContextSituation _sit, Mediator _med) {
    	if(valid(_sit)) {
	    	String[] actionParts = mAction.split("/");
	    	String className = actionParts[0];
	    	String methodName = actionParts[1];    	
	    	String[] argumentTypesInString = actionParts[2].split(" ");
	    	String[] argumentValues = actionParts[3].split(" ");
	
	    	ArrayList<Class<?>> argumentTypes = new ArrayList<>();
	    	ArrayList<Object> arguments = new ArrayList<>();
	    	
	    	try {
	    		for(int i = 0; i < argumentTypesInString.length; i++) {
	    			Class c = Class.forName(argumentTypesInString[i]);
	    			argumentTypes.add(c);
	    			if(c.isEnum()) {
	    	    		arguments.add(Enum.valueOf(c, argumentValues[i]));
	    			} else {
	    				arguments.add(Class.forName(argumentValues[i]).newInstance());
	    			}
	    		}
	    		Class c;
		        for(IComponent comp: _med.getComponents()) {
		        	if(comp.getClass() == Class.forName(className)) {
		        		c = comp.getClass();
		        		Method m = c.getMethod(methodName, argumentTypes.toArray(new Class[argumentTypes.size()]));
				        m.invoke(comp, arguments.toArray(new Object[arguments.size()]));
				        System.out.println(c);
				        break;
		        	}
		        }

	    	} catch(Exception _e) {
	    		_e.printStackTrace();
	    	}
    	} else {
    		System.out.println("Rule not valid: " + con);
    	}
    }
}