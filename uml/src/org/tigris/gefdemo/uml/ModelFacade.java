package org.tigris.gefdemo.uml;

import org.tigris.gefdemo.uml.model.UmlAssociation;
import org.tigris.gefdemo.uml.model.UmlAssociationClass;
import org.tigris.gefdemo.uml.model.UmlAssociationEnd;
import org.tigris.gefdemo.uml.model.UmlClassifier;
import org.tigris.gefdemo.uml.model.UmlModel;
import org.tigris.gefdemo.uml.model.UmlModelElement;

/**
 * A facade class to simplify access to the model.
 * In this case the model pretty mcuh fits our requirements
 * anyway but it is good practise to add another layer
 * of seperation here.
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
        // Spot any attempt to draw an association end between to classifiers
        if (identifier.equals(UmlAssociationEnd.class) && fromPort instanceof UmlClassifier && toPort instanceof UmlClassifier) {
            Object association = umlModel.createModelElement(UmlAssociation.class);
            createModelElement(UmlAssociationEnd.class, association, fromPort);
            createModelElement(UmlAssociationEnd.class, association, toPort);
            return association;
        }
        
        if (identifier.equals(UmlAssociationClass.class) && fromPort instanceof UmlClassifier && toPort instanceof UmlClassifier) {
            Object association = umlModel.createModelElement(UmlAssociationClass.class);
            umlModel.createModelElement(UmlAssociationEnd.class, (UmlModelElement)association, (UmlModelElement)fromPort);
            umlModel.createModelElement(UmlAssociationEnd.class, (UmlModelElement)association, (UmlModelElement)toPort);
            return association;
        }
        
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
