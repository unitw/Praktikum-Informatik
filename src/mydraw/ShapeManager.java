/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydraw;

import Drawer.OvalDrawer;
import Drawer.RectangleDrawer;
import Drawer.ScribbleDrawer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author 3welge
 */
public class ShapeManager implements ItemListener {

    Zeichenpanel gui;
    DrawSwingGUI drwing;
    // if this class is active, the mouse is interpreted as a pen
    // if this class is active, ovals are drawn
    ShapeDrawer currentDrawer;

    public ShapeManager(DrawSwingGUI drwing, Zeichenpanel itsGui) {
        ScribbleDrawer scribbleDrawer = new ScribbleDrawer(gui);
        gui = itsGui;

        this.drwing = drwing;

// default: scribble mode
        currentDrawer = scribbleDrawer;
        // activate scribble drawer
        gui.addMouseListener(currentDrawer);
        gui.addMouseMotionListener(currentDrawer);
    }

    // reset the shape drawer
    public void setCurrentDrawer(ShapeDrawer l) {
        if (currentDrawer == l) {
            return;
        }

        // deactivate previous drawer
        gui.removeMouseListener(currentDrawer);
        gui.removeMouseMotionListener(currentDrawer);
        // activate new drawer
        currentDrawer = l;
        gui.addMouseListener(currentDrawer);
        gui.addMouseMotionListener(currentDrawer);
    }

    // user selected new shape => reset the shape mode
    public void itemStateChanged(ItemEvent e) {

        setCurrentDrawer((ShapeDrawer) drwing.getdrawerHashtable().get(e.getItem().toString()));
    }
}
