package at.fhooe.mcm.rules;

import java.lang.reflect.Method;
import java.util.ArrayList;

import at.fhooe.mcm.compiler.ParseException;
import at.fhooe.mcm.components.gis.warnings.IWarningType;
import at.fhooe.mcm.context.elements.ContextSituation;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IUIView;
import at.fhooe.mcm.nodes.TreeNode;

public class RuleContainer {

    private TreeNode mConditionRoot = null;
    private String mAction = null;

    public RuleContainer(String _condition, String _action) throws ParseException {      	
    	mAction = _action;
    	mConditionRoot = at.fhooe.mcm.compiler.Compiler.evaluate(_condition);
    }

    public boolean valid(ContextSituation _sit) {
//    	_sit.get
////    	mConditionRoot.setVariableParameters(_sit);
//    	mConditionRoot.e
        return false;
    }

    public void execute() {
    	String[] actionParts = mAction.split("/");
    	String className = actionParts[0];
    	String methodName = actionParts[1];    	
    	String[] argumentTypesInString = actionParts[2].split(" ");
    	String[] argumentValues = actionParts[3].split(" ");

    	ArrayList<Class<?>> argumentTypes = new ArrayList<>();
    	ArrayList<Object> arguments = new ArrayList<>();
    	
    	try {
	    	for(String typeName: argumentTypesInString) {
	    		argumentTypes.add(Class.forName(typeName));
	    	}
	    	
	    	for(String argument: argumentValues) {
	    		arguments.add((IWarningType)Class.forName(argument).newInstance());
	    	}
	    	
	        IComponent c = (IComponent) Class.forName(className).newInstance();
	        Method m = c.getClass().getMethod(methodName, argumentTypes.toArray(new Class[argumentTypes.size()]));
	        m.invoke(c, arguments.toArray(new Object[arguments.size()]));
	        
    	} catch(Exception _e) {
    		_e.printStackTrace();
    	}
    }
}