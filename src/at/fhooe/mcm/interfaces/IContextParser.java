package at.fhooe.mcm.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

import at.fhooe.mcm.contextelements.ContextElement;

public interface IContextParser {

	public ContextElement parseContextFromXML(String _filePath) throws FileNotFoundException, IOException;
	
}
