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
@Test
    public void testCompare() {
       long start = System.currentTimeMillis();
        int q=0;
            File file1 = new File("filename.txt");

        /* if file doesnt exists, then create it
        if (!file.exists()) {
                    file.createNewFile();
                }*/
        FileWriter fw = null;
        try {
            fw = new FileWriter(file1.getAbsoluteFile());
        } catch (IOException ex) {
            Logger.getLogger(DrawTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedWriter bw = new BufferedWriter(fw);

                File file= new File("2000.png");
            BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException ex) {
            Logger.getLogger(DrawTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        int width = image.getWidth(null);
            int height = image.getHeight(null);
        int[][] clr=  new int[width][height]; 
        File files= new File("2002.png");
            BufferedImage images = null;
        try {
            images = ImageIO.read(files);
        } catch (IOException ex) {
            Logger.getLogger(DrawTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        int widthe = images.getWidth(null);
            int heighte = images.getHeight(null);
        int[][] clre=  new int[widthe][heighte]; 
        int smw=0;
        int smh=0;
        int p=0;
            //CALUCLATING THE SMALLEST VALUE AMONG WIDTH AND HEIGHT
            if(width>widthe)
            { 
                smw =widthe;
            }
            else 
            {
                smw=width;
            }
            if(height>heighte)
            {
                smh=heighte;
            }
            else 
            {
                smh=height;
            }
            //CHECKING NUMBER OF PIXELS SIMILARITY
            for(int a=0;a<smw;a++)
            {
                for(int b=0;b<smh;b++)
                {
                    clre[a][b]=images.getRGB(a,b);
                    clr[a][b]=image.getRGB(a,b);
                    if(clr[a][b]==clre[a][b]) 
                    {
                        try {
                            p=p+1;
                            bw.write("\t");
                            bw.write(Integer.toString(a));
                            bw.write("\t");
                            bw.write(Integer.toString(b));
                            bw.write("\n");
                        } catch (IOException ex) {
                            Logger.getLogger(DrawTest.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else
                        q=q+1;
                }
            }

    float w,h=0;
    if(width>widthe) 
    {
        w=width;
    }
    else 
    {
        w=widthe;
    }
    if(height>heighte)
    { 
        h = height;
    }
    else
    {
        h = heighte;
    }
    }
}
