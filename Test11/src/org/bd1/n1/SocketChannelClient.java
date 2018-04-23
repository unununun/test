package org.bd1.n1;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;

public class SocketChannelClient {

	 private String host = "127.0.0.1";// 要发送给服务端的ip
	 
	    private int port = 8000;// 要发送给服务端的端口
	 
	    public SocketChannelClient() throws IOException {
	        SocketChannel sc = SocketChannel.open(new InetSocketAddress(host, port));// 打开一个SocketChannel并连接到服务器
	        sc.configureBlocking(false);
	 
	 
	        // 发送消息给server
	        String sendMsg = "I am a coder.哈哈哈哈";
	        ByteBuffer writeBuffer = ByteBuffer.wrap(sendMsg.getBytes());
	        System.out.println("client send msg===" + sendMsg);
	        sc.write(writeBuffer);
	        sc.close();
	    }
	 
	    public static void main(String[] args) throws IOException {
	        new SocketChannelClient();
	    }

}
