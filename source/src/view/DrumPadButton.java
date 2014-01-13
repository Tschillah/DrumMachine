package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import strategies.IImageAnalyzer;

/**
 * Represents a single button on the drum pad. Each button as a background (part of the whole image that has been
 * uploaded) and a status (enabled/disabled)
 * @author Chilla
 *
 */
public class DrumPadButton extends JButton {
	
	boolean enabled;
	BufferedImage bg;
	
	public DrumPadButton(BufferedImage background) {
		
		this.enabled = false;
		this.bg = background;
		
		// Set the background, button size, etc.
		if (bg != null){
			setSize(bg.getWidth(), bg.getHeight());
			ImageIcon bgicon = new ImageIcon(bg);
			setIcon(bgicon);	
			setMargin(new Insets(0, 0, 0, 0));
			setBorder(new EmptyBorder(new Insets(1, 1, 1, 1)));
		}


		// Add a listener to toggle enabled on click
		this.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				toggle();				
			}
		});
		

	}
	
	/*
	 * Toggles this button state from enabled to disabled or from disabled to enabled
	 * and repaint the control
	 */
	public void toggle() {
		this.enabled = !enabled;
		repaint();
	}
	
	/*
	 * Sets the state of this button and repaints the control
	 */
	public void setState(boolean state) {
		this.enabled = state;
		repaint();
	}
	
	/*
	 * Returns the current state of this button
	 */
	public boolean getState() {
		return this.enabled;
	}
	
	/*
	 * Sets enabled according to the result of the given analyzer and repaints the control
	 */
	public void analyze(IImageAnalyzer analyzer){
		this.enabled = analyzer.analyze(bg);
		repaint();
	}
	
	/*
	 * Returns the current background image of this button
	 */
	public BufferedImage getImage(){
		return bg;
	}
	

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		// If this button is enabled, we add an additional grey layer over it
		if(enabled){
			Rectangle clipRectangle = g.getClipBounds();
			g.setColor(new Color(190,190,190,200));
			g.fillRect(0, 0, clipRectangle.width,clipRectangle.height);
		} 
	}
	
	/*
	 * Sets the background of this button and repaints it
	 */
	public void setBackground(BufferedImage background){
		this.bg = background;
		ImageIcon bgicon = new ImageIcon(bg);
		this.setIcon(bgicon);
		repaint();
	}
	
	/*
	 * Sets the border color of this button and repaints it
	 */
	public void setBorderColor(Color c){
		setBorder(new LineBorder(c));
		repaint();
	}
	


}
