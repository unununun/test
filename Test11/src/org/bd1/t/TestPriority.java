package org.bd1.t;

public class TestPriority {

	public static void main(String[] args) {
		T1 t1 = new T1();
		T2 t2 = new T2();
		t1.setPriority(Thread.NORM_PRIORITY + 2);
		t1.start();
		t2.start();
		
		try {
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t1.stopThread();
		
		System.out.println("t1的优先级：" + t1.getPriority());
		System.out.println("t2的优先级：" + t2.getPriority());
	}

}
