package org.bd1.t;

public class YieldThread extends Thread{
	YieldThread(String s) {
		super(s);
	}

	public void run() {
		for (int i = 1; i <= 30; i++) {
			System.out.println(getName() + ":" + i);

			if ((i % 10) == 0) {
				yield();
			}
		}
	}
}
