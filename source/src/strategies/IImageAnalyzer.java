package strategies;

import java.awt.image.BufferedImage;

/*
 * Interface for the strategies, analyzes a part of the image and returns whether or not
 * the appropriate button in the drum machine is activated or not, depending on the chosen
 * strategy
 */
public interface IImageAnalyzer {

	void setTeshold(int threshold);

	boolean analyze(BufferedImage imgpart);

}
