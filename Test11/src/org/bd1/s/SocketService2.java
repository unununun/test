package org.bd1.s;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketService2 {
	// 搭建服务器端
	public static void main(String[] args) throws IOException {
		SocketService2 socketService = new SocketService2();
		socketService.oneServer();
	}

	private String[] loc={"您已经进入A9楼312教室","您已经进入A9楼416教室","您已经进入A9楼313教室","您已经进入A9楼212教室","您已经进入A2楼312教室","您已经进入A9楼412教室","您已经进入A9楼322教室","您已经进入A9楼311教室","您已经进入A9楼222教室","您已经进入A9楼410教室"};
	
	private String randomLoc(){
		int r = (int)(Math.random()*10);
		return loc[r];
	}
	
	public void oneServer() {
		try {
			ServerSocket server = null;
			try {
				server = new ServerSocket(5209);
				// b)指定绑定的端口，并监听此端口。
				System.out.println("服务器启动成功");
				// 创建一个ServerSocket在端口5209监听客户请求
			} catch (Exception e) {
				System.out.println("没有启动监听：" + e);
			}
			Socket socket = null;
			try {
				socket = server.accept();
				// 2、调用accept()方法开始监听，等待客户端的连接
				// 使用accept()阻塞等待客户请求，有客户
				// 请求到来则产生一个Socket对象，并继续执行
			} catch (Exception e) {
				System.out.println("Error." + e);
			}
			String line="",clientInput="";
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// 由Socket对象得到输入流，并构造相应的BufferedReader对象
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			// 由Socket对象得到输出流，并构造PrintWriter对象

			// 4、获取输出流，响应客户端的请求
			while (true) {
				clientInput=in.readLine();
				if("left".equals(clientInput)){
					line=randomLoc();
				}else if("right".equals(clientInput)){
					line=randomLoc();
				}else if("init".equals(clientInput)){
					line="欢迎光临！！！请输入命令";
				}else{
					line="无此命令，请重新输入";
				}
				//System.out.println("玩家输入:" + clientInput);
				writer.println(line);
				// 向客户端输出该字符串
				writer.flush();
				if("bye".equals(clientInput)){// 如果该字符串为 "bye"，则停止循环
					writer.close(); // 关闭Socket输出流
					in.close(); // 关闭Socket输入流
					socket.close(); // 关闭Socket
					server.close(); // 关闭ServerSocket
				}
			} // 继续循环
		} catch (Exception e) {// 出错，打印出错信息
			System.out.println("Error." + e);
		}
	}
}