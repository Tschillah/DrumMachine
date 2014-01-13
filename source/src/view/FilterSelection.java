package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import framework.INotifyable;

public class FilterSelection extends JPanel implements INotifyable{

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
	
	ArrayList<JButton> filterButtons = new ArrayList<JButton>();

	public FilterSelection() {

		model.register(this);
		
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
		btnNone.setEnabled(false);
		btnNone.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new NullAnalyzer(false));
				btnNone.setEnabled(false);
			}
		});

		btnAll = new JButton("All");
		btnAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new NullAnalyzer(true));
				btnAll.setEnabled(false);
			}
		});

		btnFilterRedColorAnalyzer = new JButton("Red");
		btnFilterRedColorAnalyzer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new ColorAnalyzer(Color.RED, 128));
				btnFilterRedColorAnalyzer.setEnabled(false);
			}
		});

		btnFilterGreenColorAnalyzer = new JButton("Green");
		btnFilterGreenColorAnalyzer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new ColorAnalyzer(Color.GREEN));
				btnFilterGreenColorAnalyzer.setEnabled(false);
			}
		});

		btnFilterBlueColorAnalyzer = new JButton("Blue");
		btnFilterBlueColorAnalyzer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new ColorAnalyzer(Color.BLUE));
				btnFilterBlueColorAnalyzer.setEnabled(false);
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
				btnFilterGrayScaleAnalyzer.setEnabled(false);
			}
		});

		btnFilterYUVAnalyzer = new JButton("YUV");
		btnFilterYUVAnalyzer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new YUVAnalyzer());
				btnFilterYUVAnalyzer.setEnabled(false);
			}
		});

		this.add(lblSpeed);
		this.add(txtSpeed);
		this.add(btnPlay);
		this.add(btnStop);
		this.add(btnNone);
		filterButtons.add(btnNone);

		this.add(btnAll);
		filterButtons.add(btnAll);

		this.add(btnFilterRedColorAnalyzer);
		filterButtons.add(btnFilterRedColorAnalyzer);
		
		this.add(btnFilterGreenColorAnalyzer);
		filterButtons.add(btnFilterGreenColorAnalyzer);

		this.add(btnFilterBlueColorAnalyzer);
		filterButtons.add(btnFilterBlueColorAnalyzer);

		this.add(btnFilterRandomAnalyzer);
		filterButtons.add(btnFilterRandomAnalyzer);

		this.add(btnFilterGrayScaleAnalyzer);
		filterButtons.add(btnFilterGrayScaleAnalyzer);

		this.add(btnFilterYUVAnalyzer);
		filterButtons.add(btnFilterYUVAnalyzer);


	}
	
	public void toggleFilter(){
		
	}

	@Override
	public void update() {
		for (JButton b : filterButtons) {
			b.setEnabled(true);
		}
		
	}
}
