package view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import model.Model;


public class DrumPad extends JPanel {


	Model context = Model.getInstance();


	/*
	 * Contains the main part of the GUI, the sample rows with the image in the background
	 */
	public DrumPad(){

		this.setLayout(new GridLayout(6,16, 0,0));
		this.setSize(830, 600);
		this.setMaximumSize(new Dimension(830, 600));
		this.setPreferredSize(new Dimension(830, 600));
		//this.setLayout(null);



		init();


	}
	
	public void init(){
		for(int y=0; y<6; y++){

			for(int x=0; x<16; x++){
				DrumPadButton btn = new DrumPadButton(x, y, context.getImagePart(x, y));
			//	btn.setEnabled(false);
				
				this.add(btn).setLocation(x, y);
				
			}
		}
	}
	
	


}
