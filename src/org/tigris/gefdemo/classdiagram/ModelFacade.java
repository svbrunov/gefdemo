package org.tigris.gefdemo.classdiagram;

import org.tigris.gefdemo.classdiagram.model.UmlAssociation;
import org.tigris.gefdemo.classdiagram.model.UmlAssociationEnd;
import org.tigris.gefdemo.classdiagram.model.UmlClassifier;
import org.tigris.gefdemo.classdiagram.model.UmlModel;
import org.tigris.gefdemo.classdiagram.model.UmlModelElement;

/**
 * A facade class to simplify access to the model.
 * In this case it's pretty simple anyway.
 * @author Bob Tarling
 * @since 09-Jun-2004
 */
public class ModelFacade {
    private static ModelFacade instance = new ModelFacade();
    
    public static ModelFacade getInstance() {
        return instance;
    }
    
    private UmlModel umlModel = new UmlModel();
    
    private ModelFacade() {
    }

    public Object createModelElement(Class identifier, Object fromPort, Object toPort) {
        if (identifier.equals(UmlAssociationEnd.class) && fromPort instanceof UmlClassifier && toPort instanceof UmlAssociation) {
            Object swapper = fromPort;
            fromPort = toPort;
            toPort = swapper;
        }
        return umlModel.createModelElement(identifier, (UmlModelElement)fromPort, (UmlModelElement)toPort);
    }
    
    public Object createModelElement(Class identifier) {
        return umlModel.createModelElement(identifier);
    }
    
    public void removeModelElement(Object element) {
        umlModel.removeModelElement((UmlModelElement)element);
    }
}
