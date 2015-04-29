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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author 3welge
 */
public class DrawSwingGUI extends JFrame {

    Color fgcolor;
    JComboBox shape_chooser = new JComboBox();
    JComboBox color_chooser = new JComboBox();
    JLabel labelcolor = new JLabel("Color");
    JLabel labelshape = new JLabel("Shape");
    JButton buttonquit = new JButton("Quit");
    JButton buttonclear = new JButton("Clear");
    JPanel auswahlpanel = new JPanel();
    Zeichenpanel zeichenpanel = new Zeichenpanel(400, 300);

    private ConcurrentHashMap<String, Color> ht = new ConcurrentHashMap<>();

    public ConcurrentHashMap getHashtable() {
        return ht;
    }

    public String getColor() {
        Color fgcolor = zeichenpanel.color;
        String r = fgcolor.getRed() + "";
        String g = fgcolor.getGreen() + "";
        String b = fgcolor.getBlue() + "";
        String rgb = r + g + b;
        String fgc = "";
        switch (rgb) {
            case "255255255":
                fgc = "white";
                break;
            case "000":
                fgc = "black";
                break;
            case "02550":
                fgc = "green";
                break;
            case "25500":
                fgc = "red";
                break;
            case "00255":
                fgc = "blue";
                break;
            case "":
                fgc = "not supported";
                break;
        }
        return fgc;

    }

    public void setColor(String fgcolor) {
        try {
            switch (fgcolor) {
                case "white":
                    zeichenpanel.setPaintColor(Color.white);
                    break;
                case "black":
                    zeichenpanel.setPaintColor(Color.black);
                    break;
                case "green":
                    zeichenpanel.setPaintColor(Color.green);
                    break;
                case "red":
                    zeichenpanel.setPaintColor(Color.red);
                    break;
                case "blue":
                    zeichenpanel.setPaintColor(Color.blue);
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

    public final void initGUI() {
        this.setLayout(new BorderLayout());
        fgcolor = Color.BLACK;
        this.setPreferredSize(new Dimension(500, 400));
        fgcolor = Color.black;
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

                zeichenpanel.clearImage();
                zeichenpanel.clear();

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

    public final void initColors() {

        ht.put("Black", Color.BLACK);
        ht.put("Green", Color.GREEN);
        ht.put("Red", Color.RED);
        ht.put("Blue", Color.BLUE);
    }

    public DrawSwingGUI() {
        initColors();
        initGUI();

        shape_chooser.addItemListener(new ShapeManager(zeichenpanel));
        color_chooser.addItemListener(new ColorItemListener(this, zeichenpanel));

    }

}
