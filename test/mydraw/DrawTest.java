/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydraw;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tw
 */
public class DrawTest {

    public DrawTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class Draw.
     */
    /**
     * Test of setHeight method, of class Draw.
     */
    @Test
    public void testSetHeight() {
        System.out.println("setHeight");
        Draw drawtest = new Draw();
        int Height = 10;
        drawtest.setHeight(Height);

// TODO review the generated test code and remove the default call to fail.
        assertEquals("Height must be 10", Height, drawtest.gui.zeichenpanel.getHeight());

    }

    /**
     * Test of setWidth method, of class Draw.
     */
    @Test
    public void testSetWidth() {
        System.out.println("setWidth");
        Draw drawtest = new Draw();
        int Width = 10;
        drawtest.setWidth(Width);

        assertEquals("Width must be 10", Width, drawtest.gui.zeichenpanel.getWidth());

    }

    /**
     * Test of getWidth method, of class Draw.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        Draw drawtest = new Draw();
        drawtest.setWidth(100);
        int expResult = 100;
        int result = drawtest.getWidth();
        assertEquals(expResult, result);

    }

    /**
     * Test of getHeigth method, of class Draw.
     */
    @Test
    public void testGetHeigth() {
        System.out.println("getHeigth");
        Draw drawtest = new Draw();
        drawtest.setHeight(100);
        int expResult = 100;
        int result = drawtest.getHeigth();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFGColor method, of class Draw.
     */
    @Test
    public void testSetFGColor() throws Exception {
        System.out.println("setFGColor");
        Draw drawtest = new Draw();
        String fgcolor = "black";
        drawtest.setFGColor(fgcolor);

    }

    /**
     * Test of getFGColor method, of class Draw.
     */
    @Test
    public void testGetFGColor() {
        System.out.println("getFGColor");
        Draw instance = new Draw();
        String expResult = "";
        String result = instance.getFGColor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBgColor method, of class Draw.
     */
    @Test
    public void testSetBgColor() throws Exception {
        System.out.println("setBgColor");
        Draw drawtest = new Draw();
        String new_Color = "";
        drawtest.setBgColor(new_Color);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBgColor method, of class Draw.
     */
    @Test
    public void testGetBgColor() {
        System.out.println("getBgColor");
        Draw drawtest = new Draw();
        String expResult = "";
        String result = drawtest.getBgColor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawRectangle method, of class Draw.
     */
    /**
     * Test of getDrawing method, of class Draw.
     */
    @Test
    public void testGetDrawing() {
        System.out.println("getDrawing");
        Draw instance = new Draw();
        BufferedImage expResult = null;
        BufferedImage result = instance.getDrawing();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
