package org.bd1.s.t;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TClient {

	public static void main(String[] args) {
		Socket s = null;
		try {
			s = new Socket("127.0.0.1", 8888);
			BufferedReader in = new BufferedReader(new InputStreamReader(s
					.getInputStream()));
			PrintWriter out = new PrintWriter(new OutputStreamWriter(s
					.getOutputStream()), true);
			while (true) {
				Scanner sc = new Scanner(System.in);
				System.out.println("客户端：");
				String str = sc.nextLine();
				out.println(str);
				if (str.equals("exit")) {
					break;
				}
				String msg = in.readLine();
				System.out.println("接收服务器数据：" + msg);
			}
			System.out.println("客户端退出");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (s != null) {
					s.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
