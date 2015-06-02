/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydraw;

import CommandClasses.CDrawReceiver;
import XML.StaxStore;
import XML.StaxWriter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author 3flim
 */
public class ZeichenPanel extends JPanel implements KeyListener, StaxStore {

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

        Action action = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Undo();
            }
        };
        String keyStrokeAndKey = "control Z";
        KeyStroke keyStroke = KeyStroke.getKeyStroke(keyStrokeAndKey);
        this.getInputMap().put(keyStroke, keyStrokeAndKey);
        this.getActionMap().put(keyStrokeAndKey, action);

        Action action1 = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Redo();
            }
        };
        String keyStrokeAndKey1 = "control Y";
        KeyStroke keyStroke1 = KeyStroke.getKeyStroke(keyStrokeAndKey1);
        this.getInputMap().put(keyStroke1, keyStrokeAndKey1);
        this.getActionMap().put(keyStrokeAndKey1, action1);

        this.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.w = w;
        this.h = h;
        this.setPreferredSize(new Dimension(w, h));
        image = new BufferedImage(584, 300, BufferedImage.TYPE_INT_ARGB);;
        Graphics gbg = image.createGraphics();
        gbg.setColor(color.white);
        gbg.fillRect(0, 0, 584, 300);
    }

    public void clear() {

        int i =commmandList.size()-1;

        CDrawReceiver draw;

        while (!commmandList.isEmpty()) {
            draw = this.commmandList.get(i);

            commmandList.remove(draw);

            undo.push(draw);

            i--;
        }

        repaint();

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

//    public void drawCommandList() {
//        for (int i = 0; i < this.getCommmandList().size(); i++) {
//            CDrawReceiver dr = (CDrawReceiver) this.getCommmandList().get(i);
//
//            dr.draw(this.getGraphics());
//
//        }
//    }
    public void drawCommandListonImg() {
        for (int i = 0; i < this.getCommmandList().size(); i++) {
            CDrawReceiver dr = (CDrawReceiver) this.getCommmandList().get(i);
            dr.draw(image.getGraphics());
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        for (int i = 0; i < this.getCommmandList().size(); i++) {
            CDrawReceiver dr = (CDrawReceiver) this.getCommmandList().get(i);
            if (dr != null) {
                dr.draw(g2d);
            }
        }
        g2d.dispose();

    }

    @Override
    public void repaint() {
        super.repaint();

    }

    Stack<CDrawReceiver> undo = new Stack();

    public void Undo() {

        if (getCommmandList().isEmpty()) {
            try {
                throw new DrawListException();
            } catch (DrawListException ex) {
                System.err.println("Keine Elemente mehr da");
                return;
            }
        }

        CDrawReceiver drawre = (CDrawReceiver) getCommmandList().get(getCommmandList().size() - 1);

        getCommmandList().remove(drawre);

        undo.push(drawre);

        repaint();
    }

    public void Redo() {

//        if (undo.pop() != null) {
        CDrawReceiver drawre = null;

//            try {
//               
        if (undo.empty()) {
            try {
                throw new DrawListException();
            } catch (DrawListException ex) {
                System.err.println("Stack leer");
                return;
            }
        }
        drawre = undo.pop();
        getCommmandList().add(drawre);
        repaint();

//        }
    }

    @Override
    public void keyTyped(KeyEvent e
    ) {
    }

    @Override
    public void keyReleased(KeyEvent e
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke
    ) {

        if (KeyEvent.VK_Z == ke.getKeyCode() && (KeyEvent.CTRL_DOWN_MASK) == ke.getKeyCode()) {

            Undo();

        }
        if (KeyEvent.VK_CONTROL == ke.getKeyCode() && KeyEvent.VK_Y == ke.getKeyCode()) {
            Redo();
        }

    }

    @Override
    public void STAXStore(StaxWriter staxwriter, XMLEventFactory eventFactory) {
        try {
            StartElement sElement = eventFactory.createStartElement("", "", "Editor");

            staxwriter.writer.add(sElement);

            commmandList.stream().forEach((dr) -> {
                dr.STAXStore(staxwriter, eventFactory);
            });
            EndElement eElement = eventFactory.createEndElement("", "", "Editor");
            XMLEvent end = eventFactory.createDTD("\n");

            staxwriter.writer.add(eElement);
            staxwriter.writer.add(end);

        } catch (XMLStreamException ex) {
            Logger.getLogger(ZeichenPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public int getxPos() {
        return this.getX();
    }

    @Override
    public void setxPos(String s) {

    }

    @Override
    public int getyPos() {
        return this.getY();
    }

    @Override
    public void setyPos(String s) {

    }

    @Override
    public int getbreite() {
        return this.getWidth();
    }

    @Override
    public void setbreite(String s) {
    }

    @Override
    public int gethoehe() {
        return this.getHeight();
    }

    @Override
    public void sethoehe(String s) {
    }

    @Override
    public String getIdentifier() {
        return "Zeichenpane";
    }

    @Override
    public void setIdentifier(String s) {
    }
}
