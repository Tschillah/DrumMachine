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

import model.Model;
import framework.INotifyable;

public class DrumPadButton extends JButton implements INotifyable{
	
	private int x;
	private int y;
	BufferedImage bgEnabled;
	//BufferedImage bgDisabled;
	
	Model model = Model.getInstance();
	
	public DrumPadButton(int x, int y, BufferedImage bg) {
		this.x = x;
		this.y = y;
		bgEnabled = bg;
		
		
		model.addModelChangeListener(this);

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
		model.toggleButton(x, y);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if(model.getButtonState(x, y)){
			Rectangle clipRectangle = g.getClipBounds();
			g.setColor(new Color(190,190,190,200));
			g.fillRect(0, 0, clipRectangle.width,clipRectangle.height);
		} 
	}

	@Override
	public void update() {
		
		repaint();		

	}
	
	
	
	


}
