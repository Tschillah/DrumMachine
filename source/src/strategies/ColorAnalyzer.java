package strategies;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ColorAnalyzer implements IImageAnalyzer{

	Color color;
	
	public ColorAnalyzer(){
		this.color = Color.BLACK;
	}
	
	public ColorAnalyzer(Color c){
		this.color = c;
	}

	@Override
	public boolean analyze(BufferedImage img) {
		
		
		int sum = 0;
		final int TRESHOLD = 64;
	//	final int TRESHOLD = 64;

		
		for (int w = 0; w < img.getWidth(); w++) {
			for (int h = 0; h < img.getHeight(); h++) {
				Color curColor = new Color(img.getRGB(w, h));
				int r;
				if (color == Color.RED){
					r = curColor.getRed();

				} else if (color == Color.GREEN) {
					r = curColor.getGreen();

				} else {
					r = curColor.getBlue();

				}
				
				sum += r;				
			}
		}

		return !(sum/(img.getWidth()*img.getHeight()) < TRESHOLD);
		
	}
}
