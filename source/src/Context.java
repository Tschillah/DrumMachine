import javax.imageio.ImageIO;

import strategies.ColorAnalyzer;
import strategies.IImageAnalyzer;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.awt.*;

public class Context {
	
	final int LINECOUNT = 6;
	final int COLCOUNT = 16;
	final int BLOCKCOUNT = LINECOUNT * COLCOUNT;
	private BufferedImage image;
	private BufferedImage imageBlocks[][] = new BufferedImage[COLCOUNT][LINECOUNT];
	private boolean buttonStates[][] = new boolean[COLCOUNT][LINECOUNT];
	
	public Context(BufferedImage image) {
		this.image = image;
	}
	
	    public static void main(String[] args) throws IOException {
	    	BufferedImage image = ImageIO.read(new File("res/hongkong.jpg"));
	        
	        Context c = new Context(image);
	        c.imageDivider(image);
	        
	        System.out.println(image);
	        
	        // send image blocks to analyzer
	        for (int x = 0; x < c.COLCOUNT; x++) {
	            for (int y = 0; y < c.LINECOUNT; y++) {
	            	IImageAnalyzer ia = new ColorAnalyzer();
	            	c.buttonStates[x][y] = ia.analyze(c.imageBlocks[x][y]);
	            	System.out.println("x: " + x + ", y: " + y + " - " + c.imageBlocks[x][y]);
	            }
	        }
	    }

	    /**
	     * Divides image into 16*6 blocks
	     * @param image
	     */
	    public void imageDivider(BufferedImage image) {
	    	for (int x = 0; x < COLCOUNT; x++) {
	            for (int y = 0; y < LINECOUNT; y++) {
	            	imageBlocks[x][y] = new BufferedImage(getBlockWidth(), getBlockHeight(), image.getType());
	            }
	        }
	    }
	    
	    public int getBlockWidth(){
	    	return image.getWidth() / COLCOUNT;
	    }
	    
	    public int getBlockHeight(){
	    	return image.getHeight() / LINECOUNT;
	    }

}
