package model;

public class TactMachine implements Runnable {

	private int currentColumn;
	private Model model;
	private int speed = 260;

	private volatile boolean running = true;

	public TactMachine(Model model) {
		this.model = model;
	}

	public void terminate() {
		running = false;
		model.unhighlightAllButtons();
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public void run() {
		int i = 0;
		int col = 0;

		while (running) {
			// System.out.println("current Collumn: " + col);
			model.buildSound(col);
			model.highlightActiveButtons(col);
			currentColumn = col;
			i++;
			col = i % model.getColCount();
			try {
				Thread.sleep((long) speed);
			} catch (InterruptedException e) {
				running = false;
			}
			/*
			 * for (int i = 0; i < model.getColCount(); i++) {
			 * System.out.println(i); try { Thread.sleep((long) 1000); } catch
			 * (InterruptedException e) { running = false; } }
			 */
		}

	}

	public int getCurrentColumn() {
		return currentColumn;
	}

	public void setSpeed(int bpm) {
		// System.out.println("Speed changed to:" + bpm);

		int miliseconds = 60000 / bpm;
		// System.out.println(miliseconds);

		this.speed = miliseconds;
	}

	public boolean isRunning() {
		return running;
	}

}
