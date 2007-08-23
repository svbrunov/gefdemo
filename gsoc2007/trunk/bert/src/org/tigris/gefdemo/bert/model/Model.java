package org.tigris.gefdemo.bert.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * A class which represents the entire UML model
 * @author Bob Tarling
 * @since 03-Jun-2004
 */
public class Model {

    private Vector umlModelElementRemovalListeners = new Vector();
    private List umlModelElements = new ArrayList();
    
    public Object createModelElement(Object identifier) {
        if (identifier.equals(Table.class)) {
            Table table = new TableImpl();
            // For the demo at this stage all tables have just two attributes
            Attribute attr1 = new AttributeImpl(table);
            attr1.setName("Attribute1");
            table.addAttribute(attr1);
            Attribute attr2 = new AttributeImpl(table);
            attr2.setName("Attribute2");
            table.addAttribute(attr2);
            return table;
        }
        throw new IllegalArgumentException("Factory failed to create a model element for the identifier " + identifier);
    }
    
    public Object createModelElement(
            Object identifier,
            ModelElement fromPort,
            ModelElement toPort) {
        
        Object modelElement = null;
        if (identifier.equals(Relationship.class)) {
            RelationshipImpl dep = new RelationshipImpl();
            dep.setSupplier((Attribute)fromPort);
            dep.setClient((Attribute)toPort);
            modelElement = dep;
        }
        if (modelElement != null) {
            return modelElement;
        }
        throw new IllegalArgumentException("Factory failed to create a model element for the identifier " + identifier);
    }
    
    public void removeModelElement(ModelElement modelElement) {
        // TODO If node are removed then remove their
        // edges first.
        umlModelElements.remove(modelElement);
        System.out.println("ModelElement removed " + modelElement);
        if (modelElement instanceof RelationshipImpl) {
            RelationshipImpl dep = (RelationshipImpl)modelElement;
            dep.setSupplier(null);
            dep.setClient(null);
        } 
        notifyModelElementRemoval(modelElement);
    }
    
    private void notifyModelElementRemoval(ModelElement modelElement) {
        Vector v;
        synchronized(this) {
            v = (Vector)umlModelElementRemovalListeners.clone();
        }
        ModelElementRemovalEvent evt = new ModelElementRemovalEvent(this, modelElement);
        int cnt = v.size();
        for (int i=0; i < cnt; ++i) {
            ModelElementRemovalListener listener = (ModelElementRemovalListener)v.elementAt(i);
            listener.elementRemoved(evt);
        }
    }
    
    public void addUmlModelElementRemovalListener(ModelElementRemovalListener listener) {
        umlModelElementRemovalListeners.add(listener);
    }
}
