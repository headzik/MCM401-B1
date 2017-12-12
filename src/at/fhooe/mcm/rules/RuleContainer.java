package at.fhooe.mcm.rules;

import java.lang.reflect.Method;
import java.util.ArrayList;

import at.fhooe.mcm.compiler.ParseException;
import at.fhooe.mcm.context.elements.ContextSituation;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.nodes.NodeError;
import at.fhooe.mcm.nodes.TreeNode;

public class RuleContainer {

    private TreeNode mConditionRoot = null;
    private String mAction = null;

    public RuleContainer(String _condition, String _action) throws ParseException {      	
    	mAction = _action;
    	mConditionRoot = at.fhooe.mcm.compiler.Compiler.evaluate(_condition);
    }

    public boolean valid(ContextSituation _sit) {
    	try {
			mConditionRoot.calculate();
		} catch (NodeError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return false;
    }

    @SuppressWarnings("unchecked")
	public void execute() {
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
	        IComponent c = (IComponent) Class.forName(className).newInstance();
	        Method m = c.getClass().getMethod(methodName, argumentTypes.toArray(new Class[argumentTypes.size()]));
	        m.invoke(c, arguments.toArray(new Object[arguments.size()]));
	        
    	} catch(Exception _e) {
    		_e.printStackTrace();
    	}
    }
}