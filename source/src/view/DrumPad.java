package view;

import javax.swing.JPanel;

import model.Model;


public class DrumPad extends JPanel{

	/*
	 * Contains the main part of the GUI, the sample rows with the image in the background
	 */
	public DrumPad(Model model){
		
		this.add(new SampleRow(16,0));
		
		this.add(new SampleRow(16,1));
		this.add(new SampleRow(16,2));
		this.add(new SampleRow(16,3));
		this.add(new SampleRow(16,4));
		this.add(new SampleRow(16,5));
		
		
	}


}
