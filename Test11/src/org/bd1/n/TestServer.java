package org.bd1.n;

import java.io.IOException;

public class TestServer{
	public static void main(String[] args) {
		try {
			TestServerThread tst = new TestServerThread();
			Thread t1 = new Thread(tst);
			t1.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}