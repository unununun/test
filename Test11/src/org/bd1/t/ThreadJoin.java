package org.bd1.t;

public class ThreadJoin extends Thread {

	public ThreadJoin(String name) {
		super(name);
	}

	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.println(this.getName() + ":" + i);
		}
	}
}
