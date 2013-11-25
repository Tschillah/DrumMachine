package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class FilterSelection extends JPanel{

	/*
	 * GUI part for selecitng the filterFilterSelectionFilterSelection
	 */
	
	// Filters
	JButton btnFilterRedColorAnalyzer;
	JButton btnFilterBlueColorAnalyzer;
	
	public FilterSelection(){
		
		btnFilterRedColorAnalyzer = new JButton("Red");
		btnFilterRedColorAnalyzer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		btnFilterBlueColorAnalyzer = new JButton("Blue");
		
		
		this.add(new JButton());
		
		
	}

}
