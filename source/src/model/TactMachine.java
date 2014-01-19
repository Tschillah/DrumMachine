package model;

/**
 * Represents a tact machine which loops through the specified number of
 * columns.
 * 
 */
public class TactMachine implements Runnable {

	private Model model;
	private int speed = 260;

	private volatile boolean running = true;

	public TactMachine(Model model) {
		this.model = model;
	}

	/**
	 * Stops the thread by setting the running flag to false.
	 */
	public void terminate() {
		running = false;
		model.unhighlightAllButtons();
	}

	/**
	 * Sets the running flag.
	 * 
	 * @param running
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}

	/**
	 * Run method which calls everything which has to be executed within the
	 * specified tact.
	 */
	public void run() {
		int i = 0;
		int col = 0;

		while (running) {
			// System.out.println("current Collumn: " + col);

			model.buildSound(col);
			model.highlightActiveButtons(col);
			i++;
			col = i % model.getColCount();
			try {
				Thread.sleep((long) speed);
			} catch (InterruptedException e) {
				running = false;
			}
		}

	}

	/**
	 * Sets the tact defined by the given beats per minute. The tact in the
	 * tactMachine is implemented as a delay between the samples in
	 * milliseconds.
	 * 
	 * @param bpm
	 */
	public void setBPM(int bpm) {
		int miliseconds = 60000 / bpm;
		this.speed = miliseconds;
	}

	/**
	 * Returns true if the thread is running. False if it is not.
	 * 
	 * @return
	 */
	public boolean isRunning() {
		return running;
	}

}
