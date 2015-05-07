/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drawer;

import CommandClasses.CDrawReceiver;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import mydraw.ShapeDrawer;
import mydraw.Zeichenpanel;

/**
 *
 * @author 3welge
 */
public class ScribbleDrawer extends ShapeDrawer implements GeneralDrawer {

    Zeichenpanel gui;
    Graphics g;

    ArrayList<Integer> xpos = new ArrayList();
    ArrayList<Integer> ypos = new ArrayList();
    ArrayList<Integer> xposakt = new ArrayList();
    ArrayList<Integer> yposakt = new ArrayList();

    public ScribbleDrawer(Zeichenpanel gui) {
        this.gui = gui;
    }

    int lastx, lasty;

    public void mousePressed(MouseEvent e) {
        lastx = e.getX();
        lasty = e.getY();
       
    }

    public void mouseDragged(MouseEvent e) {
        g = gui.getGraphics();
        Graphics g1 = gui.getImage().getGraphics();

        xpos.add(lastx);
        ypos.add(lasty);

        int x = e.getX(), y = e.getY();
        xposakt.add(x);
        yposakt.add(y);

        g.setColor(gui.color);
        g1.setColor(gui.color);

        g.setPaintMode();
        g1.setPaintMode();

        // g.drawLine(lastx, lasty, x, y);
        //g1.drawLine(lastx, lasty, x, y);
        lastx = x;
        lasty = y;

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        CDrawReceiver drawable = new CDrawReceiver(xpos, ypos, xposakt, yposakt, gui.color, "Scribble");
        gui.getCommmandList().add(drawable);

    }

    public void doDraw(int x0, int y0, int x1, int y1, Graphics g) {

    }

}
