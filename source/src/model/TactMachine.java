package model;

public class TactMachine implements Runnable {

	private int currentColumn;
	private Model model;

	private volatile boolean running = true;

	public TactMachine(Model model) {
		this.model = model;
	}

	public void terminate() {
		running = false;
	}

	public void run() {
		int i = 0;
		int col = 0;

		while (running) {
			System.out.println("current Collumn: " + col);
			model.buildSound(col);
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
