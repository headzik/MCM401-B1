package at.fhooe.mcm.components.aal;

import at.fhooe.mcm.context.elements.*;
import at.fhooe.mcm.context.parsers.DomParser;
import at.fhooe.mcm.context.parsers.StreamParser;
import at.fhooe.mcm.interfaces.IContextParser;
import at.fhooe.mcm.objects.Observable;

import java.io.IOException;

/**
 * The model for the AAL Component. Holds all data.
 * @author ifumi
 *
 */
public class AALModel extends Observable{

    private IContextParser mParser;

    public enum ParseMode {
        DOM, STREAM
    }

    /**
     * Parses a context .xml file
     * @param _filePathText The path to the file.
     * @param _selectedParser The parsemode to use.
     */
    public void parseContextFile(String _filePathText, ParseMode _selectedParser) {
        System.out.println(_filePathText + " / " + _selectedParser.toString());

        switch (_selectedParser) {
            case DOM:
                mParser = new DomParser();
                break;
            case STREAM:
                mParser = new StreamParser();
                break;
            default:
                mParser = new DomParser();
        }

        ContextElement ce = null;
        try {
            ce = mParser.parseContextFromXML(_filePathText);
        } catch (IOException _e) {
            System.out.println(_e.toString());
        }
        if (ce != null){
            notifyObservers(ce);
        }
    }
}
