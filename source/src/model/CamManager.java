package model;

import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.Webcam;

public class CamManager {

	private Webcam webcam;

	public CamManager() {
		this.webcam = Webcam.getDefault();
		webcam.open();
	}

	public BufferedImage getCurrentFrame() {
		BufferedImage image = webcam.getImage();
		return image;
	}
}
