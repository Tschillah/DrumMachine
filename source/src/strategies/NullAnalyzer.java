package strategies;

import java.awt.image.BufferedImage;

public class NullAnalyzer implements IImageAnalyzer {

	boolean bool;

	public NullAnalyzer(boolean bool) {
		this.bool = bool;

	}

	@Override
	public boolean analyze(BufferedImage img) {

		return bool;

	}

	@Override
	public void setTeshold(int threshold) {
	}
}
