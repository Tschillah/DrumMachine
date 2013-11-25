package strategies;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class GrayScaleAnalyzer implements IImageAnalyzer {

	@Override
	public boolean analyze(BufferedImage img) {
		
		int sum = 0;
		final int TRESHOLD = 128;
		
		for (int w = 0; w < img.getWidth(); w++) {
			for (int h = 0; h < img.getHeight(); h++) {
				Color color = new Color(img.getRGB(w, h));
				int r = color.getRed();
				int g = color.getGreen();
				int b = color.getBlue();
				
				sum += (r+g+b)/3;				
			}
		}

		return(sum/(img.getWidth()*img.getHeight()) < TRESHOLD);
	}
}