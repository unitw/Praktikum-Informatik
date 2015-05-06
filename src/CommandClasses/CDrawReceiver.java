/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommandClasses;

import Drawer.RectangleDrawer;
import java.awt.Graphics;
import java.util.LinkedList;
import mydraw.ZeichenPanel;

/**
 *
 * @author 3flim
 */
public class CDrawReceiver {

    RectangleDrawer rd;
    ZeichenPanel panel;
    Graphics g;
    LinkedList list = new LinkedList();

    
    
    public CDrawReceiver(ZeichenPanel panel, RectangleDrawer rd) {
        this.rd = rd;
        this.panel = panel;

    }

 public void execute(){
     
     rd.draw(panel.getGraphics());
 }

}
