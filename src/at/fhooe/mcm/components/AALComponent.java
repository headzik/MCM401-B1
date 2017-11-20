package at.fhooe.mcm.components;

import at.fhooe.mcm.aal.AALController;
import at.fhooe.mcm.aal.AALModel;
import at.fhooe.mcm.aal.AALView;
import at.fhooe.mcm.contextparsers.DomParser;
import at.fhooe.mcm.contextparsers.StreamParser;
import at.fhooe.mcm.gps.GPSView;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IContextParser;

import java.awt.*;

public class AALComponent implements IComponent {

	private Panel mView;
	private AALModel mModel;
	
	public enum ParseMode {
		DOM, STREAM
	}
	
	private IContextParser mParser = null;
	
	 public AALComponent(ParseMode _mode) {
		switch (_mode) {
		case DOM:
		    mParser = new DomParser();
			break;
		case STREAM:
			mParser = new StreamParser();
			break;
			default:				
		}

		 mModel = new AALModel();
		 AALController controller = new AALController(mModel);
		 AALView view = new AALView(controller);

		 mView = view.getView();
	}

	@Override
	public Panel getView() {
		return mView;
	}

	@Override
	public String getName() {
		return "AALComponent";
	}
}
