package org.tigris.gefdemo.classdiagram.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.xerces.parsers.DOMParser;
import org.tigris.gef.graph.GraphModelException;
import org.tigris.gef.graph.XmlConnectionConstrainer;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * 
 * @author Bob Tarling
 * @since 25-May-2004
 */
public class ConnectionConstrainer extends XmlConnectionConstrainer {

    private static ConnectionConstrainer instance;

    public static synchronized ConnectionConstrainer getInstance() throws GraphModelException {
        if (instance == null) {
            instance = new ConnectionConstrainer();
        }
        return instance;
    }
    
    private ConnectionConstrainer() throws GraphModelException {
        super(getDocument());
    }

    private static Document getDocument() throws GraphModelException {
        try {
            File file = new File("src/org/tigris/gefdemo/classdiagram/model/graphmodel.xml");
            FileInputStream inputStream = null;
            inputStream = new FileInputStream(file);
            InputSource inputSource = new InputSource(inputStream);
            DOMParser parser = new DOMParser();
            parser.parse(inputSource);
            return parser.getDocument();
        } catch (FileNotFoundException e) {
            throw new GraphModelException(e);
        } catch (DOMException e) {
            throw new GraphModelException(e);
        } catch (SAXException e) {
            throw new GraphModelException(e);
        } catch (IOException e) {
            throw new GraphModelException(e);
        }
    }
}