package org.bd1.n;

public class TestClient {
	public static void main(String[] args) {
		try {
			TestClientThread tst = new TestClientThread();
			Thread t1 = new Thread(tst);
			t1.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
