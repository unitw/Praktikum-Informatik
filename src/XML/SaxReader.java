/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import java.util.ArrayList;
import javax.swing.JComponent;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 *
 * @author win7
 */
public class SaxReader implements ContentHandler {

    static final String EDITOR = "Editor";
    static final String CGSLABEL = "CGSLabel";
    static final String CGSFORMATTEDTEXTFIELD = "CGSFormattedTextField";
    static final String XPOS = "xPos";
    static final String YPOS = "yPos";

    static final String BREITE = "breite";
    static final String HOEHE = "hoehe";
    static final String CONFIG = "config";

    public ArrayList<JComponent> elemente = new ArrayList<>();
    public String currentValue;

    public JComponent jcomp;

    String ElementID;
    String ElementClass;
    String Text;
    String Bezeichung;
    String Direction;
    int Fontsize;
    int xpos;
    int ypos;
    int breite;
    int hoehe;

    // Aktuelle Zeichen die gelesen werden, werden in eine Zwischenvariable
    // gespeichert
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        currentValue = new String(ch, start, length);
    }

    // Methode wird aufgerufen wenn der Parser zu einem Start-Tag kommt
    @Override
    public void startElement(String uri, String localName, String qName,
            org.xml.sax.Attributes atts) throws SAXException {

        if (localName.equals(EDITOR)) {

            jcomp = null;

        }
    }

    // Methode wird aufgerufen wenn der Parser zu einem End-Tag kommt

    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        if ((localName.equals(CGSLABEL))) {

            elemente.add(jcomp);

        }
        if ((localName.equals(CGSFORMATTEDTEXTFIELD))) {

            elemente.add(jcomp);
        }

    }

    //<editor-fold defaultstate="collapsed" desc="not implemented">
    public void endDocument() throws SAXException {
    }

    public void endPrefixMapping(String prefix) throws SAXException {
    }

    public void ignorableWhitespace(char[] ch, int start, int length)
            throws SAXException {
    }

    public void processingInstruction(String target, String data)
            throws SAXException {
    }

    public void setDocumentLocator(Locator locator) {
    }

    public void skippedEntity(String name) throws SAXException {
    }

    public void startDocument() throws SAXException {
    }

    public void startPrefixMapping(String prefix, String uri)
            throws SAXException {
    }
//</editor-fold>

    public void GetAttr(org.xml.sax.Attributes atts) {

        if (atts.getValue("xPos") != null) {
            xpos = Integer.parseInt(atts.getValue("xPos"));
            ypos = Integer.parseInt(atts.getValue("yPos"));
            breite = Integer.parseInt(atts.getValue("breite"));
            hoehe = Integer.parseInt(atts.getValue("hoehe"));
        }
//        if (atts.getValue("xPosAbsolut") != null) {
//            xpos = Integer.parseInt(atts.getValue("xPosAbsolut"));
//            ypos = Integer.parseInt(atts.getValue("yPosAbsolut"));
//            breite = Integer.parseInt(atts.getValue("breite"));
//            hoehe = Integer.parseInt(atts.getValue("hoehe"));
//            
//            Text = atts.getValue("Beschritung");
//            Fontsize = Integer.parseInt(atts.getValue("hoehe"));
//            Bezeichung = atts.getValue("Bezeichung");
//            Direction = atts.getValue("Direction");
//        }
    }

//   
}
