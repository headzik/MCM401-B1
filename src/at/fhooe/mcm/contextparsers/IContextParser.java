package at.fhooe.mcm.contextparsers;

import at.fhooe.mcm.contextelements.ContextElement;

public interface IContextParser {

	public ContextElement parseContextFromXML(String _filePath);
	
}
