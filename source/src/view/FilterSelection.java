package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Model;
import strategies.ColorAnalyzer;
import strategies.GrayScaleAnalyzer;
import strategies.NullAnalyzer;
import strategies.RandomAnalyzer;
import strategies.YUVAnalyzer;

public class FilterSelection extends JPanel {

	/*
	 * GUI part for selecitng the filterFilterSelectionFilterSelection
	 */

	Model model = Model.getInstance();

	// Controls
	JLabel lblSpeed;
	JTextField txtSpeed;
	JButton btnPlay;
	JButton btnStop;

	// Filters
	JButton btnNone;
	JButton btnAll;
	JButton btnFilterRedColorAnalyzer;
	JButton btnFilterGreenColorAnalyzer;
	JButton btnFilterBlueColorAnalyzer;
	JButton btnFilterRandomAnalyzer;
	JButton btnFilterGrayScaleAnalyzer;
	JButton btnFilterYUVAnalyzer;

	public FilterSelection() {

		lblSpeed = new JLabel("bpm: ");

		txtSpeed = new JTextField();
		txtSpeed.setPreferredSize(new Dimension(60, 20));
		txtSpeed.setText("200");

		btnPlay = new JButton("Play");
		btnPlay.setBackground(Color.GREEN);
		btnPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int speed = 200;
				try {
					speed = Integer.parseInt(txtSpeed.getText());
				} catch (NumberFormatException e1) {

				}

				model.setSpeed(speed);
				model.stopTactMachine();
				model.startTactMachine();
				btnStop.setEnabled(true);
				btnPlay.setEnabled(false);
			}
		});

		btnStop = new JButton("Stop");
		btnStop.setBackground(Color.RED);
		btnStop.setEnabled(false);
		btnStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// model.buildSound(0);
				model.stopTactMachine();
				btnStop.setEnabled(false);
				btnPlay.setEnabled(true);
			}
		});

		btnNone = new JButton("None");
		btnNone.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new NullAnalyzer(false));
			}
		});

		btnAll = new JButton("All");
		btnAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new NullAnalyzer(true));
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

		btnFilterYUVAnalyzer = new JButton("YUV");
		btnFilterYUVAnalyzer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new YUVAnalyzer());
			}
		});

		this.add(lblSpeed);
		this.add(txtSpeed);
		this.add(btnPlay);
		this.add(btnStop);
		this.add(btnNone);
		this.add(btnAll);
		this.add(btnFilterRedColorAnalyzer);
		this.add(btnFilterGreenColorAnalyzer);
		this.add(btnFilterBlueColorAnalyzer);
		this.add(btnFilterRandomAnalyzer);
		this.add(btnFilterGrayScaleAnalyzer);
		this.add(btnFilterYUVAnalyzer);

	}
	
	public void toggleFilter(){
		
	}
}
