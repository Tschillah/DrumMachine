package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Model;
import strategies.*;


public class FilterSelection extends JPanel{

	/*
	 * GUI part for selecitng the filterFilterSelectionFilterSelection
	 */
	
	Model model = Model.getInstance();
	
	// Filters
	JButton btnNone;
	JButton btnAll;
	JButton btnFilterRedColorAnalyzer;
	JButton btnFilterGreenColorAnalyzer;
	JButton btnFilterBlueColorAnalyzer;
	JButton btnFilterRandomAnalyzer;
	JButton btnFilterGrayScaleAnalyzer;
	
	public FilterSelection(){
		
		btnNone = new JButton("None");
		btnNone.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new NullAnalyzer(true));				
			}
		});
		
		btnAll = new JButton("All");
		btnAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new NullAnalyzer(false));				
			}
		});
		
		
		btnFilterRedColorAnalyzer = new JButton("Red");
		btnFilterRedColorAnalyzer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new ColorAnalyzer(Color.RED));				
			}
		});
		
		btnFilterGreenColorAnalyzer = new JButton("Green");
		btnFilterGreenColorAnalyzer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new ColorAnalyzer(Color.GREEN));				
			}
		});
		
		btnFilterBlueColorAnalyzer = new JButton("Blue");
		btnFilterBlueColorAnalyzer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new ColorAnalyzer(Color.BLUE));	
			}
		});
	
		
		btnFilterRandomAnalyzer = new JButton("Random");
		btnFilterRandomAnalyzer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new RandomAnalyzer());	
			}
		});		
		
		
		btnFilterGrayScaleAnalyzer = new JButton("Grayscale");
		btnFilterGrayScaleAnalyzer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new GrayScaleAnalyzer());	
			}
		});
		
		this.add(btnNone);
		this.add(btnAll);
		this.add(btnFilterRedColorAnalyzer);
		this.add(btnFilterGreenColorAnalyzer);
		this.add(btnFilterBlueColorAnalyzer);
		this.add(btnFilterRandomAnalyzer);
		this.add(btnFilterGrayScaleAnalyzer);
		
	}
	

}
