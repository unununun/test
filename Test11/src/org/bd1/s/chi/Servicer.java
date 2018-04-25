package org.bd1.s.chi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Servicer implements Runnable {
	Socket s;

	public Servicer(Socket s) {
		this.s = s;
	}

	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;

		try {
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()),
					true);
			while (true) {
				String str = in.readLine();
				if (str.equals("exit")) {
					break;
				}
				System.out.println("接收客户端数据：" + str);
				System.out.println("服务器端：");
				Scanner sc = new Scanner(System.in);
				String serStr = sc.nextLine();
				out.println(serStr);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
