/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommandClasses;

import Drawer.CDrawLine;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 *
 * @author 3flim
 */
public class CDrawReceiver implements Drawer {

    String s;
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

    public Rectangle getrect(){
        return rect;
    }
    
    public ArrayList getlines(){
        return lines;
    }
    public boolean isPaintable() {
        return paintable;
    }

    public void setPaintable(boolean paintable) {
        this.paintable = paintable;
    }

    public CDrawReceiver(Rectangle rect, Color c, String s) {
        this.rect = rect;
        this.c = c;
        this.s = s;
    }

    public CDrawReceiver(ArrayList lines, Color c, String s) {
        this.lines = lines;
        this.c = c;
        this.s = s;
    }

    public CDrawReceiver(Ellipse2D oval, Color c, String s) {
        this.oval = oval;
        this.c = c;
        this.s = s;
    }

    public CDrawReceiver(Image img, int x, int y, String s) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.s = s;
    }

    @Override
    public void draw(Graphics g) {

        switch (s) {
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
            case "Smiley":
                g.drawImage(img, x, y, null);
        }

    }

}
