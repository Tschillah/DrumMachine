package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
	 * @throws IOException 
	 */
	public Main() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setTitle("ecp - Drum Machine");
		frame.setBounds(100, 0, 1000, 680);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		
		final Model model = new Model();
		DrumPad drumPad = new DrumPad();
		
		frame.add(drumPad, BorderLayout.WEST);
		
		
		FilterSelection filterSelection = new FilterSelection();
		frame.add(filterSelection, BorderLayout.NORTH);
		
		final JFileChooser fileChooser = new JFileChooser();
		
		JButton openFileChooser = new JButton("Select Image");
		openFileChooser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				fileChooser.showOpenDialog(frame);
				
				String filename = fileChooser.getSelectedFile().getAbsolutePath();
				
				if (filename != null){
					
					try {
						model.setImage(ImageIO.read(new File(filename)));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
	      		
		frame.add(openFileChooser, BorderLayout.EAST);
		
		// TEST
		
	}

}
