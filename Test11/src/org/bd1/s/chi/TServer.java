package org.bd1.s.chi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TServer {
	
	private static final int THREAD_POOL_SIZE = 5;//线程池数量

	public static void main(String[] args) {
		ServerSocket ss = null;
		//创建固定数目的线程池
		ExecutorService pool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
		try {
			ss = new ServerSocket(9988);
			while(true){
				Socket s = ss.accept();
				Servicer ser = new Servicer(s);
				Thread t = new Thread(ser);
				pool.submit(t);//已线程池的方式启动线程
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
