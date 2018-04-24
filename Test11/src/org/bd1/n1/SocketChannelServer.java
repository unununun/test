package org.bd1.n1;

import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;

public class SocketChannelServer {
	private int port = 8000;// 端口

	 
    public SocketChannelServer() throws Exception {
        
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        
        serverChannel.configureBlocking(false); // 设置为非阻塞方式,如果为true 那么就为传统的阻塞方式
        
        serverChannel.socket().bind(new InetSocketAddress(port)); // 绑定IP 及 端口
        
        Selector selector = Selector.open();
        
        serverChannel.register(selector, SelectionKey.OP_ACCEPT); // 注册
 
        while (true) {
            //System.out.println("Waiting accept!");
            selector.select();// 刚启动时，没有客户端连接时，会堵塞在这里
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();// 为了防止重复迭代
                if (key.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();// 新的连接
                    System.out.println("Client accept!" + socketChannel);
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);// 注册read
                } else if (key.isReadable()) {// 读取
                    SocketChannel socketChannel = (SocketChannel) key.channel();// 获得与客户端通信的信道
                    ByteBuffer readbuffer = ByteBuffer.allocate(2048);//2k,1024个中文
                    int conut = socketChannel.read(readbuffer); // 读取信息获得读取的字节数
                    if (conut >0) {
                        String s = new String(readbuffer.array(),0,conut);
                        System.out.println("server receive msg===" + s);
                    }
                    //socketChannel.register(selector, SelectionKey.OP_WRITE);// 注册read
                } else if (key.isWritable()) {// 写入
                    SocketChannel socketChannel = (SocketChannel) key.channel();// 获得与客户端通信的信道
                    String sendMsg = "hello world!";
                    ByteBuffer writeBuffer = ByteBuffer.allocate(2048);//2k,1024个中文
                    writeBuffer.put(sendMsg.getBytes());
                    System.out.println("server send msg===" + sendMsg);
                    socketChannel.write(writeBuffer);
                }
            }
        }
    }
 
    public static void main(String[] args) throws Exception {
        new SocketChannelServer();
    }

}
