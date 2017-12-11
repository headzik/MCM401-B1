package at.fhooe.mcm.components;

import at.fhooe.mcm.Mediator;
import at.fhooe.mcm.interfaces.IComponent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ComponentsFactory {

    private List<IComponent> mComponents;
    private Node mNode;

    public ComponentsFactory(){

    }

    public List<IComponent> buildComponents(String _filePath, Mediator _mediator){
        mComponents = new ArrayList<>();
        try{
            File file = new File(_filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("component");
            for (int i = 0; i < nList.getLength(); i++){
                mNode = nList.item(i);
                String classname = new String(mNode.getAttributes().getNamedItem("name").getTextContent());
                IComponent c = (IComponent) Class.forName(classname).newInstance();
                c.init(_mediator);
                mComponents.add(c);
            }
        }catch (Exception _e){
           _e.printStackTrace();
        }
        return mComponents;
    }
}
