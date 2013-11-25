package view;

import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DrumPadButton extends JButton{
	
	private int x;
	private int y;
	private boolean state;
	
	public DrumPadButton(int x, int y, boolean state, BufferedImage bg) {
		this.x = x;
		this.y = y;

		if (bg != null){
			setSize(bg.getWidth(), bg.getHeight());
			ImageIcon bgicon = new ImageIcon(bg);
			setIcon(bgicon);	
			setMargin(new Insets(0, 0, 0, 0));
		}

		
		this.state = state; 

	}
	
	/*
	 * Toggles this button state from enabled to disabled or from disabled to enabled
	 */
	public void toggle() {
		this.state = !state;
	}
	
	
	
	


}
