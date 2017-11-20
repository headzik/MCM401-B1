package at.fhooe.mcm.components;

import java.awt.Panel;

import at.fhooe.mcm.contextparsers.DomParser;
import at.fhooe.mcm.contextparsers.IContextParser;
import at.fhooe.mcm.contextparsers.StreamParser;
import at.fhooe.mcm.interfaces.IComponent;

public class AALComponent implements IComponent {
	
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
	}

	@Override
	public Panel getView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
