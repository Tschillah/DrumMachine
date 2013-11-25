package view;

import javax.swing.JPanel;

import model.Context;


public class DrumPad extends JPanel{

	/*
	 * Contains the main part of the GUI, the sample rows with the image in the background
	 */
	public DrumPad(Context model){
		
		this.add(new SampleRow(16));
		this.add(new SampleRow(16));
		this.add(new SampleRow(16));
		this.add(new SampleRow(16));
		this.add(new SampleRow(16));
		this.add(new SampleRow(16));
		
	}

}
