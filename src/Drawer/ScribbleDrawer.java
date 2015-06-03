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
        //scribble Problem:

        int x = e.getX(), y = e.getY();

       

        // g.drawLine(lastx, lasty, x, y);
        //     g1.drawLine(lastx, lasty, x, y);
        lines.add(new CDrawLine(lastx, lasty, x, y));
        lastx = x;
        lasty = y;

       if (drawable != null) {

            gui.getCommmandList().remove(drawable);
            gui.getCommmandList().size();
            gui.repaint();
        }
        drawable = new CDrawReceiver(lines, gui.color, "Scribble");

        gui.getCommmandList().add(drawable);

        gui.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

       
        gui.getCommmandList().remove(drawable);
      
        
        ArrayList<CDrawLine> lines1 = new ArrayList(lines);

        
        CDrawReceiver drawablefinal = new CDrawReceiver(lines1, gui.color, "Scribble");
        gui.getCommmandList().add(drawablefinal);
        gui.repaint();

    }

    public void doDraw(int x0, int y0, int x1, int y1, Graphics g) {

    }

}
