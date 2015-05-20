/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydraw;

import Drawer.GeneralDrawer;
import Drawer.OvalDrawer;
import Drawer.PictureDrawer;
import Drawer.RectangleDrawer;
import Drawer.ScribbleDrawer;
import Drawer.SquareDrawer;
import Drawer.TriangleDrawer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
    JComboBox Forground_color_chooser = new JComboBox();

    JComboBox Background_color_chooser = new JComboBox();

    JLabel labelshape = new JLabel("Shape");
    JButton buttonquit = new JButton("Quit");
    JButton buttonclear = new JButton("Clear");
    JPanel auswahlpanel = new JPanel();
    ZeichenPanel zeichenpanel = new ZeichenPanel(400, 300);
    JButton save = new JButton("Save");

    public final void initGUI() {
        this.setLayout(new BorderLayout());
        fgcolor = Color.BLACK;
        this.setPreferredSize(new Dimension(500, 400));
        fgcolor = Color.black;
        auswahlpanel.add(labelshape);
        auswahlpanel.add(shape_chooser);

        auswahlpanel.add(new JLabel("Foreground"));
        auswahlpanel.add(Forground_color_chooser);
        auswahlpanel.add(new JLabel("Background"));
        auswahlpanel.add(Background_color_chooser);
        auswahlpanel.add(buttonclear);
        auswahlpanel.add(buttonquit);
        auswahlpanel.add(save);
        zeichenpanel.setBackground(Color.white);
        this.add(auswahlpanel, BorderLayout.NORTH);
        this.add(zeichenpanel, BorderLayout.SOUTH);

        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser saveFile = new JFileChooser();

                int result = saveFile.showSaveDialog(null);

                String strFileName = saveFile.getSelectedFile().getName();
                String path = saveFile.getSelectedFile().getPath();

                try {
                    zeichenpanel.saveImage(strFileName, "png", path);
                } catch (IOException ex) {
                    Logger.getLogger(DrawSwingGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

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
        shape_chooser.addItem("Smiley");
        shape_chooser.addItem("Square");
        shape_chooser.addItem("Triangle");

        Forground_color_chooser.addItem("Black");
        Forground_color_chooser.addItem("Green");
        Forground_color_chooser.addItem("Red");
        Forground_color_chooser.addItem("Blue");

        Background_color_chooser.addItem("Black");
        Background_color_chooser.addItem("Green");
        Background_color_chooser.addItem("Red");
        Background_color_chooser.addItem("Blue");
        Background_color_chooser.addItem("White");

    }

    private ConcurrentHashMap<String, Color> colorhashtable = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, GeneralDrawer> drawerhastable = new ConcurrentHashMap<>();

    public ConcurrentHashMap getColorHashtable() {
        return colorhashtable;
    }

    public ConcurrentHashMap getdrawerHashtable() {
        return drawerhastable;
    }

    public String getColor() {
        Color fgcolor = zeichenpanel.color;
        String fgc = null;

        Enumeration e = colorhashtable.keys();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            Color c = colorhashtable.get(key);
            if (c == fgcolor) {
                fgc = key;
                break;
            }

        }
        return fgc;
    }

    public void setColor(String fgcolor) {

        try {
            if (colorhashtable.get(fgcolor) == null) {
                throw new ColorException();
            }
            zeichenpanel.setPaintColor(colorhashtable.get(fgcolor));

        } catch (ColorException ex) {

            System.err.println("Color not supported");
        }
    }
//soweit alles fertig

    public JComboBox getColor_chooser() {
        return Forground_color_chooser;
    }

    public void setColor_chooser(JComboBox color_chooser) {
        this.Forground_color_chooser = color_chooser;
    }

    public JPanel getAuswahlpanel() {
        return auswahlpanel;
    }

    public void setAuswahlpanel(JPanel auswahlpanel) {
        this.auswahlpanel = auswahlpanel;
    }

    public ZeichenPanel getZeichenpanel() {
        return zeichenpanel;
    }

    public void setZeichenpanel(ZeichenPanel zeichenpanel) {
        this.zeichenpanel = zeichenpanel;
    }

    public final void initColors() {

        colorhashtable.put("Black", Color.BLACK);
        colorhashtable.put("Green", Color.GREEN);
        colorhashtable.put("Red", Color.RED);
        colorhashtable.put("Blue", Color.BLUE);
        colorhashtable.put("White", Color.white);
    }

    public void initDrawers() {
        drawerhastable.put("Rectangle", new RectangleDrawer(zeichenpanel));
        drawerhastable.put("Scribble", new ScribbleDrawer(zeichenpanel));
        drawerhastable.put("Oval", new OvalDrawer(zeichenpanel));
        drawerhastable.put("Smiley", new PictureDrawer(zeichenpanel));
        drawerhastable.put("Square", new SquareDrawer(zeichenpanel));
        drawerhastable.put("Triangle", new TriangleDrawer(zeichenpanel));

    }

    public DrawSwingGUI() {
        initColors();
        initDrawers();
        initGUI();

        shape_chooser.addItemListener(new ShapeManager(this, zeichenpanel));
        Forground_color_chooser.addItemListener(new ColorItemListener(this, zeichenpanel, "For"));
        Background_color_chooser.addItemListener(new ColorItemListener(this, zeichenpanel, "Back"));
        Background_color_chooser.setSelectedItem("White");

    }

}
