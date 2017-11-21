package at.fhooe.mcm.components.ctxmanagement;

import java.awt.event.*;

import at.fhooe.mcm.components.CMComponent;
import at.fhooe.mcm.context.elements.SpeedContext;

public class CMController implements ActionListener {
	
	private CMModel mModel;
	private CMComponent mComponent;
	
    public CMController(CMModel _model, CMComponent _comp) {
    	mModel = _model;
    	mComponent = _comp;
    }

	@Override
	public void actionPerformed(ActionEvent _e) {
		switch (_e.getActionCommand()) {
		case "thread":
			mComponent.togglePeriodicUpdate();
			break;
		case "setContext":
			// TODO --> Read context values from the GUI elements and set according ContextElements in the model
			// mModel.setContextElement(new SpeedElement(stuff from speed gui));
			// mModel.setContextElement(new Position(stuff from position gui));
			// ........ do that for all 9 elements
			break;
			default:
		}
	}
}
