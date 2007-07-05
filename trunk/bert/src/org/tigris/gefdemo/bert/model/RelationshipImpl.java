package org.tigris.gefdemo.bert.model;

/** A NetEdge subclass to represent a dependency between tagets.
 */

class RelationshipImpl 
        extends ModelElementImpl
        implements Relationship {
    
    Attribute supplier;
    Attribute client;
    
    private String name;
    
    /** Construct a new Depends. */
    public RelationshipImpl() {
        super();
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
    public Attribute getClient() {
        return client;
    }
    public Attribute getSupplier() {
        return supplier;
    }
    /**
     * @param element
     */
    public void setClient(Attribute client) {
        AttributeImpl oldClient = (AttributeImpl)this.client;
        AttributeImpl newClient = (AttributeImpl)client;
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
    public void setSupplier(Attribute supplier) {
        AttributeImpl oldSupplier = (AttributeImpl)this.supplier;
        AttributeImpl newSupplier = (AttributeImpl)supplier;
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
