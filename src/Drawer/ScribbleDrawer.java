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
import mydraw.ShapeDrawer;
import mydraw.ZeichenPanel;

/**
 *
 * @author 3welge
 */
public class ScribbleDrawer extends ShapeDrawer implements GeneralDrawer {

    ZeichenPanel gui;
    Graphics g;

    int[] xpos = new int[99999];
    int[] ypos = new int[99999];

    public ScribbleDrawer(ZeichenPanel gui) {
        this.gui = gui;
    }

    int lastx, lasty;
    int i = 0;

    public void mousePressed(MouseEvent e) {
        lastx = e.getX();
        lasty = e.getY();
        xpos[i] = e.getX();
        ypos[i] = e.getY();
        i++;
    }

    public void mouseDragged(MouseEvent e) {
        g = gui.getGraphics();
        Graphics g1 = gui.getImage().getGraphics();

        int x = e.getX(), y = e.getY();

        g.setColor(gui.color);
        g1.setColor(gui.color);

        g.setPaintMode();
        g1.setPaintMode();

        g.drawLine(lastx, lasty, x, y);
        g1.drawLine(lastx, lasty, x, y);

        xpos[i] = x;
        ypos[i] = x;

        lastx = x;
        lasty = y;
        i++;

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Polygon poly = new Polygon();
        for (int i = 0; i < xpos.length; i++) {
            poly.addPoint(xpos[i], ypos[i]);

        }
        CDrawReceiver drawble = new CDrawReceiver(poly, g.getColor(), "Scribble");
    }

    public void doDraw(int x0, int y0, int x1, int y1, Graphics g) {

    }

}
