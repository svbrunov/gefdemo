package org.tigris.gefdemo.classdiagram.model;

/** A NetEdge subclass to represent a dependency between tagets.
 */

class UmlDependencyImpl implements UmlDependency {
    
    UmlModelElement supplier;
    UmlModelElement client;
    
    private String name;
    
    /** Construct a new Depends. */
    public UmlDependencyImpl(UmlModelElement supplier, UmlModelElement client) {
        this.supplier = supplier;
        this.client = client;
    }

    public String getId() {
        return toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public UmlModelElement getClient() {
        return client;
    }
    public UmlModelElement getSupplier() {
        return supplier;
    }
}
