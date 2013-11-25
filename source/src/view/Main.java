package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import model.Model;



public class Main {

	private JFrame frame;
	final JFileChooser fc = new JFileChooser();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("ecp - Drum Machine");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		
		Model model = new Model();
		DrumPad drumPad = new DrumPad(model);
		
		frame.add(drumPad);
		
		
		FilterSelection filterSelection = new FilterSelection();
		frame.add(filterSelection, BorderLayout.NORTH);
		
		final FileDialog fileChooser = new FileDialog(frame);
		
		JButton openFileChooser = new JButton("Select Image");
		openFileChooser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fileChooser.show();
				
			}
		});
	      		
		frame.add(openFileChooser, BorderLayout.EAST);
		
		// TEST
		
	}

}
