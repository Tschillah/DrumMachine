package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import strategies.ColorAnalyzer;
import strategies.IImageAnalyzer;
import view.DrumPad;
import view.DrumPadButton;

public class Model {

	private static Model instance = null;

	private IImageAnalyzer imageAnalyzer;

	final int LINECOUNT = 6;
	final int COLCOUNT = 16;
	final int BLOCKCOUNT = LINECOUNT * COLCOUNT;
	private BufferedImage image;
	private DrumPadButton buttons[][] = new DrumPadButton[COLCOUNT][LINECOUNT];
	private DrumPad drumPad;

	// private String[] sampleLines = { "clave.wav", "perc7.wav", "perc3.wav",
	// "snare1.wav", "snare2.wav", "snare3.wav" };
	private String[] sampleLines = { "cowbell1.wav", "cowbell2.wav",
			"cowbell6.wav", "bongo10.wav", "kickdrum2.wav", "rattle2.wav" };

	// private LinkedList<INotifyable> listeners = new
	// LinkedList<INotifyable>();
	private SoundManager sou;

	private TactMachine tactMachine = null;
	private Thread thread = null;

	public Model() {

		sou = new SoundManager();

		tactMachine = new TactMachine(this);
		thread = new Thread(tactMachine);

		try {
			image = ImageIO.read(new File("res/farben.jpg"));
			// image = ImageIO.read(new File("res/flower.jpg"));

			imageAnalyzer = new ColorAnalyzer(Color.RED);

			// imageAnalyzer = new GrayScaleAnalyzer();
			divideImage();
			analyzeImage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void startTactMachine() {
		tactMachine.setRunning(true);
		thread = new Thread(tactMachine);
		thread.start();
	}

	public void stopTactMachine() {
		if (thread != null) {
			tactMachine.terminate();

			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int getColCount() {
		return COLCOUNT;
	}

	public void setDrumPad(DrumPad drumPad) {
		this.drumPad = drumPad;
	}

	public DrumPad getDrumPad() {
		return drumPad;
	}

	/*
	 * Sets an image and afterwarts divides an analyzes it
	 */
	public void setImage(BufferedImage img) {
		this.image = img;

		// image = ImageIO.read(new File("res/farben.jpg"));
		divideImage();
		analyzeImage();
	}

	/*
	 * Divides the image into smaller parts
	 */
	public void divideImage() {

		for (int x = 0; x < COLCOUNT; x++) {

			for (int y = 0; y < LINECOUNT; y++) {

				if (buttons[x][y] == null) {

					BufferedImage bg = image.getSubimage(getBlockWidth() * x,
							getBlockHeight() * y, getBlockWidth(),
							getBlockHeight());

					buttons[x][y] = new DrumPadButton(bg);

				} else {

					buttons[x][y].setBackground(image.getSubimage(
							getBlockWidth() * x, getBlockHeight() * y,
							getBlockWidth(), getBlockHeight()));

				}
			}
		}

	}

	/*
	 * Analayzed all image parts with the analyzer which is currently set.
	 */
	public void analyzeImage() {
		// send image blocks to analyzer
		for (int x = 0; x < COLCOUNT; x++) {
			for (int y = 0; y < LINECOUNT; y++) {
				buttons[x][y].analyze(imageAnalyzer);
			}
		}
	}

	public int getBlockWidth() {
		return image.getWidth() / COLCOUNT;
	}

	public int getBlockHeight() {
		return image.getHeight() / LINECOUNT;
	}

	public DrumPadButton getButton(int x, int y) {
		return buttons[x][y];
	}

	/**
	 * Sets the current ImageAnalyzer
	 * 
	 * @param analyzer
	 */
	public void setFilter(IImageAnalyzer analyzer) {
		this.imageAnalyzer = analyzer;
		analyzeImage();
	}

	// method which preserves access to this class
	public static Model getInstance() {
		if (instance == null) {
			instance = new Model();
		}
		return instance;
	}

	public int getLineCount() {
		return LINECOUNT;
	}

	public int getColumnCount() {
		return COLCOUNT;
	}

	/*
	 * public void addModelChangeListener(INotifyable listener) {
	 * listeners.add(listener); }
	 * 
	 * public void notifyListeners() { for (INotifyable listener : listeners) {
	 * listener.update(); } }
	 */
	public boolean isFilterActive() {
		return true;
	}

	/**
	 * Fill SoundManager
	 */
	public void buildSound(int column) {

		/*
		 * if (column > COLCOUNT - 1) { column = 0; }
		 */

		int count = -1;
		sou.clear();

		for (int j = 0; j < buttons[1].length; j++) {
			if (buttons[column][j].getState()) {

				try {
					sou.addClip("samples/sam2/" + sampleLines[j]);
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

		/*
		 * try { Thread.sleep(500); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

	}

}
