/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydraw;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author 3flim
 */
public class Draw {

    DrawSwingGUI gui;

    public Draw() {
        gui = new DrawSwingGUI();

        JButton automal = new JButton("Auto malen");
        JButton save = new JButton("Save");
        save.setPreferredSize(new Dimension(100, 25));
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                String filename = new String("TestBild");

                try {

                    Draw.this.writeImage(filename);
                } catch (IOException ex) {
                    Logger.getLogger(Draw.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        automal.setPreferredSize(new Dimension(100, 25));
        automal.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {

                        try {
                            Draw.this.autodraw();
                        } catch (ColorException ex) {
                            Logger.getLogger(Draw.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

            }
        });
        gui.setSize(600, 400);
        gui.setLocationRelativeTo(null);
        gui.auswahlpanel.add(automal);

        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);

        gui.auswahlpanel.add(save);

    }

    public Draw(int zwitdh, int zheight, String BgColor, String FgColor) {
        this.gui = new DrawSwingGUI();

        setHeight(zheight);
        setWidth(zwitdh);
        try {
            setBgColor(BgColor);
            setFGColor(FgColor);
        } catch (ColorException ex) {
            Logger.getLogger(Draw.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setHeight(int Height) {
        JPanel pan = gui.getZeichenpanel();
        pan.setSize(new Dimension(pan.getWidth(), Height));
    }

    public void setWidth(int Width) {
        JPanel pan = gui.getZeichenpanel();
        pan.setSize(new Dimension(Width, pan.getHeight()));
    }

    public int getWidth() {
        JPanel pan = gui.getZeichenpanel();
        return pan.getWidth();

    }

    public int getHeigth() {
        JPanel pan = gui.getZeichenpanel();
        return pan.getHeight();
    }

    public void setFGColor(String fgcolor) throws ColorException {

        gui.setColor(fgcolor);

    }

    public String getFGColor() {
        return gui.getColor();
    }

    public void setBgColor(String new_Color) throws ColorException {
        JPanel zpan = gui.getZeichenpanel();
        try {

            if (gui.getColorHashtable().get(new_Color) == null) {
                throw new ColorException();
            }

            zpan.setBackground((Color) gui.getColorHashtable().get(new_Color));
            gui.getZeichenpanel().setImageBackground((Color) gui.getColorHashtable().get(new_Color));
        } catch (ColorException ex) {

            System.err.println("Color not supported");
        }
    }

    public String getBgColor() {

        Color Bgcolor = gui.getZeichenpanel().getBackground();

        String r = Bgcolor.getRed() + "";
        String g = Bgcolor.getGreen() + "";
        String b = Bgcolor.getBlue() + "";
        String rgb = r + g + b;
        String bgc = "";
        switch (rgb) {
            case "255255255":
                bgc = "White";
                break;
            case "000":
                bgc = "Black";
                break;
            case "02550":
                bgc = "Green";
                break;
            case "25500":
                bgc = "Red";
                break;
            case "00255":
                bgc = "Blue";
                break;
            case "":
                bgc = "not supported";
                break;
        }
        return bgc;

    }

    public void drawRectangle(Point upper_left, Point lower_right) {

        Graphics g = gui.getZeichenpanel().getGraphics();
        g.setColor(gui.zeichenpanel.color);
        Graphics g1 = gui.zeichenpanel.getImage().getGraphics();
        g1.setColor(gui.zeichenpanel.color);
        g.drawRect(upper_left.x, upper_left.y, lower_right.x, lower_right.y);
        g1.drawRect(upper_left.x, upper_left.y, lower_right.x, lower_right.y);

    }

    public void drawOval(Point upper_left, Point lower_right) {
        Graphics g = gui.getZeichenpanel().getGraphics();
        g.setColor(gui.zeichenpanel.color);
        Graphics g1 = gui.zeichenpanel.getImage().getGraphics();
        g1.setColor(gui.zeichenpanel.color);
        g.drawOval(upper_left.x, upper_left.y, lower_right.x, lower_right.y);
        g1.drawOval(upper_left.x, upper_left.y, lower_right.x, lower_right.y);

    }

    public void drawPolyLine(List<Point> points) {
        Graphics g = gui.getZeichenpanel().getGraphics();
        Graphics g1 = gui.zeichenpanel.getImage().getGraphics();
        g.setColor(gui.zeichenpanel.color);
        g1.setColor(gui.zeichenpanel.color);
        int[] x = new int[points.size()];
        int[] y = new int[points.size()];

        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            x[i] = p.x;
            y[i] = p.y;

        }
        g.drawPolyline(x, y, points.size());
        g1.drawPolyline(x, y, points.size());
    }

    public BufferedImage getDrawing() {
        BufferedImage awtImage = gui.zeichenpanel.getImage();
        return awtImage;
    }

    public void clear() {
        gui.zeichenpanel.clear();

    }

    public void autodraw() throws ColorException {

        setFGColor("red");
        setBgColor("green");
        List<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(100, 100));
        points.add(new Point(100, 200));
        points.add(new Point(300, 200));

        drawOval(new Point(0, 5), new Point(100, 100));
        drawRectangle(new Point(200, 0), new Point(100, 100));
        drawPolyLine(points);

    }

    public void writeImage(String Filename) throws IOException {

        gui.zeichenpanel.saveImage(Filename, "png");

    }
}
