/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydraw;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
        int expResult = 300;
        Draw drawtest = new Draw(0, 0, "white", "black");

        drawtest.setHeight(expResult);
        int result = drawtest.getHeigth();

        assertEquals("Height must be 300", expResult, result);

    }

    /**
     * Test of setWidth method, of class Draw.
     */
    @Test
    public void testSetWidth() {
        System.out.println("setWidth");
        Draw drawtest = new Draw(0, 0,  "white", "black");
        int expResult = 100;

        drawtest.setWidth(expResult);
        int result = drawtest.getWidth();
        assertEquals("Width must be 100", expResult, result);
        assertFalse(0 == result);
    }

    /**
     * Test of getWidth method, of class Draw.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        Draw drawtest = new Draw(100, 0, "white", "black");

        int expResult = 100;
        int result = drawtest.getWidth();
        assertEquals(expResult, result);
        assertFalse(0 == result);
    }

    /**
     * Test of getHeigth method, of class Draw.
     */
    @Test
    public void testGetHeigth() {
        System.out.println("getHeigth");
        Draw drawtest = new Draw(0, 300,  "white", "black");

        int expResult = 300;
        int result = drawtest.getHeigth();
        assertEquals(expResult, result);
        assertFalse(0 == result);
    }

    /**
     * Test of setFGColor method, of class Draw.
     */
    @Test
    public void testSetFGColor() throws Exception {

        Draw drawtest = new Draw(100, 0,  "white", "black");
        System.out.println("setFGColor");

        String expResult = "red";

        drawtest.setFGColor(expResult);
        String result = drawtest.getFGColor();
        assertEquals(expResult, result);
        assertFalse("0".equals(result));
    }

    /**
     * Test of getFGColor method, of class Draw.
     */
    @Test
    public void testGetFGColor() {
        System.out.println("getFGColor");

        Draw drawtest = new Draw(100, 0,  "white", "black");

        String expResult = "black";
        String result = drawtest.getFGColor();
        assertEquals(expResult, result);
        assertFalse("0".equals(result));
    }

    /**
     * Test of setBgColor method, of class Draw.
     */
    @Test
    public void testSetBgColor() throws Exception {
        System.out.println("setBgColor");
        Draw drawtest = new Draw(0, 0,  "White", "Black");
        String expResult = "Red";

        String new_Color = "Red";
        drawtest.setBgColor(new_Color);
        String result = drawtest.getBgColor();
        assertEquals(expResult, result);
        assertFalse("0".equals(result));

    }

    /**
     * Test of getBgColor method, of class Draw.
     */
    @Test
    public void testGetBgColor() {
        System.out.println("getBgColor");
        Draw drawtest = new Draw(0, 0,  "White", "Black");
        String expResult = "White";
        String result = drawtest.getBgColor();

        assertEquals(expResult, result);
        assertFalse("0".equals(result));

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

        Draw drawtest = new Draw();

        BufferedImage expResult = null;
        try {
            expResult = ImageIO.read(new File("TestBild.png"));
        } catch (IOException ex) {
            Logger.getLogger(DrawTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (expResult == null) {
            fail("kein Bild vorhanden");
        }

        try {
            drawtest.autodraw();
        } catch (ColorException ex) {
            Logger.getLogger(DrawTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedImage result = drawtest.getDrawing();

        int width = expResult.getWidth();
        int height = expResult.getHeight();
        int[][] clr = new int[width][height];

        int widthe = result.getWidth();
        int heighte = result.getHeight();
        int[][] clre = new int[widthe][heighte];

        int smw = 0;
        int smh = 0;
        int p = 0;

        if (width != widthe) {
            fail("width unterschiedlich");
        }
        if (height != heighte) {
            fail("height unterschiedlich");
        }

        for (int a = 0; a < width; a++) {
            for (int b = 0; b < height; b++) {
                clre[a][b] = result.getRGB(a, b);
                clr[a][b] = expResult.getRGB(a, b);
                if (clr[a][b] != clre[a][b]) {
                    fail("Unterschiedliche Bilder");
                }

            }
        }

    }
}
