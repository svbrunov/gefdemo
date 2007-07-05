package org.tigris.panelbeater;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

/**
 * @author Bob Tarling
 */
class InternalFrame extends JInternalFrame implements InternalFrameListener {

    /**
     * @param title
     */
    public InternalFrame(String title) {
    	super(title);
        addInternalFrameListener(this);
    }
    
    /* (non-Javadoc)
     * @see javax.swing.event.InternalFrameListener#internalFrameActivated(javax.swing.event.InternalFrameEvent)
     */
    public void internalFrameActivated(InternalFrameEvent e) {
    	// TODO Auto-generated method stub
    	
    }
    
    /* (non-Javadoc)
     * @see javax.swing.event.InternalFrameListener#internalFrameClosed(javax.swing.event.InternalFrameEvent)
     */
    public void internalFrameClosed(InternalFrameEvent e) {
        removeInternalFrameListener(this);
    }
    
    /**
     * @see javax.swing.event.InternalFrameListener#internalFrameClosing(javax.swing.event.InternalFrameEvent)
     */
    public void internalFrameClosing(InternalFrameEvent e) {
        System.out.println("The internal frame is closing");
        if (getContentPane().getComponent(0) instanceof InternalFrameListener) {
            InternalFrameListener listener = (InternalFrameListener)getContentPane().getComponent(0);
            System.out.println("Passing on event closing");
            listener.internalFrameClosing(e);
        }
    }
    
    /* (non-Javadoc)
     * @see javax.swing.event.InternalFrameListener#internalFrameDeactivated(javax.swing.event.InternalFrameEvent)
     */
    public void internalFrameDeactivated(InternalFrameEvent e) {
    	// TODO Auto-generated method stub
    	
    }
    
    /* (non-Javadoc)
     * @see javax.swing.event.InternalFrameListener#internalFrameDeiconified(javax.swing.event.InternalFrameEvent)
     */
    public void internalFrameDeiconified(InternalFrameEvent e) {
    	// TODO Auto-generated method stub
    	
    }
    
    /* (non-Javadoc)
     * @see javax.swing.event.InternalFrameListener#internalFrameIconified(javax.swing.event.InternalFrameEvent)
     */
    public void internalFrameIconified(InternalFrameEvent e) {
    	// TODO Auto-generated method stub
    	
    }
    
    /* (non-Javadoc)
     * @see javax.swing.event.InternalFrameListener#internalFrameOpened(javax.swing.event.InternalFrameEvent)
     */
    public void internalFrameOpened(InternalFrameEvent e) {
    	// TODO Auto-generated method stub
    	
    }
}
