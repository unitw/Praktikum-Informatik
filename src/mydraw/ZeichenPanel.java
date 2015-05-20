/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydraw;

import CommandClasses.CDrawReceiver;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author 3flim
 */
public class ZeichenPanel extends JPanel implements KeyListener {

    private int w;
    private int h;
    public Color color = Color.black;
    private BufferedImage image;
    ArrayList<CDrawReceiver> commmandList = new ArrayList();

    public ArrayList getCommmandList() {
        return commmandList;
    }

    public void setCommmandList(ArrayList commmandList) {
        this.commmandList = commmandList;
    }

    public ZeichenPanel(int w, int h) {

        this.addKeyListener(this);

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

    public void setImageBackground(Color c) {
        Graphics ig = image.getGraphics();
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

    public void saveImage(String name, String type, String Path) throws IOException {

        drawCommandListonImg();
        ImageIO.write(image, type, new File(Path + "." + type));

    }

    public void drawCommandList() {
        for (int i = 0; i < this.getCommmandList().size(); i++) {
            CDrawReceiver dr = (CDrawReceiver) this.getCommmandList().get(i);

            if (dr.isPaintable()) {
                dr.draw(this.getGraphics());
            }
        }
    }

    public void drawCommandListonImg() {
        for (int i = 0; i < this.getCommmandList().size(); i++) {
            CDrawReceiver dr = (CDrawReceiver) this.getCommmandList().get(i);
            dr.draw(image.getGraphics());
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (getCommmandList() != null) {
            this.drawCommandList();
        }
    }

    @Override
    public void repaint() {
        super.repaint();

        if (getCommmandList() != null) {
            this.drawCommandList();
        }

    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {

        if (KeyEvent.VK_CONTROL == ke.getKeyCode() && KeyEvent.VK_Z == ke.getKeyCode()) {
            Undo();
        }
        if (KeyEvent.VK_CONTROL == ke.getKeyCode() && KeyEvent.VK_Y == ke.getKeyCode()) {
            Redo();
        }

    }

    LinkedBlockingQueue<CDrawReceiver> undo = new LinkedBlockingQueue();

    public void Undo() {

        CDrawReceiver drawre = (CDrawReceiver) getCommmandList().get(getCommmandList().size());

        getCommmandList().remove(drawre);
        try {
            undo.put(drawre);
        } catch (InterruptedException ex) {
            Logger.getLogger(ZeichenPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        drawCommandList();
    }

    public void Redo() {

        CDrawReceiver drawre = undo.poll();
        drawCommandList();

    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

}
