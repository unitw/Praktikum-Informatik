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
public class ZeichenPanel extends JPanel {

    private int w;
    private int h;
    public Color color = Color.black;
    private BufferedImage image;

    public ZeichenPanel(int w, int h) {
        this.w = w;
        this.h = h;
        this.setPreferredSize(new Dimension(w, h));
        image = new BufferedImage(584, 300, BufferedImage.TYPE_INT_ARGB);;
        Graphics gbg = image.createGraphics();
        gbg.setColor(color.white);
        gbg.fillRect(0, 0, 584, 300);
    }

    public void clear() {
        this.setBackground(color.white);
        this.repaint();

    }

   public void setImageBackground(Color c){
       Graphics ig= image.getGraphics();
         ig.setColor(c);
        ig.fillRect(0, 0, 584, 300);
       
   }
    
    
    public void setPaintColor(Color c) {
        color = c;

    }

    public void clearImage() {
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

    public void saveImage(String name, String type) throws IOException {

        Graphics g = this.getGraphics();
        //  g.drawImage(image, 0, 0, null);

        File file = new File("ZeichenPanel1");
        if (file.exists()) {
            String filename = file.getName() + "1";
            ImageIO.write(image, type, new File(filename + "." + type));
        } else {
            ImageIO.write(image, type, new File(name + "." + type));
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
