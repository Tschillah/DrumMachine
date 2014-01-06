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

public class DrumPadButton extends JButton {
	
	boolean enabled;
	BufferedImage bg;
	
	public DrumPadButton(BufferedImage background) {
		
		this.enabled = false;
		this.bg = background;
		
		
		if (bg != null){
			setSize(bg.getWidth(), bg.getHeight());
			ImageIcon bgicon = new ImageIcon(bg);
			setIcon(bgicon);	
			setMargin(new Insets(0, 0, 0, 0));
			setBorder(new EmptyBorder(new Insets(1, 1, 1, 1)));
		}

		
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				toggle();				
			}
		});
		

	}
	
	/*
	 * Toggles this button state from enabled to disabled or from disabled to enabled
	 */
	public void toggle() {
		this.enabled = !enabled;
		repaint();
	}
	
	public void setState(boolean state) {
		this.enabled = state;
		repaint();
	}
	
	public boolean getState() {
		return this.enabled;
	}
	
	public void analyze(IImageAnalyzer analyzer){
		this.enabled = analyzer.analyze(bg);
		repaint();
	}
	
	public BufferedImage getImage(){
		return bg;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if(enabled){
			Rectangle clipRectangle = g.getClipBounds();
			g.setColor(new Color(190,190,190,200));
			g.fillRect(0, 0, clipRectangle.width,clipRectangle.height);
		} 
	}
	
	
	public void setBackground(BufferedImage background){
		this.bg = background;
		ImageIcon bgicon = new ImageIcon(bg);
		this.setIcon(bgicon);
		repaint();
	}
	
	
	public void setBorderColor(Color c){
		setBorder(new LineBorder(c));
		repaint();
	}
	


}
