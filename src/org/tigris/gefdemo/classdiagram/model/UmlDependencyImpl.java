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
        UmlModelElementImpl oldClient = (UmlModelElementImpl)this.client;
        UmlModelElementImpl newClient = (UmlModelElementImpl)client;
        if (oldClient == newClient) return;
        if (oldClient != null) {
            oldClient.removeClientDependency(this);
        }
        this.client = newClient;
        if (newClient != null) {
            newClient.addClientDependency(this);
        }
    }

    /**
     * @param element
     */
    public void setSupplier(UmlModelElement supplier) {
        UmlModelElementImpl oldSupplier = (UmlModelElementImpl)this.supplier;
        UmlModelElementImpl newSupplier = (UmlModelElementImpl)supplier;
        if (oldSupplier == newSupplier) return;
        if (oldSupplier != null) {
            oldSupplier.removeSupplierDependency(this);
        }
        this.supplier = newSupplier;
        if (newSupplier != null) {
            newSupplier.addSupplierDependency(this);
        }
    }

}
