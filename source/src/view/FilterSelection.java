package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Model;


public class FilterSelection extends JPanel{

	/*
	 * GUI part for selecitng the filterFilterSelectionFilterSelection
	 */
	
	Model model = Model.getInstance();
	
	// Filters
	JButton btnFilterRedColorAnalyzer;
	JButton btnFilterBlueColorAnalyzer;
	
	public FilterSelection(){
		
		btnFilterRedColorAnalyzer = new JButton("Red");
		btnFilterRedColorAnalyzer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
		//		model.setFilter(new ColorAnalyzer(Color.RED));				
			}
		});
		
		
		
		btnFilterBlueColorAnalyzer = new JButton("Blue");
		
		
		this.add(new JButton());
		
		
	}

}
