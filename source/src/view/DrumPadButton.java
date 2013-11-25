package view;

import java.awt.image.BufferedImage;

import javax.swing.JButton;

public class DrumPadButton extends JButton{
	
	private int x;
	private int y;
	private int witdh;
	private int height;
	private boolean state;
	private BufferedImage bg;
	
	public DrumPadButton(int x, int y, int witdh, int height, boolean state, BufferedImage bg) {
		this.x = x;
		this.y = y;
		this.witdh = witdh;
		this.height = height;
		this.state = state;
		this.bg = bg;
		
		
	}
	
	
	
	


}
