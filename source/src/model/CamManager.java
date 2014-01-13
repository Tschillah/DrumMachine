package model;

import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.Webcam;

public class CamManager {

	public static BufferedImage getCurrentFrame() {
		Webcam webcam = Webcam.getDefault();
		webcam.open();
		BufferedImage image = webcam.getImage();
		return image;
		// ImageIO.write(image, "PNG", new File("test.png"));

	}
}
