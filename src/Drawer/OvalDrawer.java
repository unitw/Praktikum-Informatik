/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drawer;

import CommandClasses.CDrawReceiver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import mydraw.ZeichenPanel;

/**
 *
 * @author 3welge
 */
public class OvalDrawer extends RectangleDrawer implements GeneralDrawer {

    public OvalDrawer(ZeichenPanel gui) {
        super(gui);
    }
    CDrawReceiver drawable = null;

    public void mousePressed(MouseEvent e) {
        pressx = e.getX();
        pressy = e.getY();
    }
    Ellipse2D oval;
    CDrawReceiver clear;

    // mouse released => fix second corner of rectangle
    // and draw the resulting shape
    public void mouseReleased(MouseEvent e) {
      

        if (lastx != -1) {
       
            doDraw(pressx, pressy, lastx, lasty);

        
            doDraw(pressx, pressy, lastx, lasty);

            lastx = -1;
            lasty = -1;
        }
        // these commands finish the rubberband mode
       
        doDraw(pressx, pressy, e.getX(), e.getY());
        doDraw(pressx, pressy, e.getX(), e.getY());

        if (drawable != null) {
            gui.getCommmandList().remove(drawable);
            gui.getCommmandList().size();

        } else {
            return;
        }

        CDrawReceiver drawfinal = new CDrawReceiver(oval, gui.color, "Oval");
        gui.getCommmandList().add(drawfinal);

         gui.repaint();
    }

    // mouse released => temporarily set second corner of rectangle
    // draw the resulting shape in "rubber-band mode"
    public void mouseDragged(MouseEvent e) {
     

        if (lastx != -1) {
            // first undraw previous rubber rect
            doDraw(pressx, pressy, lastx, lasty);
            doDraw(pressx, pressy, lastx, lasty);

        }
        lastx = e.getX();
        lasty = e.getY();
        // draw new rubber rect
        doDraw(pressx, pressy, lastx, lasty);
        doDraw(pressx, pressy, lastx, lasty);

        if (drawable != null) {

            gui.getCommmandList().remove(drawable);
            gui.getCommmandList().size();
           

        }
        drawable = new CDrawReceiver(oval, gui.color, "Oval");

        gui.getCommmandList().add(drawable);

         gui.repaint();

    }

    @Override
    public void doDraw(int x0, int y0, int x1, int y1) {
        int x = Math.min(x0, x1);
        int y = Math.min(y0, y1);
        int w = Math.abs(x1 - x0);
        int h = Math.abs(y1 - y0);
        // draw oval instead of rectangle
        //g.drawOval(x, y, w, h);
        oval = new Ellipse2D.Double(x, y, w, h);

    }
}
