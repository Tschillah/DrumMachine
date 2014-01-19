package model;

import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.Webcam;

public class CamManager implements Runnable{

	private Webcam webcam;
	private Model model;
	private boolean running;


	public CamManager(Model model) {
		this.model = model;
		this.webcam = Webcam.getDefault();
		webcam.open();
	}

	public BufferedImage getCurrentFrame() {
		BufferedImage image = webcam.getImage();
		return image;
	}

	@Override
	public void run() {
		while (running) {
			model.setImage(getCurrentFrame());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				running = false;
			}

		}
	}
	
	public void terminate() {
		running = false;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}
