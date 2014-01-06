package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import strategies.ColorAnalyzer;
import strategies.IImageAnalyzer;
import framework.INotifyable;

public class Model implements PropertyChangeListener {

	PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
	private static Model instance = null;

	private int[][] matrix;
	private int sampleCount;
	private int loopLength;
	private IImageAnalyzer imageAnalyzer;

	final int LINECOUNT = 6;
	final int COLCOUNT = 16;
	final int BLOCKCOUNT = LINECOUNT * COLCOUNT;
	private BufferedImage image;
	private BufferedImage imageBlocks[][] = new BufferedImage[COLCOUNT][LINECOUNT];
	private boolean buttonStates[][] = new boolean[COLCOUNT][LINECOUNT];
	private String[] sampleLines = { "clave.wav", "perc7.wav", "perc3.wav",
			"snare1.wav", "snare2.wav", "snare3.wav" };

	private LinkedList<INotifyable> listeners = new LinkedList<INotifyable>();
	private SoundManager sou;

	public Model() {

		sou = new SoundManager();

		try {
			image = ImageIO.read(new File("res/farben.jpg"));
			// image = ImageIO.read(new File("res/hongkong.jpg"));

			imageAnalyzer = new ColorAnalyzer(Color.RED);

			// imageAnalyzer = new GrayScaleAnalyzer();
			divideImage();
			analyzeImage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * Sets an image and afterwarts divides an analyzes it
	 */
	public void setImage(BufferedImage img) {
		this.image = img;
		divideImage();
		analyzeImage();
	}

	/*
	 * Divides the image into smaller parts
	 */
	public void divideImage() {

		for (int x = 0; x < COLCOUNT; x++) {
			for (int y = 0; y < LINECOUNT; y++) {

				imageBlocks[x][y] = image
						.getSubimage(getBlockWidth() * x, getBlockHeight() * y,
								getBlockWidth(), getBlockHeight());

			}
		}

		System.out.println(image);

	}

	public void toggleButton(int x, int y) {
		buttonStates[x][y] = !buttonStates[x][y];
		notifyListeners();
	}

	/*
	 * Analayzed all image parts with the analyzer which is currently set.
	 */
	public void analyzeImage() {

		// send image blocks to analyzer
		for (int x = 0; x < COLCOUNT; x++) {
			for (int y = 0; y < LINECOUNT; y++) {

				buttonStates[x][y] = imageAnalyzer.analyze(imageBlocks[x][y]);
				System.out.println("x: " + x + ", y: " + y + " - "
						+ imageBlocks[x][y]);
			}
		}
	}

	public int getBlockWidth() {
		return image.getWidth() / COLCOUNT;
	}

	public int getBlockHeight() {
		return image.getHeight() / LINECOUNT;
	}

	public BufferedImage getImagePart(int x, int y) {
		return imageBlocks[x][y];
	}

	public boolean getButtonState(int x, int y) {
		return buttonStates[x][y];
	}

	/**
	 * Sets the current ImageAnalyzer
	 * 
	 * @param analyzer
	 */
	public void setFilter(IImageAnalyzer analyzer) {
		this.imageAnalyzer = analyzer;

		analyzeImage();
		notifyListeners();
	}

	public void setMatrix(int sampleCount, int loopLength) {
		this.sampleCount = sampleCount;
		this.loopLength = loopLength;

		matrix = new int[loopLength][sampleCount];
	}

	// defining the listener-methods
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		changeSupport.firePropertyChange(event);
	}

	// method which preserves access to this class
	public static Model getInstance() {
		if (instance == null) {
			instance = new Model();
		}
		return instance;
	}

	public void addModelChangeListener(INotifyable listener) {
		listeners.add(listener);
	}

	public void notifyListeners() {
		for (INotifyable listener : listeners) {
			listener.update();
		}
	}

	public boolean isFilterActive() {
		return true;
	}

	/**
	 * Fill SoundManager
	 */
	public void buildSound(int column) {

		if (column > COLCOUNT - 1) {
			column = 0;
		}

		int count = -1;
		sou.clear();

		for (int j = 0; j < buttonStates[1].length; j++) {
			if (buttonStates[column][j]) {

				try {
					sou.addClip("samples/" + sampleLines[j]);
					System.out.println("sample: " + sampleLines[j] + " added");
					count++;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		if (count > -1) {
			for (int i = 0; i <= count; i++) {
				try {
					sou.playSound(i);
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		/*
		 * Timer timer = new Timer(500, null); timer.start();
		 */

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		buildSound(++column);
	}

}
