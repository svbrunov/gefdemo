package org.tigris.gefdemo.classdiagram.model;

/** A NetEdge subclass to represent a dependency between tagets.
 */

class UmlDependencyImpl implements UmlDependency {
    
    UmlModelElement supplier;
    UmlModelElement client;
    
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
    public UmlModelElement getClient() {
        return client;
    }
    public UmlModelElement getSupplier() {
        return supplier;
    }
    /**
     * @param element
     */
    public void setClient(UmlModelElement client) {
        if (this.client == client) return;
        if (this.client != null) {
            this.client.removeClientDependency(this);
        }
        this.client = client;
        if (client != null) {
            client.addClientDependency(this);
        }
    }

    /**
     * @param element
     */
    public void setSupplier(UmlModelElement supplier) {
        if (this.supplier == supplier) return;
        if (this.supplier != null) {
            this.supplier.removeSupplierDependency(this);
        }
        this.supplier = supplier;
        if (supplier != null) {
            supplier.addSupplierDependency(this);
        }
    }

}
