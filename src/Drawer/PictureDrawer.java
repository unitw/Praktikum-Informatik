/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drawer;

import CommandClasses.CDrawReceiver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import mydraw.ShapeDrawer;
import mydraw.ZeichenPanel;

/**
 *
 * @author 3flim
 */
public class PictureDrawer extends ShapeDrawer implements GeneralDrawer {

    ZeichenPanel gui;

    public PictureDrawer(ZeichenPanel gui) {
        this.gui = gui;
    }

    CDrawReceiver drawable;

    @Override

    public void mouseClicked(MouseEvent me) {
        try {

            URL url = ClassLoader.getSystemClassLoader().getResource("Resources/smiley.jpg");

            Image img = ImageIO.read(url);

            drawable = new CDrawReceiver(img, me.getX(), me.getY(), "Smiley");

            gui.getCommmandList().add(drawable);
            gui.drawCommandList();

        } catch (IOException ex) {
            Logger.getLogger(PictureDrawer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void doDraw(int x0, int y0, int x1, int y1, Graphics g) {

    }

}
