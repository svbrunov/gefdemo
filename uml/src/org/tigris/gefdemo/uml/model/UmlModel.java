package org.tigris.gefdemo.uml.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * A class which represents the entire UML model
 * @author Bob Tarling
 * @since 03-Jun-2004
 */
public class UmlModel {

    private Vector umlModelElementRemovalListeners = new Vector();
    private List umlModelElements = new ArrayList();
     
    public Object createModelElement(Object identifier) {
        if (identifier.equals(UmlClass.class)) return new UmlClassImpl();
        if (identifier.equals(UmlInterface.class)) return new UmlInterfaceImpl();
        if (identifier.equals(UmlAssociation.class)) return new UmlAssociationImpl();
        throw new IllegalArgumentException("Factory failed to create a model element for the identifier " + identifier);
    }
    
    public Object createModelElement(
            Object identifier,
            UmlModelElement fromPort,
            UmlModelElement toPort) {
        Object modelElement = null;
        if (identifier.equals(UmlAssociationEnd.class)) {
            UmlAssociationEndImpl ae = new UmlAssociationEndImpl();
            ae.setAssociation((UmlAssociation)fromPort);
            ae.setClassifier((UmlClassifier)toPort);
            modelElement = ae;
        }
        if (identifier.equals(UmlDependency.class)) {
            UmlDependencyImpl dep = new UmlDependencyImpl();
            dep.setSupplier((UmlModelElement)fromPort);
            dep.setClient((UmlModelElement)toPort);
            modelElement = dep;
        }
        System.out.println("ModelElement created " + modelElement);
        if (modelElement != null) {
            return modelElement;
        }
        throw new IllegalArgumentException("Factory failed to create a model element for the identifier " + identifier);
    }
    
    public void removeModelElement(UmlModelElement modelElement) {
        // TODO If node are removed then remove their
        // edges first.
        umlModelElements.remove(modelElement);
        System.out.println("ModelElement removed " + modelElement);
        if (modelElement instanceof UmlAssociationEndImpl) {
            UmlAssociationEndImpl ae = (UmlAssociationEndImpl)modelElement;
            ae.setAssociation(null);
            ae.setClassifier(null);
        } else if (modelElement instanceof UmlDependencyImpl) {
            UmlDependencyImpl dep = (UmlDependencyImpl)modelElement;
            dep.setSupplier(null);
            dep.setClient(null);
        } 
        notifyModelElementRemoval(modelElement);
    }
    
    private void notifyModelElementRemoval(UmlModelElement modelElement) {
        Vector v;
        synchronized(this) {
            v = (Vector)umlModelElementRemovalListeners.clone();
        }
        UmlModelElementRemovalEvent evt = new UmlModelElementRemovalEvent(this, modelElement);
        int cnt = v.size();
        for (int i=0; i < cnt; ++i) {
            UmlModelElementRemovalListener listener = (UmlModelElementRemovalListener)v.elementAt(i);
            listener.elementRemoved(evt);
        }
    }
    
    public void addUmlModelElementRemovalListener(UmlModelElementRemovalListener listener) {
        umlModelElementRemovalListeners.add(listener);
    }
}
