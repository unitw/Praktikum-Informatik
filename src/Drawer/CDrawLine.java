/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drawer;

/**
 *
 * @author 3flim
 */
public class CDrawLine {

    private int x;
    private int y;
    private int xakt;
    private int yakt;

    public CDrawLine(int x, int y, int xakt, int yakt) {
        this.x = x;
        this.y = y;
        this.xakt = xakt;
        this.yakt = yakt;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getXakt() {
        return xakt;
    }

    public void setXakt(int xakt) {
        this.xakt = xakt;
    }

    public int getYakt() {
        return yakt;
    }

    public void setYakt(int yakt) {
        this.yakt = yakt;
    }

}
