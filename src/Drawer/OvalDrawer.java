/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drawer;

import CommandClasses.CDrawReceiver;
import java.awt.Graphics;
import mydraw.ZeichenPanel;

/**
 *
 * @author 3welge
 */
public class OvalDrawer extends RectangleDrawer implements GeneralDrawer {

    public OvalDrawer(ZeichenPanel gui) {
        super(gui);
    }

    @Override
    public void doDraw(int x0, int y0, int x1, int y1, Graphics g) {
        int x = Math.min(x0, x1);
        int y = Math.min(y0, y1);
        int w = Math.abs(x1 - x0);
        int h = Math.abs(y1 - y0);
        // draw oval instead of rectangle
        // g.drawOval(x, y, w, h);
        CDrawReceiver drawble = new CDrawReceiver(x0, y0, x1, y1, g.getColor(), "Oval");

    }
}
