package org.bd1.s.t;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TServer {

	public static void main(String[] args) {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(8888);
			while(true){
				Socket s = ss.accept();
				Servicer ser = new Servicer(s);
				Thread t = new Thread(ser);
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
