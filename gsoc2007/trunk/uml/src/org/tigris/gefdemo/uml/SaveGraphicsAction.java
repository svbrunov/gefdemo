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

import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.AbstractAction;

import org.freehep.graphicsio.gif.GIFGraphics2D;
import org.tigris.gef.base.Editor;
import org.tigris.gef.base.Globals;

public class SaveGraphicsAction extends AbstractAction {

    public SaveGraphicsAction() {
        super("Save as graphics using freehep...");
    }

    public void actionPerformed(ActionEvent e) {
        try {
            Editor ce = Globals.curEditor();
            // TODO Should use JFileChooser
            FileDialog fd = new FileDialog(ce.findFrame(), "Save Diagram",
                    FileDialog.SAVE);
            fd.setDirectory(Globals.getLastDirectory());
            fd.setVisible(true);
            String filename = fd.getFile(); // blocking
            String path = fd.getDirectory(); // blocking
            Globals.setLastDirectory(path);
            if (filename != null) {
                Globals.showStatus("Writing " + path + filename + "...");
                FileOutputStream s = new FileOutputStream(path + filename);

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

        System.out.println("width = " + drawingArea.width);
        System.out.println("height = " + drawingArea.height);
        Dimension dim = new Dimension(drawingArea.width, drawingArea.height);
        GIFGraphics2D ps = new GIFGraphics2D(s, dim);
        //        ps.translate(32,32+778);
        //        double scale=Math.min(535.0/drawingArea.width,
        //                  778.0/drawingArea.height);
        //        if (scale < 1.0) {
        //            ps.scale(scale,scale);
        //        }
        ps.translate(-drawingArea.x, -drawingArea.y);
//        ps.setClip(drawingArea.x, drawingArea.y, drawingArea.width,
//                drawingArea.height);
        // java bug if using Rectangle.shape() ???
        ce.print(ps);
        ps.closeStream();
        s.close();
        ps.dispose();
    }
}