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
import XML.SaxReader;
import XML.StaxWriter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import static javax.swing.UIManager.put;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

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
    JButton save = new JButton("SaveAsImg");
    JButton undo = new JButton("Undo");
    JButton redo = new JButton("Redo");
    JButton xmlsave = new JButton("SaveAsXml");
    JButton xmlopen = new JButton("Open");

    public final void initGUI() {

        Action action = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                zeichenpanel.Undo();
            }
        };
        String keyStrokeAndKey = "control Z";
        KeyStroke keyStroke = KeyStroke.getKeyStroke(keyStrokeAndKey);
        this.getRootPane().getInputMap().put(keyStroke, keyStrokeAndKey);
        this.getRootPane().getActionMap().put(keyStrokeAndKey, action);

        Action action1 = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                zeichenpanel.Redo();
            }
        };
        String keyStrokeAndKey1 = "control Y";
        KeyStroke keyStroke1 = KeyStroke.getKeyStroke(keyStrokeAndKey1);
        this.getRootPane().getInputMap().put(keyStroke1, keyStrokeAndKey1);
        this.getRootPane().getActionMap().put(keyStrokeAndKey1, action1);

        this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        this.setFocusable(true);
        this.setLayout(new BorderLayout());

        this.xmlopen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc1 = new JFileChooser("C:/");

                XMLReader xmlReader = null;
                try {
                    xmlReader = XMLReaderFactory.createXMLReader();
                } catch (SAXException ex) {
                    System.out.println("kein Pfad ausgewählt");
                }
                SaxReader stxrd = new SaxReader();
                int a1 = fc1.showOpenDialog(xmlopen);
                if (fc1.getSelectedFile().getPath() == null) {
                    return;
                }
                String path1 = fc1.getSelectedFile().getPath();
                // Pfad zur XML Datei
                FileReader reader = null;
                try {
                    reader = new FileReader(path1);
                } catch (FileNotFoundException ex) {
                    System.out.println("kein Pfad ausgewählt");
                }
                InputSource inputSource = new InputSource(reader);

                xmlReader.setContentHandler(stxrd);
                try {
                    // Parsen wird gestartet
                    xmlReader.parse(inputSource);
                } catch (IOException | SAXException ex) {
                    System.out.println("kein Pfad ausgewählt");
                }

                zeichenpanel.commmandList = stxrd.elemente;

                System.out.println(path1 + " geoeffnet");
                zeichenpanel.repaint();
            }
        });

        this.xmlsave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String path = null;
                try {
                    StaxWriter staxwriter = new StaxWriter();
                    final JFileChooser fc = new JFileChooser("C:/");
                    FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter(
                            ".xml", ".xml");
                    fc.setFileFilter(xmlFilter);
                    int a = fc.showSaveDialog(xmlsave);

                    if (fc.getSelectedFile().getPath() != null) {
                        path = fc.getSelectedFile().getPath();
                        if (path.endsWith(path)) {
                            path = path + ".xml";
                        }
                    }

                    if (path != null) {
                        staxwriter.writer = staxwriter.outputFactory.createXMLEventWriter(
                                new FileOutputStream(path));

                        StoreProject(staxwriter);
                    }
                } catch (Exception ex) {
                    System.out.println("kein Pfad ausgewählt");

                }
                System.out.println("Gespeichert in " + path);
            }
        });

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
        auswahlpanel.add(xmlsave);
        auswahlpanel.add(xmlopen);
        auswahlpanel.add(undo);
        auswahlpanel.add(redo);
        zeichenpanel.setBackground(Color.white);
        this.add(auswahlpanel, BorderLayout.NORTH);
        this.add(zeichenpanel, BorderLayout.SOUTH);

        undo.addActionListener(action);
        redo.addActionListener(action1);

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

        this.setFocusable(true);
        shape_chooser.addItemListener(new ShapeManager(this, zeichenpanel));
        Forground_color_chooser.addItemListener(new ColorItemListener(this, zeichenpanel, "For"));
        Background_color_chooser.addItemListener(new ColorItemListener(this, zeichenpanel, "Back"));
        Background_color_chooser.setSelectedItem("White");

    }

    public void StoreProject(StaxWriter staxwriter) throws Exception {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
    // create XMLEventWriter

        // create an EventFactory
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        // create and write Start Tag
        StartDocument startDocument = eventFactory.createStartDocument();
        staxwriter.writer.add(startDocument);

        // create config open tag
        StartElement configStartElement = eventFactory.createStartElement("",
                "", "config");
        staxwriter.writer.add(configStartElement);
        staxwriter.writer.add(end);

        zeichenpanel.STAXStore(staxwriter, eventFactory);
        staxwriter.writer.add(eventFactory.createEndElement("", "", "config"));
        staxwriter.writer.add(end);
        staxwriter.writer.add(eventFactory.createEndDocument());

        staxwriter.writer.close();

    }
}
