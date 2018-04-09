package org.bd1.t;

public class TestThread {

	public static void main(String[] args) {
		ThreadDemo t = new ThreadDemo();
		t.start();
		
		for(int i=0;i<20;i++){
			System.out.println("Main:"+i);
		}

	}

}
