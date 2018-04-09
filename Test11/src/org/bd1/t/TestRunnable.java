package org.bd1.t;

public class TestRunnable {

	public static void main(String[] args) {
		RunnableDemo runner = new RunnableDemo();
		Thread t = new Thread(runner);
		t.start();

		for(int i=0;i<20;i++){
			System.out.println("Main:"+i);
		}
	}

}
