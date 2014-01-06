package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JPanel;

import model.Model;


public class DrumPad extends JPanel {

	Model context = Model.getInstance();
	int linecount = context.getLineCount();
	int colcount = context.getColumnCount();


	/*
	 * Contains the main part of the GUI, the sample rows with the image in the background
	 */
	public DrumPad(){

		this.setLayout(new GridLayout(linecount,colcount, 0,0));
		this.setSize(830, 600);
		this.setMaximumSize(new Dimension(830, 600));
		this.setPreferredSize(new Dimension(830, 600));
		//this.setLayout(null);

		init();
	}
	
	public void init(){
		for(int y=0; y<linecount; y++){

			for(int x=0; x<colcount; x++){
				DrumPadButton btn = new DrumPadButton(x, y, context.getImagePart(x, y));
			//	btn.setEnabled(false);
				context.setButton(x,y,btn);
				
				this.add(btn).setLocation(x, y);
				
			}
		}
	}
	
	/*
	 * Method to highlight column of currently played buttons
	 * @author Caroline, Marlene
	 */
	public void highlightActiveButtons(int col){
		int colOld = 0;
		
		// vorgängige col entfaerben
		if(col > 0){
			colOld = col - 1;
		} else {
			colOld = linecount;
		}
		for(int y = 0; y < linecount; y++){
			context.getButton(colOld,y).setBorderColor(Color.WHITE);
		}
		
		// aktuelle col einfaerben
		for(int y = 0; y < linecount; y++){
			context.getButton(col,y).setBorderColor(Color.BLACK);
		}
	}

}
