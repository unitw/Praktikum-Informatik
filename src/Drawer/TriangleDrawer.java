/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drawer;

import CommandClasses.CDrawReceiver;
import CommandClasses.Drawer;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import static javafx.scene.paint.Color.color;
import mydraw.ShapeDrawer;
import mydraw.ZeichenPanel;

/**
 *
 * @author 3flim
 */
public class TriangleDrawer extends ShapeDrawer implements GeneralDrawer, Drawer {

    ZeichenPanel gui;
    Graphics g;

    ArrayList<CDrawLine> lines = new ArrayList();

    public TriangleDrawer(ZeichenPanel gui) {
        this.gui = gui;
    }
    CDrawReceiver drawable;
    int pressx, pressy;
    int lastx = -1, lasty = -1;

    public void mousePressed(MouseEvent e) {
        pressx = e.getX();
        pressy = e.getY();
    }

  
    public void mouseReleased(MouseEvent e) {
       
        doDraw(pressx, pressy, e.getX(), e.getY());
        doDraw(pressx, pressy, e.getX(), e.getY());
        
        
        if (drawable != null) {
            gui.getCommmandList().remove(drawable);
            gui.getCommmandList().size();
        }
        
        
          ArrayList<CDrawLine> lines1 = new ArrayList(lines);
        
        
        CDrawReceiver drawfinal = new CDrawReceiver(lines1, gui.color, "Triangle");
        gui.getCommmandList().add(drawfinal);
        gui.repaint();
    }

    // mouse released => temporarily set second corner of rectangle
    // draw the resulting shape in "rubber-band mode"
    public void mouseDragged(MouseEvent e) {
     
        lastx = e.getX();
        lasty = e.getY();
       
        

        doDraw(pressx, pressy, lastx, lasty);
        doDraw(pressx, pressy, lastx, lasty);

        if(drawable != null){
            gui.getCommmandList().remove(drawable);
        }
        
        
        drawable = new CDrawReceiver(lines, gui.color, "Triangle");
        gui.getCommmandList().add(drawable);
          gui.repaint();

    }

    CDrawLine l1 = null;
    CDrawLine l2 = null;
    CDrawLine l3 = null;

    
    public void doDraw(int x0, int y0, int x1, int y1) {
       
        int x = Math.min(x0, x1);
        int y = Math.min(y0, y1);
        int w = Math.abs(x1 - x0);
        int h = Math.abs(y1 - y0);
       
        
        Point point2 = new Point(x0 + w, y0);

        Point point3 = new Point(x0 + (w / 2), y0 - h);

        if (!lines.isEmpty()) {
            lines.remove(l1);
            lines.remove(l2);
            lines.remove(l3);
        }

        l1 = new CDrawLine(x0, y0, point2.x, point2.y);
        l2 = new CDrawLine(x0, y0, point3.x, point3.y);
        l3 = new CDrawLine(point2.x, point2.y, point3.x, point3.y);

        lines.add(l1);
        lines.add(l2);
        lines.add(l3);

    }

    @Override
    public void draw(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doDraw(int x0, int y0, int x1, int y1, Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
