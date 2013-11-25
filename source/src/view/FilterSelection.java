package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Model;
import strategies.ColorAnalyzer;
import strategies.GrayScaleAnalyzer;


public class FilterSelection extends JPanel{

	/*
	 * GUI part for selecitng the filterFilterSelectionFilterSelection
	 */
	
	Model model = Model.getInstance();
	
	// Filters
	JButton btnFilterRedColorAnalyzer;
	JButton btnFilterGrayScaleAnalyzer;
	
	public FilterSelection(){
		
		
		
		btnFilterRedColorAnalyzer = new JButton("Red");
		btnFilterRedColorAnalyzer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new ColorAnalyzer(Color.RED));				
			}
		});
	
		
		
		btnFilterGrayScaleAnalyzer = new JButton("Grayscale");
		btnFilterGrayScaleAnalyzer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new GrayScaleAnalyzer());				
			}
		});
		
		this.add(btnFilterRedColorAnalyzer);
		this.add(btnFilterGrayScaleAnalyzer);
		
	}

}
