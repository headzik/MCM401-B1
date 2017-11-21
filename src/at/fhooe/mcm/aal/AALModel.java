package at.fhooe.mcm.aal;

import at.fhooe.mcm.contextelements.*;
import at.fhooe.mcm.contextparsers.DomParser;
import at.fhooe.mcm.contextparsers.StreamParser;
import at.fhooe.mcm.interfaces.IContextParser;
import at.fhooe.mcm.objects.Observable;

import java.io.IOException;

public class AALModel extends Observable{

    private IContextParser mParser;
    private ContextSituation mContextSituation;


    public enum ParseMode {
        DOM, STREAM
    }


    public AALModel() {
        mContextSituation = new ContextSituation();
    }

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
