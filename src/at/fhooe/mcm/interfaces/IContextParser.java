package at.fhooe.mcm.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

import at.fhooe.mcm.context.elements.ContextElement;

public interface IContextParser {

	ContextElement parseContextFromXML(String _filePath) throws FileNotFoundException, IOException;
	
}
