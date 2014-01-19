package strategies;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ColorAnalyzer implements IImageAnalyzer{

	Color color;
	int treshold;
	
	public ColorAnalyzer(){
		this.color = Color.BLACK;
		this.treshold = 128;
	}

	public ColorAnalyzer(Color c){
		this.color = c;
		this.treshold = 128;
	}
	
	public ColorAnalyzer(Color c, int treshold){
		this.color = c;
		this.treshold = treshold;
	}

	@Override
	public boolean analyze(BufferedImage img) {
		
		int sum = 0;
		
		for (int w = 0; w < img.getWidth(); w++) {
			for (int h = 0; h < img.getHeight(); h++) {
				Color curColor = new Color(img.getRGB(w, h));
				int pixel;
				if (color == Color.RED){
					pixel = curColor.getRed();

				} else if (color == Color.GREEN) {
					pixel = curColor.getGreen();

				} else {
					pixel = curColor.getBlue();

				}
				
				sum += pixel;				
			}
		}

		return sum / (img.getWidth()*img.getHeight()) > treshold;
		
	}
}
