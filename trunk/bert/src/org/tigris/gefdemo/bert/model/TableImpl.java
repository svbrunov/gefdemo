package org.tigris.gefdemo.bert.model;

import java.util.ArrayList;
import java.util.List;

class TableImpl extends ModelElementImpl implements Table {
    
    List<Attribute> attributes = new ArrayList<Attribute>();
    
    public TableImpl() {
        super();
    }
    
    public List<Attribute> getAttributes() {
	return attributes;
    }

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
    }
}
