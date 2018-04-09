package org.bd1.t;

public class TestSleepThread {
	public static void main(String[] args) {
		System.out.println("aaaa");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("dddd");
	}
}
