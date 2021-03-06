/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import CommandClasses.CDrawReceiver;
import Drawer.CDrawLine;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 *
 * @author win7
 */
public class SaxReader implements ContentHandler {

    public ArrayList<CDrawReceiver> elemente = new ArrayList<>();
    ArrayList<CDrawLine> linestri = new ArrayList();
    ArrayList<CDrawLine> linesscri = new ArrayList();
    public String currentValue;

    public CDrawReceiver draw;

    int xpos;
    int ypos;
    int breite;
    int hoehe;
    Color color;

    URL url = ClassLoader.getSystemClassLoader().getResource("Resources/smiley.jpg");

    Rectangle rect = null;
    Ellipse2D oval;
    String scribble;

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

        if (localName.equals("Scribble")) {
            scribble = "scribble";
            GetAttr(atts);
            draw = new CDrawReceiver(linesscri, color, "Scribble");
        }
        if (localName.equals("Rectangle")) {

            scribble = "";
            GetAttr(atts);
            draw = new CDrawReceiver(rect, color, "Rectangle");

        }
        if (localName.equals("Oval")) {
            scribble = "";
            GetAttr(atts);
            draw = new CDrawReceiver(oval, color, "Oval");

        }

        if (localName.equals("Triangle")) {
            scribble = "triangle";
            GetAttr(atts);
            draw = new CDrawReceiver(linestri, color, "Triangle");
        }

        if (localName.equals("Smiley")) {
            scribble = "";
            GetAttr(atts);
            try {
                Image img = ImageIO.read(url);
                draw = new CDrawReceiver(img, xpos, ypos, "Smiley");
            } catch (IOException ex) {
                Logger.getLogger(SaxReader.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    // Methode wird aufgerufen wenn der Parser zu einem End-Tag kommt
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        if ((localName.equals("Scribble"))) {
            elemente.add(draw);

        }
        if (localName.equals("Triangle")) {

            elemente.add(draw);
        }
        if ((localName.equals("Rectangle"))) {

            elemente.add(draw);

        }
        if ((localName.equals("Oval"))) {

            elemente.add(draw);

        }
        if ((localName.equals("Smiley"))) {

            elemente.add(draw);

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

        if (scribble.equals("scribble")) {
            String linepos;

            linepos = (atts.getValue("Lines"));

            String[] felder = linepos.split(",");
            String text = "";

            int posanfang = 0;

            for (int i = 0; i < felder.length / 4; i++) {

                int x = Integer.parseInt(felder[posanfang]);
                int y = Integer.parseInt(felder[1 + posanfang]);
                int x1 = Integer.parseInt(felder[2 + posanfang]);
                int y1 = Integer.parseInt(felder[3 + posanfang]);
                linesscri.add(new CDrawLine(x, y, x1, y1));

                posanfang += 4;

            }

        }

        if (scribble.equals("triangle")) {
            String linepos;

            linepos = (atts.getValue("Lines"));

            String[] felder = linepos.split(",");
            String text = "";

            int posanfang = 0;

            for (int i = 0; i < felder.length / 4; i++) {

                int x = Integer.parseInt(felder[posanfang]);
                int y = Integer.parseInt(felder[1 + posanfang]);
                int x1 = Integer.parseInt(felder[2 + posanfang]);
                int y1 = Integer.parseInt(felder[3 + posanfang]);
                linestri.add(new CDrawLine(x, y, x1, y1));

                posanfang += 4;

            }

        }

        if (atts.getValue("xPos") != null) {
            xpos = Integer.parseInt(atts.getValue("xPos"));
            ypos = Integer.parseInt(atts.getValue("yPos"));
            breite = Integer.parseInt(atts.getValue("breite"));
            hoehe = Integer.parseInt(atts.getValue("hoehe"));

            rect = new Rectangle(xpos, ypos, breite, hoehe);
            oval = new Ellipse2D.Float(xpos, ypos, breite, hoehe);
            int argb = Integer.parseInt(atts.getValue("Farbe"));

            int r = (argb) & 0xFF;
            int g = (argb >> 8) & 0xFF;
            int b = (argb >> 16) & 0xFF;
            int a = (argb >> 24) & 0xFF;
            this.color = new Color(argb);
        }
    }
}
