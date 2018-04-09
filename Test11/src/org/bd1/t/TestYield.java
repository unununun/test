package org.bd1.t;

public class TestYield {

	public static void main(String[] args) {
		YieldThread y1 = new YieldThread("y1");
		YieldThread y2 = new YieldThread("y2");
		y1.start();
		y2.start();
	}

}
