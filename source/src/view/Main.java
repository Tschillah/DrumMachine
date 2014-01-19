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
		frame.setBounds(100, 0, 845, 746);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		// Boxes to layout the controls
		Box rowImageSource = Box.createHorizontalBox();
		Box rowBeatControls = Box.createHorizontalBox();
		Box rowAnalyzer = Box.createHorizontalBox();

		// Instantiate the filter selection and add the filter selection to the
		// frame
		BeatControls beatControls = new BeatControls();
		rowBeatControls.add(beatControls);

		// Get the model instance
		final Model model = Model.getInstance();

		// Instantiate the drum pad and add it to the frame
		DrumPad drumPad = new DrumPad();
		frame.add(drumPad, BorderLayout.WEST);

		// Instantiate the filter selection and add the filter selection to the
		// frame
		final FilterSelection filterSelection = new FilterSelection();
		rowAnalyzer.add(filterSelection, BorderLayout.NORTH);

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

		final JButton captureWebcam = new JButton("Start Webcam Capture");
		captureWebcam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				model.toggleWebcamCapture();
				if (captureWebcam.getText() == "Start Webcam Capture") {
					captureWebcam.setText("Stop Webcam Capture");
					filterSelection.btnFilterRandomAnalyzer.setEnabled(false);
				} else {
<<<<<<< HEAD
					captureWebcam.setText("Start Webcam Capture");		
					filterSelection.btnFilterRandomAnalyzer.setEnabled(true);

=======
					captureWebcam.setText("Start Webcam Capture");
>>>>>>> 0976310cde67ac5e23d58c4f2382b85a0d41957e
				}
			}
		});

		final JButton captureWebcamFrame = new JButton("Take Webcam Snapshot");
		captureWebcamFrame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.takeWebcamSnapshot();
			}
		});

		rowImageSource.add(openFileChooser);
		rowImageSource.add(captureWebcam);
		rowImageSource.add(captureWebcamFrame);

		Box topControls = Box.createVerticalBox();
		topControls.add(rowImageSource);
		topControls.add(rowBeatControls);
		topControls.add(rowAnalyzer);

		frame.add(topControls, BorderLayout.NORTH);

	}
}
