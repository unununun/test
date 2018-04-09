package org.bd1.t;

public class ThreadInterrupted implements Runnable {

	private boolean finished = false;

	public void run() {

		int i = 0;
		while (true) {
			System.out.println(++i);
			if (finished) {
				break;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

}
