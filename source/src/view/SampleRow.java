package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


import model.Context;


public class SampleRow extends JPanel{

	//private final int count = 16;
	
	Context context = Context.getInstance();
	private int[] sampleLine;
	
	
	/*
	 * A single row of drumpads for a specific sample
	 */
	public SampleRow(int count){
		
		sampleLine = new int[count];
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		for(int i=0; i<count; i++){
			JButton btn = new JButton("" + i);
			
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
