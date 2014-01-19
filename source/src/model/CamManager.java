package model;

import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.Webcam;

/**
 * the cam manager is used for the capturing of webcam images and using these images for the drum machine
 * @author Chilla
 *
 */
public class CamManager implements Runnable{

	private Webcam webcam;
	private Model model;
	private boolean running;
	
	// Sets how many captures per second the webcam should take
	private static final long capturesPerSecond = 10;


	public CamManager(Model model) {
		this.model = model;
		
		// Opens the webcam to take captues
		this.webcam = Webcam.getDefault();
		webcam.open();
	}

	/**
	 * Captures the current webcam image and returns it
	 * @return
	 */
	public BufferedImage getCurrentFrame() {
		BufferedImage image = webcam.getImage();
		return image;
	}

	/**
	 * captures the webcam image and sets it in the model in the given interval (capturesPerSecond)
	 */
	@Override
	public void run() {
		while (running) {
			model.setImage(getCurrentFrame(), true);
			try {
				Thread.sleep(1000/capturesPerSecond);
			} catch (InterruptedException e) {
				running = false;
			}

		}
	}
	
	/**
	 * stops the capturing of webcam images
	 */
	public void terminate() {
		running = false;
	}

	/**
	 * sets whether or not the webcam should capture images right now
	 * @param running
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	/**
	 * returns whether or not the webcam is capturing images right now
	 * @return
	 */
	public boolean getRunning() {
		return running;
	}
}
