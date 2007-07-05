package org.tigris.gefdemo.uml.model;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * @author Bob Tarling
 */
public class UmlNamespaceImpl extends UmlModelElementImpl implements UmlNamespace {

    ArrayList ownedElements = new ArrayList();
    
    public UmlNamespaceImpl() {
        super(null);
    }
    
    /**
     * @see org.tigris.gefdemo.uml.model.UmlNamespace#addOwnedElement(org.tigris.gefdemo.uml.model.UmlModelElement)
     */
    public void addOwnedElement(UmlModelElement modelElement) {
        ownedElements.add(modelElement);
    }

    /**
     * @see org.tigris.gefdemo.uml.model.UmlNamespace#removeOwnedElement(org.tigris.gefdemo.uml.model.UmlModelElement)
     */
    public void removeOwnedElement(UmlModelElement modelElement) {
        ownedElements.remove(modelElement);
    }
}
