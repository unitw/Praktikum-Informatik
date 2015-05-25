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
import mydraw.ZeichenPanel;

/**
 *
 * @author 3welge
 */
public class ScribbleDrawer extends ShapeDrawer implements GeneralDrawer {

    ZeichenPanel gui;
    Graphics g;

    ArrayList<CDrawLine> lines = new ArrayList();

    public ScribbleDrawer(ZeichenPanel gui) {
        this.gui = gui;
    }
    CDrawReceiver drawable = null;
    int lastx, lasty;

    public void mousePressed(MouseEvent e) {
        lastx = e.getX();
        lasty = e.getY();

    }

    public void mouseDragged(MouseEvent e) {
        g = gui.getGraphics();
        Graphics g1 = gui.getImage().getGraphics();
        //scribble Problem:

        int x = e.getX(), y = e.getY();

        g.setColor(gui.color);
        g1.setColor(gui.color);

        g.setPaintMode();
        g1.setPaintMode();

        // g.drawLine(lastx, lasty, x, y);
        //     g1.drawLine(lastx, lasty, x, y);
        lines.add(new CDrawLine(lastx, lasty, x, y));
        lastx = x;
        lasty = y;

       
        drawable = new CDrawReceiver(lines, gui.color, "Scribble");

        gui.getCommmandList().add(drawable);

        gui.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        CDrawReceiver drawable = new CDrawReceiver(lines, gui.color, "Scribble");
        gui.getCommmandList().add(drawable);
        gui.repaint();

    }

    public void doDraw(int x0, int y0, int x1, int y1, Graphics g) {

    }

}
