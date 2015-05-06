/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drawer;

import CommandClasses.Drawer;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import mydraw.ShapeDrawer;
import mydraw.ZeichenPanel;

/**
 *
 * @author 3welge
 */
public class RectangleDrawer extends ShapeDrawer implements GeneralDrawer, Drawer {

    ZeichenPanel gui;
    Graphics g;

    public RectangleDrawer(ZeichenPanel gui) {
        this.gui = gui;
    }

    int pressx, pressy;
    int lastx = -1, lasty = -1;

    public Rectangle rect;

    // mouse pressed => fix first corner of rectangle

    public void mousePressed(MouseEvent e) {
        pressx = e.getX();
        pressy = e.getY();
    }

    // mouse released => fix second corner of rectangle
    // and draw the resulting shape
    public void mouseReleased(MouseEvent e) {
        Graphics g = gui.getGraphics();
        Graphics g1 = gui.getImage().getGraphics();

        if (lastx != -1) {
            // first undraw a rubber rect
            g.setXORMode(gui.color);
            g.setColor(gui.getBackground());
            doDraw(pressx, pressy, lastx, lasty, g);

            g1.setXORMode(gui.color);
            g1.setColor(gui.getBackground());
            doDraw(pressx, pressy, lastx, lasty, g1);

            lastx = -1;
            lasty = -1;
        }
        // these commands finish the rubberband mode
        g.setPaintMode();
        g.setColor(gui.color);
        g1.setPaintMode();
        g1.setColor(gui.color);
        // draw the finel rectangle
        doDraw(pressx, pressy, e.getX(), e.getY(), g);
        doDraw(pressx, pressy, e.getX(), e.getY(), g1);
    }

    // mouse released => temporarily set second corner of rectangle
    // draw the resulting shape in "rubber-band mode"
    public void mouseDragged(MouseEvent e) {
        g = gui.getGraphics();
        Graphics g1 = gui.getImage().getGraphics();

        // these commands set the rubberband mode
        g.setXORMode(gui.color);
        g.setColor(gui.getBackground());

        g1.setXORMode(gui.color);
        g1.setColor(gui.getBackground());

        if (lastx != -1) {
            // first undraw previous rubber rect
            doDraw(pressx, pressy, lastx, lasty, g);
            doDraw(pressx, pressy, lastx, lasty, g1);

        }
        lastx = e.getX();
        lasty = e.getY();
        // draw new rubber rect
        doDraw(pressx, pressy, lastx, lasty, g);
        doDraw(pressx, pressy, lastx, lasty, g1);
    }

    @Override
    public void doDraw(int x0, int y0, int x1, int y1, Graphics g) {
        // calculate upperleft and width/height of rectangle
        int x = Math.min(x0, x1);
        int y = Math.min(y0, y1);
        int w = Math.abs(x1 - x0);
        int h = Math.abs(y1 - y0);
        // draw rectangle

        //g.drawRect(x, y, w, h);
        rect = new Rectangle(x, y, w, h);

    }

    

    @Override
    public void draw(Graphics g) {
            g.drawRect(rect.x, rect.y, rect.width, rect.height);
        
    }

}
