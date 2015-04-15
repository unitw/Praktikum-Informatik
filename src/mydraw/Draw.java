/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydraw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
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

    static DrawSwingGUI gui;

    public static void main(String[] args) {

        JButton automal = new JButton("Auto malen");
        automal.setPreferredSize(new Dimension(50, 25));
        automal.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {

                        try {
                            Draw.autodraw();
                        } catch (ColorException ex) {
                            Logger.getLogger(Draw.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

            }
        });
        gui = new DrawSwingGUI();
        gui.setSize(500, 400);
        gui.auswahlpanel.add(automal);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }

    public static void setHeight(int Height) {
        JPanel pan = gui.getZeichenpanel();
        pan.setPreferredSize(new Dimension(pan.getWidth(), Height));
    }

    public static void setWidth(int Width) {
        JPanel pan = gui.getZeichenpanel();
        pan.setPreferredSize(new Dimension(Width, pan.getHeight()));
    }

    public int getWidth() {
        JPanel pan = gui.getZeichenpanel();
        return pan.getWidth();

    }

    public int getHeigth() {
        JPanel pan = gui.getZeichenpanel();
        return pan.getHeight();
    }

    public static void setFGColor(String fgcolor) throws ColorException {

        gui.setColor(fgcolor);

    }

    public String getFGColor() {
        return gui.getColor();
    }

    public static void setBgColor(String new_Color) throws ColorException {
        try {
            JPanel zpan = gui.getZeichenpanel();
            switch (new_Color) {
                case "white":
                    zpan.setBackground(Color.white);
                    break;
                case "black":
                    zpan.setBackground(Color.black);

                    break;
                case "green":
                    zpan.setBackground(Color.green);

                    break;
                case "red":
                    zpan.setBackground(Color.red);

                    break;
                case "blue":
                    zpan.setBackground(Color.blue);
                    break;
                default:
                    throw new ColorException();

            }
        } catch (ColorException ex) {
            System.err.println("Color not supported");
        }

    }

    public static String getBgColor() {
        String Bgcolor = gui.getZeichenpanel().getBackground().toString();
        String Bgcoloronly = Bgcolor.substring(14);
        return Bgcoloronly;
    }

    public static void drawRectangle(Point upper_left, Point lower_right) {

        Graphics g = gui.getZeichenpanel().getGraphics();
        g.drawRect(upper_left.x, upper_left.y, lower_right.x, lower_right.y);

    }

    public static void drawOval(Point upper_left, Point lower_right) {
        Graphics g = gui.getZeichenpanel().getGraphics();
        g.drawOval(upper_left.x, upper_left.y, lower_right.x, lower_right.y);
    }

    public static void drawPolyLine(List<Point> points) {
        Graphics g = gui.getZeichenpanel().getGraphics();
        int[] x = new int[points.size()];
        int[] y = new int[points.size()];

        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            x[i] = p.x;
            y[i] = p.y;

        }
        g.drawPolyline(x, y, points.size());
    }

    public static Image getDrawing() {
        BufferedImage awtImage = new BufferedImage(gui.zeichenpanel.getWidth(), gui.zeichenpanel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = awtImage.createGraphics();
        gui.getZeichenpanel().print(g);
        return awtImage;
    }

    public static void clear() {
        Color c = gui.zeichenpanel.getBackground();
        gui.zeichenpanel.repaint();
        gui.zeichenpanel.setBackground(c);
    }

    public static void autodraw() throws ColorException {

        //setBgColor("red");
       // setFGColor("green");

        List<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(100, 100));
        points.add(new Point(100, 200));
        points.add(new Point(300, 200));
        drawOval(new Point(0, 5), new Point(100, 100));
        drawRectangle(new Point(200, 0), new Point(100, 100));
        drawPolyLine(points);

    }

}

class DrawSwingGUI extends JFrame {

    Color color;
    JComboBox shape_chooser = new JComboBox();
    JComboBox color_chooser = new JComboBox();
    JLabel labelcolor = new JLabel("Color");
    JLabel labelshape = new JLabel("Shape");
    JButton buttonquit = new JButton("Quit");
    JButton buttonclear = new JButton("Clear");
    JPanel auswahlpanel = new JPanel();
    Zeichenpanel zeichenpanel = new Zeichenpanel();

    public String getColor() {
        String fgcolor = color.toString();
        String fgcoloronly = fgcolor.substring(14);
        return fgcoloronly;

    }

    public void setColor(String fgcolor) {
        try {
            switch (fgcolor) {
                case "white":
                    zeichenpanel.setForeground(Color.white);
                    break;
                case "black":
                    zeichenpanel.setForeground(Color.black);
                    break;
                case "green":
                    zeichenpanel.setForeground(Color.green);
                    break;
                case "red":
                    zeichenpanel.setForeground(Color.red);
                    break;
                case "blue":
                    zeichenpanel.setForeground(Color.blue);
                    break;
                default:
                    throw new ColorException();

            }
        } catch (ColorException ex) {

            System.err.println("Color not supported");
        }

    }

    public JComboBox getColor_chooser() {
        return color_chooser;
    }

    public void setColor_chooser(JComboBox color_chooser) {
        this.color_chooser = color_chooser;
    }

    public JPanel getAuswahlpanel() {
        return auswahlpanel;
    }

    public void setAuswahlpanel(JPanel auswahlpanel) {
        this.auswahlpanel = auswahlpanel;
    }

    public Zeichenpanel getZeichenpanel() {
        return zeichenpanel;
    }

    public void setZeichenpanel(Zeichenpanel zeichenpanel) {
        this.zeichenpanel = zeichenpanel;
    }

    public void initGUI() {
        this.setLayout(new BorderLayout());

        this.setPreferredSize(new Dimension(500, 400));
        zeichenpanel.setPreferredSize(new Dimension(400, 300));
        color = Color.black;
        auswahlpanel.add(labelshape);
        auswahlpanel.add(shape_chooser);
        auswahlpanel.add(labelcolor);
        auswahlpanel.add(color_chooser);
        auswahlpanel.add(buttonclear);
        auswahlpanel.add(buttonquit);
        zeichenpanel.setBackground(Color.white);
        this.add(auswahlpanel, BorderLayout.NORTH);
        this.add(zeichenpanel, BorderLayout.SOUTH);

        buttonclear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                DrawSwingGUI.this.repaint();

            }
        });
        buttonquit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                DrawSwingGUI.this.dispose();

            }
        });
        shape_chooser.addItem("Scribble");
        shape_chooser.addItem("Rectangle");
        shape_chooser.addItem("Oval");

        color_chooser.addItem("Black");
        color_chooser.addItem("Green");
        color_chooser.addItem("Red");
        color_chooser.addItem("Blue");
    }

    public DrawSwingGUI() {

        initGUI();
        class ShapeManager implements ItemListener {

            Zeichenpanel gui;

            abstract class ShapeDrawer
                    extends MouseAdapter implements MouseMotionListener {

                public void mouseMoved(MouseEvent e) { /* ignore */ }
            }

            // if this class is active, the mouse is interpreted as a pen
            class ScribbleDrawer extends ShapeDrawer {

                int lastx, lasty;

                public void mousePressed(MouseEvent e) {
                    lastx = e.getX();
                    lasty = e.getY();
                }

                public void mouseDragged(MouseEvent e) {
                    Graphics g = gui.getGraphics();
                    int x = e.getX(), y = e.getY();
                    g.setColor(gui.color);
                    g.setPaintMode();
                    g.drawLine(lastx, lasty, x, y);
                    lastx = x;
                    lasty = y;
                }
            }

            // if this class is active, rectangles are drawn
            class RectangleDrawer extends ShapeDrawer {

                int pressx, pressy;
                int lastx = -1, lasty = -1;

                // mouse pressed => fix first corner of rectangle
                public void mousePressed(MouseEvent e) {
                    pressx = e.getX();
                    pressy = e.getY();
                }

                // mouse released => fix second corner of rectangle
                // and draw the resulting shape
                public void mouseReleased(MouseEvent e) {
                    Graphics g = gui.getGraphics();
                    if (lastx != -1) {
                        // first undraw a rubber rect
                        g.setXORMode(gui.color);
                        g.setColor(gui.getBackground());
                        doDraw(pressx, pressy, lastx, lasty, g);
                        lastx = -1;
                        lasty = -1;
                    }
                    // these commands finish the rubberband mode
                    g.setPaintMode();
                    g.setColor(gui.color);
                    // draw the finel rectangle
                    doDraw(pressx, pressy, e.getX(), e.getY(), g);
                }

                // mouse released => temporarily set second corner of rectangle
                // draw the resulting shape in "rubber-band mode"
                public void mouseDragged(MouseEvent e) {
                    Graphics g = gui.getGraphics();
                    // these commands set the rubberband mode
                    g.setXORMode(gui.color);
                    g.setColor(gui.getBackground());
                    if (lastx != -1) {
                        // first undraw previous rubber rect
                        doDraw(pressx, pressy, lastx, lasty, g);

                    }
                    lastx = e.getX();
                    lasty = e.getY();
                    // draw new rubber rect
                    doDraw(pressx, pressy, lastx, lasty, g);
                }

                public void doDraw(int x0, int y0, int x1, int y1, Graphics g) {
                    // calculate upperleft and width/height of rectangle
                    int x = Math.min(x0, x1);
                    int y = Math.min(y0, y1);
                    int w = Math.abs(x1 - x0);
                    int h = Math.abs(y1 - y0);
                    // draw rectangle
                    g.drawRect(x, y, w, h);
                }
            }

            // if this class is active, ovals are drawn
            class OvalDrawer extends RectangleDrawer {

                public void doDraw(int x0, int y0, int x1, int y1, Graphics g) {
                    int x = Math.min(x0, x1);
                    int y = Math.min(y0, y1);
                    int w = Math.abs(x1 - x0);
                    int h = Math.abs(y1 - y0);
                    // draw oval instead of rectangle
                    g.drawOval(x, y, w, h);
                }
            }

            ScribbleDrawer scribbleDrawer = new ScribbleDrawer();
            RectangleDrawer rectDrawer = new RectangleDrawer();
            OvalDrawer ovalDrawer = new OvalDrawer();
            ShapeDrawer currentDrawer;

            public ShapeManager(Zeichenpanel itsGui) {
                gui = itsGui;
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
                if (e.getItem().equals("Scribble")) {
                    setCurrentDrawer(scribbleDrawer);
                } else if (e.getItem().equals("Rectangle")) {
                    setCurrentDrawer(rectDrawer);
                } else if (e.getItem().equals("Oval")) {
                    setCurrentDrawer(ovalDrawer);
                }
            }
        }

        shape_chooser.addItemListener(new ShapeManager(zeichenpanel));

        class ColorItemListener implements ItemListener {

            // user selected new color => store new color in DrawGUI
            public void itemStateChanged(ItemEvent e) {
                if (e.getItem().equals("Black")) {
                    color = Color.black;
                } else if (e.getItem().equals("Green")) {
                    color = Color.green;
                } else if (e.getItem().equals("Red")) {
                    color = Color.red;
                } else if (e.getItem().equals("Blue")) {
                    color = Color.blue;
                }
            }
        }
        color_chooser.addItemListener(new ColorItemListener());

    }

}
