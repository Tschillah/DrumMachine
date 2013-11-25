package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import model.Model;


public class SampleRow extends JPanel{

	//private final int count = 16;
	
	Model context = Model.getInstance();
	private int[] sampleLine;
	
	
	/*
	 * A single row of drumpads for a specific sample
	 */
	public SampleRow(int count, int rowNumber){
		
		
		
		sampleLine = new int[count];
		this.setLayout(new GridLayout(1, count));
		
		
		
		for(int i=0; i<count; i++){
			DrumPadButton btn = new DrumPadButton(1, i, context.getImagePart(i, rowNumber));
			
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//context.setma
					
				}
			});
			
			this.add(btn);
		}
		
		
	}

}
