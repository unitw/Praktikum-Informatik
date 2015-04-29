/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydraw;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JPanel;

/**
 *
 * @author 3welge
 */
public class ColorItemListener implements ItemListener {

    Zeichenpanel pan;
    DrawSwingGUI gui;

    public ColorItemListener(DrawSwingGUI gui, Zeichenpanel pan) {
        this.gui = gui;
        this.pan = pan;
    }

    public void itemStateChanged(ItemEvent e) {

        pan.color = (Color) gui.getHashtable().get(e.getItem().toString());

    }
}
