package org.tigris.gefdemo.uml.model;

/**
 * @author Bob Tarling
 */
public interface UmlNamespace extends UmlModelElement {
    void addOwnedElement(UmlModelElement modelElement);
    void removeOwnedElement(UmlModelElement modelElement);
}
