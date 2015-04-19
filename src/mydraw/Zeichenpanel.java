/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydraw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author 3flim
 */
public class Zeichenpanel extends JPanel {

    private int w;
    private int h;
    public Color color = Color.black;
    private BufferedImage image;

    public Zeichenpanel(int w, int h) {
        this.w = w;
        this.h = h;
        this.setPreferredSize(new Dimension(w, h));
         image = new BufferedImage(584, 300, BufferedImage.TYPE_INT_ARGB);;
        Graphics gbg = image.createGraphics();
        gbg.setColor(color.white);
        gbg.fillRect(0, 0, 584, 300);

    }

    
    public BufferedImage getImage() {

        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void saveImage(String name, String type) {

        Graphics g = this.getGraphics();
      //  g.drawImage(image, 0, 0, null);

        try {
            ImageIO.write(image, type, new File(name + "." + type));
        } catch (IOException ex) {
            Logger.getLogger(Zeichenpanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
