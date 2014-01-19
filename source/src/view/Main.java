package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import model.Model;

/**
 * Main class that starts the application and instantiates the model and the
 * view
 * 
 * @author Chilla
 * 
 */
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
	 * 
	 * @throws IOException
	 */
	public Main() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {

		// Instantiate frame, set title/size/closeoperation/layout
		frame = new JFrame();
		frame.setTitle("ecp - Drum Machine");
		frame.setBounds(100, 0, 1100, 680);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		// Get the model instance
		final Model model = Model.getInstance();

		// Instantiate the drum pad and add it to the frame
		DrumPad drumPad = new DrumPad();
		frame.add(drumPad, BorderLayout.WEST);

		// Instantiate the filter selection and add the filter selection to the
		// frame
		FilterSelection filterSelection = new FilterSelection();
		frame.add(filterSelection, BorderLayout.NORTH);

		// Instantiate the file chooser to select images and add it to the frame
		final JFileChooser fileChooser = new JFileChooser();
		JButton openFileChooser = new JButton("Select Image");
		openFileChooser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				fileChooser.showOpenDialog(frame);
				if (fileChooser.getSelectedFile() != null) {
					String filename = fileChooser.getSelectedFile()
							.getAbsolutePath();

					if (filename != null) {

						try {
							// Read the selected image
							BufferedImage newImage = ImageIO.read(new File(
									filename));

							// Set the image in the model
							model.setImage(newImage, false);
						} catch (IOException e1) {
							e1.printStackTrace();
						}

					}
				}

			}
		});

		JButton captureWebcam = new JButton("Webcam");
		captureWebcam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				
				model.toggleWebcamCapture();
			}
		});

		Box rightBox = Box.createVerticalBox();
		rightBox.add(openFileChooser);
		rightBox.add(captureWebcam);
		frame.add(rightBox, BorderLayout.EAST);

	}

}
