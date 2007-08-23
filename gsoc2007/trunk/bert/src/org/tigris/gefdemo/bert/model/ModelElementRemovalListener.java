package org.tigris.gefdemo.bert.model;

import java.util.EventListener;

/**
 * A listener which is called by the UML model whenever items
 * are removed from it.
 * @author Bob Tarling
 * @since 13-Jun-2004
 */
public interface ModelElementRemovalListener extends EventListener {
    void elementRemoved(ModelElementRemovalEvent event);
}
