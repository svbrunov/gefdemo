package org.tigris.gefdemo.classdiagram.model;

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
    
    public Object createModelElement(Object identifier) {
        if (identifier.equals(UmlClass.class)) return new UmlClassImpl();
        if (identifier.equals(UmlInterface.class)) return new UmlInterfaceImpl();
        if (identifier.equals(UmlAssociation.class)) return new UmlAssociationImpl();
        throw new IllegalArgumentException("Factory failed to create a model element for the identifier " + identifier);
    }
    
    public Object createModelElement(
            Object identifier,
            Object fromPort,
            Object toPort) {
        if (identifier.equals(UmlAssociationEnd.class)) return new UmlAssociationEndImpl();
        if (identifier.equals(UmlDependency.class)) return new UmlDependencyImpl((UmlModelElement)fromPort, (UmlModelElement)toPort);
        throw new IllegalArgumentException("Factory failed to create a model element for the identifier " + identifier);
    }
}
