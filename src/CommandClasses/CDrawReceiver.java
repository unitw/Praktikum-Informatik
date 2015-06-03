/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommandClasses;

import Drawer.CDrawLine;
import XML.StaxStore;
import XML.StaxWriter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author 3flim
 */
public class CDrawReceiver implements Drawer, StaxStore {

    String Identifier;
    Rectangle rect = null;
    Polygon poly = null;
    Color c = null;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
    int x, y, w, h;
    Ellipse2D oval;
    ArrayList<CDrawLine> lines;
    Image img;

    private boolean paintable;

    public Rectangle getrect() {
        return rect;
    }

    public ArrayList getlines() {
        return lines;
    }

    public boolean isPaintable() {
        return paintable;
    }

    public void setPaintable(boolean paintable) {
        this.paintable = paintable;
    }

    public CDrawReceiver(Rectangle rect, Color c, String s) {
        this.x = rect.x;
        this.y = rect.y;
        this.w = rect.width;
        this.h = rect.height;

        this.rect = rect;
        this.c = c;
        this.Identifier = s;
    }

    public CDrawReceiver(ArrayList lines, Color c, String s) {
        this.lines = lines;
        this.c = c;
        this.Identifier = s;
    }

    public CDrawReceiver(Ellipse2D oval, Color c, String s) {

        this.x = (int) oval.getX();
        this.y = (int) oval.getY();
        this.w = (int) oval.getWidth();
        this.h = (int) oval.getHeight();

        this.oval = oval;
        this.c = c;
        this.Identifier = s;
    }

    public CDrawReceiver(Image img, int x, int y, String s) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.w = img.getWidth(null);
        this.h = img.getHeight(null);
        this.Identifier = s;
    }

    @Override
    public void draw(Graphics g) {

        switch (Identifier) {
            case "Rectangle":

                g.setColor(c);
                g.drawRect(rect.x, rect.y, rect.width, rect.height);

                break;
            case "Oval":
                g.setColor(c);
                g.drawOval((int) oval.getX(), (int) oval.getY(), (int) oval.getWidth(), (int) oval.getHeight());

                break;
            case "Scribble":

                g.setColor(c);
                for (CDrawLine line : lines) {
                    g.drawLine(line.getX(), line.getY(), line.getXakt(), line.getYakt());
                }
                break;
                case "Triangle":

                g.setColor(c);
                for (CDrawLine line : lines) {
                    g.drawLine(line.getX(), line.getY(), line.getXakt(), line.getYakt());
                }
                
                
                break;
            case "Smiley":
                g.drawImage(img, x, y, null);
        }

    }

    @Override
    public void STAXStore(StaxWriter staxwriter, XMLEventFactory eventFactory) {

        Integer xpos = x;

        Integer ypos = y;

        Integer breite = this.w;

        Integer hoehe = this.h;

        String linespos = "";
        if (Identifier.equals("Scribble")||Identifier.equals("Triangle")) {
           
            
            
           for(CDrawLine lin:lines){
               linespos+= lin.toString();
           }
            
            try {        
                staxwriter.CreateMultiAttributeNode10(staxwriter.writer, Identifier, "xPos", xpos.toString(), "yPos", ypos.toString(), "breite", breite.toString(), "hoehe", hoehe.toString(), "Farbe", c.getRGB() + "","Lines",linespos, 1);
            } catch (XMLStreamException ex) {
                Logger.getLogger(CDrawReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        } else {
           
            if(c==null){
                c=Color.white;
                
            }
            
            try {
                staxwriter.CreateMultiAttributeNode8(staxwriter.writer, Identifier, "xPos", xpos.toString(), "yPos", ypos.toString(), "breite", breite.toString(), "hoehe", hoehe.toString(), "Farbe", c.getRGB() + "", 1);
            } catch (XMLStreamException ex) {
                Logger.getLogger(CDrawReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public int getxPos() {

        return this.getX();
    }

    @Override
    public void setxPos(String s) {

        this.x = Integer.parseInt(s);

    }

    @Override
    public int getyPos() {
        return this.y;
    }

    @Override
    public void setyPos(String s) {
        this.y = Integer.parseInt(s);
    }

    @Override
    public int getbreite() {
        return this.w;
    }

    @Override
    public void setbreite(String s) {
        this.w = Integer.parseInt(s);
    }

    @Override
    public int gethoehe() {
        return this.h;
    }

    @Override
    public void sethoehe(String s) {

        this.h = Integer.parseInt(s);
    }

    @Override
    public String getIdentifier() {
        return this.Identifier;
    }

    @Override
    public void setIdentifier(String s) {
        this.Identifier = s;
    }

}
