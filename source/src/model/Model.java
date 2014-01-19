package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.imgscalr.Scalr;

import strategies.IImageAnalyzer;
import strategies.NullAnalyzer;
import view.DrumPadButton;
import framework.INotifyable;

/**
 * Represents the business logic. Implemented as a singleton.
 */
public class Model {

	// Instance which holds the model
	private static Model instance = null;

	// Observers
	ArrayList<INotifyable> notifyables = new ArrayList<INotifyable>();

	// Fixed drummachine settings
	final int LINECOUNT = 6;
	final int COLCOUNT = 16;
	final int BLOCKCOUNT = LINECOUNT * COLCOUNT;
	final int BPMMIN = 200;
	final int BPMMAX = 800;
	final int BPMDEFAULT = 400;
	final int TRESHOLDMIN = 0;
	final int TRESHOLDMAX = 255;
	final int TRESHOLDDEFAULT = 128;

	private DrumPadButton buttons[][] = new DrumPadButton[COLCOUNT][LINECOUNT];
	private String[] sampleLines = { "cowbell1.wav", "cowbell2.wav",
			"cowbell6.wav", "bongo10.wav", "kickdrum2.wav", "rattle2.wav" };

	private IImageAnalyzer imageAnalyzer;
	private BufferedImage image;
	private SoundManager sou;
	private CamManager camManager;
	private TactMachine tactMachine = null;
	private Thread thread = null;
	private Thread webcamThread = null;

	/*
	 * private int currentTreshold; private int currentBPM;
	 */

	public Model() {

		sou = new SoundManager();
		camManager = new CamManager(this);

		tactMachine = new TactMachine(this);
		thread = new Thread(tactMachine);
		webcamThread = new Thread(camManager);

		try {
			image = ImageIO.read(new File("res/farben.jpg"));
			imageAnalyzer = new NullAnalyzer(false);

			divideImage();
			analyzeImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Starts a new thread within the tactMachine and sets the running flag to
	 * true.
	 */
	public void startTactMachine() {
		tactMachine.setRunning(true);
		thread = new Thread(tactMachine);
		thread.start();
	}

	/**
	 * Sets the running flag of the tactMachine to false which will stop the
	 * thread.
	 */
	public void stopTactMachine() {
		if (thread != null) {
			tactMachine.terminate();

			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void toggleWebcamCapture() {
		if (camManager.getRunning()) {
			stopWebcamCapture();
		} else {
			startWebcamCapture();
		}

	}

	public void startWebcamCapture() {
		camManager.setRunning(true);
		webcamThread = new Thread(camManager);
		webcamThread.start();
	}

	public void stopWebcamCapture() {
		if (webcamThread != null) {
			camManager.terminate();

			try {
				webcamThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Returns the number of columns of the drumMachine.
	 * 
	 * @return
	 */
	public int getColCount() {
		return COLCOUNT;
	}

	/**
	 * Sets an image and afterwards divides and analyzes it.
	 */
	public void setImage(BufferedImage img, boolean webcam) {
		// stopTactMachine();

		if (!webcam) {
			stopWebcamCapture();
		}

		if (img.getHeight() != 600 && img.getWidth() != 800) {
			img = Scalr.resize(img, 800);
		}

		this.image = img;
		divideImage();
		analyzeImage();
	}

	/**
	 * Divides the image into smaller parts based on the grid size, which is
	 * defined by COLCOUNT and LINECOUNT.
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

	/**
	 * Analyzes all image parts with the current analyzer strategy.
	 */
	public void analyzeImage() {
		// send image blocks to analyzer
		for (int x = 0; x < COLCOUNT; x++) {
			for (int y = 0; y < LINECOUNT; y++) {
				buttons[x][y].analyze(imageAnalyzer);
			}
		}
	}

	/**
	 * Returns the width of a single cell in the grid.
	 * 
	 * @return
	 */
	public int getBlockWidth() {
		return image.getWidth() / COLCOUNT;
	}

	/**
	 * Returns the height of a single cell in the grid.
	 * 
	 * @return
	 */
	public int getBlockHeight() {
		return image.getHeight() / LINECOUNT;
	}

	/**
	 * Returns the JButton on the specified place of the grid.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
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
		update();
	}

	/**
	 * Provides access to this class. Returns the model.
	 * 
	 * @return
	 */
	public static Model getInstance() {
		if (instance == null) {
			instance = new Model();
		}
		return instance;
	}

	/**
	 * Returns the amount of rows in the grid.
	 * 
	 * @return
	 */
	public int getLineCount() {
		return LINECOUNT;
	}

	/**
	 * Returns the amount of columns in the grid
	 * 
	 * @return
	 */
	public int getColumnCount() {
		return COLCOUNT;
	}

	/**
	 * Adds the activated samples from a column to the SoundManager and plays
	 * them.
	 */
	public void buildSound(int column) {

		int count = -1;
		sou.clear();

		for (int j = 0; j < buttons[1].length; j++) {
			if (buttons[column][j].getState()) {

				try {
					sou.addClip("samples/sam2/" + sampleLines[j]);
					// System.out.println("sample: " + sampleLines[j] +
					// " added");
					count++;
				} catch (IOException e) {
					e.printStackTrace();
				} catch (UnsupportedAudioFileException e) {
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
			}
		}

		if (count > -1) {
			for (int i = 0; i <= count; i++) {
				try {
					sou.playSound(i);
				} catch (UnsupportedAudioFileException e) {
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Highlights the buttons of the given column.
	 * 
	 */
	public void highlightActiveButtons(int col) {
		int colOld = 0;

		// Colorize antecendent column
		if (col > 0) {
			colOld = col - 1;
		} else {
			colOld = COLCOUNT - 1;
		}
		for (int y = 0; y < LINECOUNT; y++) {
			this.getButton(colOld, y).setUnhighlighted();
		}

		// Colorize current column
		for (int y = 0; y < LINECOUNT; y++) {
			this.getButton(col, y).setHighlighted();
		}
	}

	/**
	 * Unhighlights all buttons.
	 */
	public void unhighlightAllButtons() {
		for (int x = 0; x < COLCOUNT; x++) {
			for (int y = 0; y < LINECOUNT; y++) {
				this.getButton(x, y).setUnhighlighted();
			}
		}
	}

	/**
	 * Sets the beats per minute on the tactMachine.
	 * 
	 * @param bpm
	 */
	public void setBPM(int bpm) {
		tactMachine.setBPM(bpm);
	}

	/**
	 * Returns true if the tactMachine is running. False when it is not.
	 * 
	 * @return
	 */
	public boolean isRunning() {
		return tactMachine.isRunning();
	}

	/**
	 * Registers an observer to the model.
	 * 
	 * @param observer
	 */
	public void register(INotifyable observer) {
		notifyables.add(observer);
	}

	/**
	 * Calls the update method on every observer.
	 */
	public void update() {
		for (INotifyable observer : notifyables) {
			observer.update();
		}
	}

	/**
	 * Returns a snapshot of the current webcam screen.
	 * 
	 * @return
	 */
	public BufferedImage getCurrentFrame() {
		return camManager.getCurrentFrame();
	}

	// Getters and Setters

	public int getBPMMIN() {
		return BPMMIN;
	}

	public int getBPMMAX() {
		return BPMMAX;
	}

	public int getBPMDEFAULT() {
		return BPMDEFAULT;
	}

	public int getTRESHOLDMIN() {
		return TRESHOLDMIN;
	}

	public int getTRESHOLDMAX() {
		return TRESHOLDMAX;
	}

	public int getTRESHOLDDEFAULT() {
		return TRESHOLDDEFAULT;
	}
}
