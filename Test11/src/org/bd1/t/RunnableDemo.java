package org.bd1.t;

public class RunnableDemo implements Runnable{

	public void run() {
		for(int i=0;i<20;i++){
			System.out.println("runnable:"+i);
		}
		
	}

}
