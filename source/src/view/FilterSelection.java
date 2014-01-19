package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Model;
import strategies.ColorAnalyzer;
import strategies.GrayScaleAnalyzer;
import strategies.NullAnalyzer;
import strategies.RandomAnalyzer;
import strategies.YUVAnalyzer;
import framework.INotifyable;

public class FilterSelection extends JPanel implements INotifyable {

	/*
	 * GUI part for selecitng the filterFilterSelectionFilterSelection
	 */

	Model model = Model.getInstance();

	// Controls
	JLabel lblSpeed;
	JLabel lblThreshold;
	JTextField txtSpeed;
	JSlider sldrBPM;
	JSlider sldrThreshold;
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
		sldrBPM = new JSlider(60, 800, 128);
		txtSpeed = new JTextField();
		txtSpeed.setPreferredSize(new Dimension(60, 20));
		txtSpeed.setText("200");

		lblThreshold = new JLabel("Threshold: ");
		sldrThreshold = new JSlider(0, 255, 128);

		sldrBPM.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				System.out.println(sldrBPM.getValue());
				model.setBPM(sldrBPM.getValue());
				txtSpeed.setText("" + sldrBPM.getValue());
			}
		});

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

				model.setBPM(speed);
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

		btnFilterRedColorAnalyzer = new JButton("R");
		btnFilterRedColorAnalyzer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new ColorAnalyzer(Color.RED, sldrThreshold
						.getValue()));
				btnFilterRedColorAnalyzer.setEnabled(false);
			}
		});

		btnFilterGreenColorAnalyzer = new JButton("G");
		btnFilterGreenColorAnalyzer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new ColorAnalyzer(Color.GREEN, sldrThreshold
						.getValue()));
				btnFilterGreenColorAnalyzer.setEnabled(false);
			}
		});

		btnFilterBlueColorAnalyzer = new JButton("B");
		btnFilterBlueColorAnalyzer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new ColorAnalyzer(Color.BLUE, sldrThreshold
						.getValue()));
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

		btnFilterGrayScaleAnalyzer = new JButton("B/W");
		btnFilterGrayScaleAnalyzer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new GrayScaleAnalyzer(sldrThreshold.getValue()));
				btnFilterGrayScaleAnalyzer.setEnabled(false);
			}
		});

		btnFilterYUVAnalyzer = new JButton("YUV");
		btnFilterYUVAnalyzer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new YUVAnalyzer(sldrThreshold.getValue()));
				btnFilterYUVAnalyzer.setEnabled(false);
			}
		});

		this.add(lblSpeed);
		this.add(sldrBPM);
		this.add(txtSpeed);
		this.add(btnPlay);
		this.add(btnStop);
		this.add(btnNone);
		filterButtons.add(btnNone);

		this.add(btnAll);
		filterButtons.add(btnAll);

		this.add(btnFilterRandomAnalyzer);
		filterButtons.add(btnFilterRandomAnalyzer);

		this.add(btnFilterRedColorAnalyzer);
		filterButtons.add(btnFilterRedColorAnalyzer);

		this.add(btnFilterGreenColorAnalyzer);
		filterButtons.add(btnFilterGreenColorAnalyzer);

		this.add(btnFilterBlueColorAnalyzer);
		filterButtons.add(btnFilterBlueColorAnalyzer);

		this.add(btnFilterGrayScaleAnalyzer);
		filterButtons.add(btnFilterGrayScaleAnalyzer);

		this.add(btnFilterYUVAnalyzer);
		filterButtons.add(btnFilterYUVAnalyzer);

		this.add(lblThreshold);
		this.add(sldrThreshold);

	}

	public void toggleFilter() {

	}

	@Override
	public void update() {
		for (JButton b : filterButtons) {
			b.setEnabled(true);
		}

	}
}
