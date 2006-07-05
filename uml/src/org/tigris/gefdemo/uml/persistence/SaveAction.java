// Copyright (c) 1996-99 The Regents of the University of California. All
// Rights Reserved. Permission to use, copy, modify, and distribute this
// software and its documentation without fee, and without a written
// agreement is hereby granted, provided that the above copyright notice
// and this paragraph appear in all copies.  This software program and
// documentation are copyrighted by The Regents of the University of
// California. The software program and documentation are supplied "AS
// IS", without any accompanying services from The Regents. The Regents
// does not warrant that the operation of the program will be
// uninterrupted or error-free. The end-user understands that the program
// was developed for research purposes and is advised not to rely
// exclusively on the program for any reason.  IN NO EVENT SHALL THE
// UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
// SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
// ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
// THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
// SUCH DAMAGE. THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
// PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
// CALIFORNIA HAS NO OBLIGATIONS TO PROVIDE MAINTENANCE, SUPPORT,
// UPDATES, ENHANCEMENTS, OR MODIFICATIONS.

// File: CmdSave.java
// Classes: CmdSave
// Original Author: jrobbins@ics.uci.edu
// $Id$

package org.tigris.gefdemo.uml.persistence;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.ResourceManager;
import org.tigris.gef.base.Editor;
import org.tigris.gef.base.Globals;
import org.tigris.gef.base.Layer;
import org.tigris.gefdemo.uml.CmdCreateNode;

/**
 * Save the 
 */

public class SaveAction extends AbstractAction {

    private static final long serialVersionUID = 6303842448377621194L;
    
    private static Log LOG = LogFactory.getLog(CmdCreateNode.class);
    
    public SaveAction(String title, Icon icon) {
        super(title, icon);
    }

    public SaveAction(String title) {
        super(title);
    }

    public SaveAction() {
        super();
    }

    public void actionPerformed(ActionEvent arg0) {
        try {
            Editor ce = Globals.curEditor();
            // TODO Should use JFileChooser
            FileDialog fd =
                new FileDialog(ce.findFrame(), "Save Diagram", FileDialog.SAVE);
            fd.setDirectory(Globals.getLastDirectory());
            fd.setVisible(true);
            String filename = fd.getFile(); // blocking
            String path = fd.getDirectory(); // blocking
            Globals.setLastDirectory(path);
            if (filename != null) {
                Globals.showStatus("Writing " + path + filename + "...");
                FileOutputStream f = new FileOutputStream(path + filename);
                ce.preSave();
                save(ce.getLayerManager().getActiveLayer(), f);
                Globals.showStatus("Wrote " + path + filename);
                f.close();
            }
        } catch (FileNotFoundException ignore) {
            System.out.println("got an FileNotFoundException");
        } catch (IOException ignore) {
            System.out.println("got an IOException");
            ignore.printStackTrace();
        }
    }
    
    private void save(Layer layer, FileOutputStream f) {
        try
        {
            
            VelocityEngine ve = new VelocityEngine();
            ve.setProperty("file.resource.loader.path", "c:/cvs/gefdemo/uml/src/org/tigris/gefdemo/uml/persistence");

            ve.init();
            
            /*
             *  Make a context object and populate with the data.  This 
             *  is where the Velocity engine gets the data to resolve the
             *  references (ex. $diagrams) in the template
             */

            VelocityContext context = new VelocityContext();
            context.put("diagrams", getLayers());
            
            /*
             *  get the Template object.  This is the parsed version of your 
             *  template input file.  Note that getTemplate() can throw
             *   ResourceNotFoundException : if it doesn't find the template
             *   ParseErrorException : if there is something wrong with the VTL
             *   Exception : if something else goes wrong (this is generally
             *        indicative of as serious problem...)
             */

            Template template =  null;

//            Properties p = new Properties(); 
//            p.setProperty( "file.resource.loader.path", "c:/cvs/gefdemo/uml/src/org/tigris/gefdemo/uml/persistence" );

            template = ve.getTemplate("diagram.vm");

            /*
             *  Now have the template engine process your template using the
             *  data placed into the context.  Think of it as a  'merge' 
             *  of the template and the data to produce the output stream.
             */

            BufferedWriter writer = writer = new BufferedWriter(
                new OutputStreamWriter(f));

            if ( template != null)
                template.merge(context, writer);

            /*
             *  flush and cleanup
             */

            writer.flush();
            writer.close();
        } catch( Exception e ) {
            LOG.error("oops", e);
        }
    }
    
    private List getLayers() {
        Editor ce = Globals.curEditor();
        ArrayList layers = new ArrayList(1);
        layers.add(ce.getLayerManager().getActiveLayer());
        return layers;
    }
} /* end class CmdSave */
