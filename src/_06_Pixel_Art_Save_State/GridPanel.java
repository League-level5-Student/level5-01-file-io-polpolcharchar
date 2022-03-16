package _06_Pixel_Art_Save_State;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JPanel;

public class GridPanel extends JPanel{

    private static final long serialVersionUID = 1L;
    private int windowWidth;
    private int windowHeight;
    private int pixelWidth;
    private int pixelHeight;
    private int rows;
    private int cols;

    // 1. Create a 2D array of pixels. Do not initialize it yet.
    Pixel[][] pixels;

    private Color color;

    public GridPanel(int w, int h, int r, int c) throws IOException {
//        this.windowWidth = w;
//        this.windowHeight = h;
//        this.rows = r;
//        this.cols = c;
        BufferedReader f = new BufferedReader(new FileReader("C:\\Users\\HomePC\\git\\level5-01-file-io-polpolcharchar\\src\\_06_Pixel_Art_Save_State\\artSave.txt"));
        this.windowWidth = Integer.parseInt(f.readLine());
        this.windowHeight = Integer.parseInt(f.readLine());
        this.rows = Integer.parseInt(f.readLine());
        this.cols = Integer.parseInt(f.readLine());
//        System.out.println(f.readLine());
//        System.out.println(f.readLine());
        f.close();
        
        
        this.pixelWidth = windowWidth / cols;
        this.pixelHeight = windowHeight / rows;

        color = Color.BLACK;

        setPreferredSize(new Dimension(windowWidth, windowHeight));

        // 2. Initialize the pixel array using the rows and cols variables.

        pixels = new Pixel[rows][cols];

        // 3. Iterate through the array and initialize each element to a new pixel.

        for(int i = 0; i<pixels.length; i++) {
        	for(int j = 0; j<pixels[i].length; j++) {
        		pixels[i][j] = new Pixel(i, j);
        	}
        }

    }

    public void setColor(Color c) {
        color = c;
    }

    public void clickPixel(int mouseX, int mouseY) {
        // 5. Use the mouseX and mouseY variables to change the color
        //    of the pixel that was clicked. *HINT* Use the pixel's dimensions.
    	pixels[mouseX / pixelWidth][mouseY / pixelHeight].color = color;
    	System.out.println(mouseX / pixelWidth);
    	
    }

    @Override
    public void paintComponent(Graphics g) {
        // 4. Iterate through the array.
        //    For every pixel in the list, fill in a rectangle using the pixel's color.
        //    Then, use drawRect to add a grid pattern to your display.
    	for(int i = 0; i<pixels.length; i++) {
    		for(int j = 0; j<pixels.length; j++) {
    			g.setColor(pixels[i][j].color);
    			g.fillRect(i * pixelWidth, j * pixelHeight, pixelWidth, pixelHeight);
    		}
    	}

    }
}
