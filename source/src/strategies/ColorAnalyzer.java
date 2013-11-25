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
	public boolean analyze(BufferedImage imgpart) {
		
		
		// TODO Auto-generated method stub
		return false;
		
	}
}
