package model;

import view.DrumPad;

public class TactMachine implements Runnable {

	private int currentColumn;
	private Model model;
	private DrumPad drumPad;

	private volatile boolean running = true;

	public TactMachine(Model model) {
		this.model = model;
		this.drumPad = model.getDrumPad();
	}

	public void terminate() {
		running = false;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public void run() {
		int i = 0;
		int col = 0;

		while (running) {
			System.out.println("current Collumn: " + col);
			model.buildSound(col);
			// drumPad.highlightActiveButtons(col);
			currentColumn = col;
			i++;
			col = i % model.getColCount();
			try {
				Thread.sleep((long) 200);
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

}
