package strategies;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class RandomAnalyzer implements IImageAnalyzer {

	@Override
	public boolean analyze(BufferedImage img) {
	
		return(Math.random() < 0.75);
	}
}