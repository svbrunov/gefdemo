// Copyright (c) 1996-99 The Regents of the University of California. All
//Rights Reserved. Permission to use, copy, modify, and distribute this
//software and its documentation without fee, and without a written
//agreement is hereby granted, provided that the above copyright notice
//and this paragraph appear in all copies. This software program and
//documentation are copyrighted by The Regents of the University of
//California. The software program and documentation are supplied "AS
//IS", without any accompanying services from The Regents. The Regents
//does not warrant that the operation of the program will be
//uninterrupted or error-free. The end-user understands that the program
//was developed for research purposes and is advised not to rely
//exclusively on the program for any reason. IN NO EVENT SHALL THE
//UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
//SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
//ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
//THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
//SUCH DAMAGE. THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY
//WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
//MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
//PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
//CALIFORNIA HAS NO OBLIGATIONS TO PROVIDE MAINTENANCE, SUPPORT,
//UPDATES, ENHANCEMENTS, OR MODIFICATIONS.

package org.tigris.gefdemo.uml;

import java.awt.FileDialog;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.swing.AbstractAction;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;

import org.tigris.gef.base.Editor;
import org.tigris.gef.base.Globals;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

public class SaveSvgAction extends AbstractAction {

    public SaveSvgAction() {
        super("Save as SVG...");
    }

    public void actionPerformed(ActionEvent e) {
        try {
            Editor ce = Globals.curEditor();
            // TODO Should use JFileChooser
            FileDialog fd = new FileDialog(ce.findFrame(), "Save Diagram",
                    FileDialog.SAVE);
            fd.setDirectory(Globals.getLastDirectory());
            fd.show();
            String filename = fd.getFile(); // blocking
            String path = fd.getDirectory(); // blocking
            Globals.setLastDirectory(path);
            if (filename != null) {
                Globals.showStatus("Writing " + path + filename + "...");
                FileOutputStream f = new FileOutputStream(path + filename);
                OutputStream s = f;

                Rectangle drawingArea = ce.getLayerManager().getActiveLayer()
                        .calcDrawingArea();

                //  Tell the editor to hide the grid before exporting:

                boolean h = ce.getGridHidden();
                ce.setGridHidden(true);

                saveGraphics(s, ce, drawingArea);

                //  Restore old grid state:
                ce.setGridHidden(h);

            }
        } catch (FileNotFoundException ignore) {
            System.out.println("got an FileNotFoundException");
        } catch (IOException ignore) {
            System.out.println("got an IOException");
            ignore.printStackTrace();
        }
    }

    protected void saveGraphics(OutputStream s, Editor ce, Rectangle drawingArea)
            throws IOException {
        SVGGraphics2D svgGraphics2D = null;
        try {
            // Get a DOMImplementation
            DOMImplementation domImpl = GenericDOMImplementation
                    .getDOMImplementation();

            // Create an instance of org.w3c.dom.Document
            Document document = domImpl.createDocument(null, "svg", null);

            // Create an instance of the SVG Generator
            svgGraphics2D = new SVGGraphics2D(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (svgGraphics2D != null) {
            // Finally, stream out SVG to the standard output using UTF-8
            // character to byte encoding
            boolean useCSS = true; // we want to use CSS style attribute
            ce.print(svgGraphics2D);
            Writer out = new OutputStreamWriter(s, "UTF-8");
            svgGraphics2D.stream(out, useCSS);
            svgGraphics2D.dispose();
        }
    }
}