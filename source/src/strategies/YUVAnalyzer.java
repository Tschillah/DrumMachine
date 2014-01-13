package strategies;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class YUVAnalyzer implements IImageAnalyzer {

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
				
				int y = (int) (r *  .299000 + g *  .587000 + b *  .114000);
				int u = (int) (r * -.168736 + g * -.331264 + b *  .500000 + 128);
				int v = (int) (r *  .500000 + g * -.418688 + b * -.081312 + 128);
				
				sum += (y + u + v)/3;
			}
		}

		return !(sum/(img.getWidth()*img.getHeight()) < TRESHOLD);
	}
}