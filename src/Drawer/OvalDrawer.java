/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drawer;

import java.awt.Graphics;
import mydraw.Zeichenpanel;

/**
 *
 * @author 3welge
 */
public class OvalDrawer extends RectangleDrawer implements Drawer {

    public OvalDrawer(Zeichenpanel gui) {
        super(gui);
    }

    public void doDraw(int x0, int y0, int x1, int y1, Graphics g) {
        int x = Math.min(x0, x1);
        int y = Math.min(y0, y1);
        int w = Math.abs(x1 - x0);
        int h = Math.abs(y1 - y0);
        // draw oval instead of rectangle
        g.drawOval(x, y, w, h);
    }
}
