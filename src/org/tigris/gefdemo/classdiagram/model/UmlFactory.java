package org.tigris.gefdemo.classdiagram.model;

import org.tigris.gef.graph.GraphModelException;

/**
 * 
 * @author Bob Tarling
 * @since 03-Jun-2004
 */
public class UmlFactory {

    private static UmlFactory instance = new UmlFactory();
    
    public static UmlFactory getInstance() {
        return instance;
    }
    
    public Object createModelElement(Object identifier) throws GraphModelException {
        if (identifier.equals(UmlClass.class)) return new UmlClassImpl();
        if (identifier.equals(UmlInterface.class)) return new UmlInterfaceImpl();
        if (identifier.equals(UmlAssociation.class)) return new UmlAssociationImpl();
        throw new GraphModelException("Factory failed to create a model element for the identifier " + identifier);
    }
}
