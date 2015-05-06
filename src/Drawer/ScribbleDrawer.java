/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drawer;

import java.awt.Graphics;
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

    public ScribbleDrawer(ZeichenPanel gui) {
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

        int x = e.getX(), y = e.getY();
        g.setColor(gui.color);
        g1.setColor(gui.color);

        g.setPaintMode();
        g1.setPaintMode();

        g.drawLine(lastx, lasty, x, y);
        g1.drawLine(lastx, lasty, x, y);

        lastx = x;
        lasty = y;
    }

    public void doDraw(int x0, int y0, int x1, int y1, Graphics g) {

    }
    
    
    
   

    public Graphics getG() {
        return g;
    }

    
    public void draw(Graphics g) {

    }
}
