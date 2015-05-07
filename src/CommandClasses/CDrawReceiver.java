/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommandClasses;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
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
    int x, y, w, h;
    ArrayList<Integer> xpos;
    ArrayList<Integer> ypos;

    ArrayList<Integer> xposakt;
    ArrayList<Integer> yposakt;

    public CDrawReceiver(Rectangle rect, Color c, String s) {
        this.rect = rect;
        this.c = c;
        this.s = s;
    }

    public CDrawReceiver(ArrayList xpos, ArrayList ypos, ArrayList xposakt, ArrayList yposakt, Color c, String s) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.xposakt = xposakt;
        this.yposakt = ypos;
        this.c = c;
        this.s = s;
    }

    public CDrawReceiver(int x, int y, int w, int h, Color c, String s) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.c = c;
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
                g.drawOval(x, y, w, h);

                break;
            case "Scribble":
                g.setColor(c);
                for (int i = 0; i < xpos.size(); i++) {
                    g.drawLine(xpos.get(i), ypos.get(i), xposakt.get(i), yposakt.get(i));
                }

                break;

        }

    }

}
