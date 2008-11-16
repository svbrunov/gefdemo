package org.tigris.gefdemo.uml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
            InputStream inputStream = ConnectionConstrainer.class.getResourceAsStream("/org/tigris/gefdemo/uml/graphmodel.xml");
            InputSource inputSource = new InputSource(inputStream);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            return db.parse(inputSource);
        } catch (FileNotFoundException e) {
            throw new GraphModelException(e);
        } catch (ParserConfigurationException e) {
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