package org.tigris.gefdemo.bert.model;

class AttributeImpl extends ModelElementImpl implements Attribute {

    private Table table;
    
    public AttributeImpl(Table table) {
        super();
        this.table = table;
    }

    public Table getTable() {
	return table;
    }
}
