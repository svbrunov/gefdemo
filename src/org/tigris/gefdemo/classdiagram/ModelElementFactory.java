/*
 * Created on 09-Jun-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.tigris.gefdemo.classdiagram;

import org.tigris.gefdemo.classdiagram.model.UmlAssociation;
import org.tigris.gefdemo.classdiagram.model.UmlAssociationEnd;
import org.tigris.gefdemo.classdiagram.model.UmlClassifier;
import org.tigris.gefdemo.classdiagram.model.UmlDependency;
import org.tigris.gefdemo.classdiagram.model.UmlModel;
import org.tigris.gefdemo.classdiagram.model.UmlModelElement;

/**
 * 
 * @author Bob Tarling
 * @since 09-Jun-2004
 */
public class ModelElementFactory {
    private static ModelElementFactory instance = new ModelElementFactory();
    
    public static ModelElementFactory getInstance() {
        return instance;
    }
    
    private ModelElementFactory() {
    }

    public Object create(Class identifier, Object fromPort, Object toPort) {
        if (identifier.equals(UmlAssociationEnd.class) && fromPort instanceof UmlClassifier && toPort instanceof UmlAssociation) {
            Object swapper = fromPort;
            fromPort = toPort;
            toPort = swapper;
        }
        return UmlModel.getInstance().createModelElement(identifier, (UmlModelElement)fromPort, (UmlModelElement)toPort);
    }
}
