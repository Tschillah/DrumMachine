package view;

import helpers.Parser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Model;

/**
 * GUI part for the beat selection (start/stop drum machine, set bpm)
 * @author Chilla
 *
 */
public class BeatControls extends JPanel {

	Model model = Model.getInstance();

	// Controls
	JLabel lblBPM;
	JSlider sldrBPM;
	JTextField txtBPM;

	JButton btnPlay;
	JButton btnStop;

	public BeatControls() {


		// BPM Components
		lblBPM = new JLabel("bpm: ");
		sldrBPM = new JSlider(model.getBPMMIN(), model.getBPMMAX(),
				model.getBPMDEFAULT());
		txtBPM = new JTextField();
		txtBPM.setPreferredSize(new Dimension(60, 20));
		txtBPM.setText(Integer.toString(model.getBPMDEFAULT()));
		txtBPM.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Parser.validateIntRange(txtBPM.getText(),
						model.getBPMMIN(), model.getBPMMAX())) {
					int bpm = Integer.parseInt(txtBPM.getText());
					model.setBPM(bpm);
					sldrBPM.setValue(bpm);
				}
			}
		});

		sldrBPM.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				System.out.println(sldrBPM.getValue());
				model.setBPM(sldrBPM.getValue());
				txtBPM.setText(Integer.toString(sldrBPM.getValue()));
			}
		});


		btnPlay = new JButton("Play");
		btnPlay.setBackground(Color.GREEN);
		btnPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int speed = 200;
				try {
					speed = Integer.parseInt(txtBPM.getText());
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

		this.add(lblBPM);
		this.add(sldrBPM);
		this.add(txtBPM);
		this.add(btnPlay);
		this.add(btnStop);

	}


}
