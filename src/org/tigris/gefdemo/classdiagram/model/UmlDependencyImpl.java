package org.tigris.gefdemo.classdiagram.model;

/** A NetEdge subclass to represent a dependency between tagets.
 */

class UmlDependencyImpl implements UmlDependency {
    
    private String name;
    
    /** Construct a new Depends. */
    public UmlDependencyImpl() {
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
}
