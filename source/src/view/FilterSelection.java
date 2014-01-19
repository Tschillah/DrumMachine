package view;

import helpers.Parser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
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

	JLabel lblThreshold;
	JSlider sldrThreshold;
	JTextField txtTreshold;


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

		lblThreshold = new JLabel("Threshold: ");
		sldrThreshold = new JSlider(model.getTHRESHOLDMIN(),
				model.getTHRESHOLDMAX(), model.getTHRESHOLDDEFAULT());
		txtTreshold = new JTextField();
		txtTreshold.setPreferredSize(new Dimension(60, 20));
		txtTreshold.setText(Integer.toString(model.getTHRESHOLDDEFAULT()));
		txtTreshold.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Parser.validateIntRange(txtTreshold.getText(),
						model.getTHRESHOLDMIN(), model.getTHRESHOLDMAX())) {
					int threshold = Integer.parseInt(txtTreshold.getText());
					model.setThreshold(threshold);
					sldrThreshold.setValue(threshold);
				}
			}
		});

		sldrThreshold.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				System.out.println(sldrThreshold.getValue());
				model.setThreshold(sldrThreshold.getValue());
				txtTreshold.setText(Integer.toString(sldrThreshold.getValue()));
			}
		});


		btnNone = new JButton("None");
		btnNone.setEnabled(false);
		btnNone.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new NullAnalyzer(false));
				btnNone.setEnabled(false);
				sldrThreshold.setEnabled(false);
				txtTreshold.setEnabled(false);
			}
		});

		btnAll = new JButton("All");
		btnAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setFilter(new NullAnalyzer(true));
				btnAll.setEnabled(false);
				sldrThreshold.setEnabled(false);
				txtTreshold.setEnabled(false);
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
				sldrThreshold.setEnabled(false);
				txtTreshold.setEnabled(false);
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

		GridLayout buttonLayout = new GridLayout(2,1);
		this.setLayout(buttonLayout);
		
		JPanel row1 = new JPanel();
		JPanel row2 = new JPanel();
		this.add(row1);
		this.add(row2);
		
		row1.add(btnNone);
		filterButtons.add(btnNone);

		row1.add(btnAll);
		filterButtons.add(btnAll);

		row1.add(btnFilterRandomAnalyzer);
		filterButtons.add(btnFilterRandomAnalyzer);

		row1.add(btnFilterRedColorAnalyzer);
		filterButtons.add(btnFilterRedColorAnalyzer);

		row1.add(btnFilterGreenColorAnalyzer);
		filterButtons.add(btnFilterGreenColorAnalyzer);

		row1.add(btnFilterBlueColorAnalyzer);
		filterButtons.add(btnFilterBlueColorAnalyzer);

		row1.add(btnFilterGrayScaleAnalyzer);
		filterButtons.add(btnFilterGrayScaleAnalyzer);

		row1.add(btnFilterYUVAnalyzer);
		filterButtons.add(btnFilterYUVAnalyzer);

		row2.add(lblThreshold);
		row2.add(sldrThreshold);
		row2.add(txtTreshold);

	}

	public void toggleFilter() {

	}

	@Override
	public void update() {
		for (JButton b : filterButtons) {
			b.setEnabled(true);
		}
		sldrThreshold.setEnabled(true);
		txtTreshold.setEnabled(true);

	}
}
