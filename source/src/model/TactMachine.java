package model;

public class TactMachine implements Runnable {

	private int currentColumn;
	private int colCount;

	private volatile boolean running = true;

	public TactMachine(int colCount) {
		this.colCount = colCount;
	}

	public void terminate() {
		running = false;
	}

	public void run() {
		int i = 0;
		while (running) {
			System.out.println(i % colCount);
			i++;
			try {
				Thread.sleep((long) 1000);
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
