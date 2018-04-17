package org.bd1.n;

import java.io.*; //引入Java.io包
import java.net.*; //引入Java.net包
import java.nio.ByteBuffer;
import java.nio.channels.*; //引入Java.nio.channels包
import java.util.*; //引入Java.util包

public class TestClientThread implements Runnable {

	private Selector selector;
	private SocketChannel socketChannel;

	/**
	 * 构造函数
	 */
	public TestClientThread() throws IOException {
		selector = Selector.open();//创建选择器
		socketChannel = SocketChannel.open();//打开监听通道
		//如果为 true，则此通道将被置于阻塞模式；如果为 false，则此通道将被置于非阻塞模式
		socketChannel.configureBlocking(false);//开启非阻塞模式
		socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999));
	}

	public void run() {
		
		//while (true) {
			try {
				
				
				selector.select(1000);//无论是否有读写事件发生，selector每隔1s被唤醒一次
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> it = keys.iterator();
				SelectionKey key = null;
				while (it.hasNext()) {
					key = it.next();
					it.remove();
					//读消息
					if (key.isReadable()) {
						
					}
				}
				write();
			} catch (Exception e) {
				e.printStackTrace();
			}
		//}
	}

	private void write() throws ClosedChannelException, IOException {
		byte[] bytes = "hello".getBytes();
		//根据数组容量创建ByteBuffer
		ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
		//将字节数组复制到缓冲区
		writeBuffer.put(bytes);
		//flip操作
		writeBuffer.flip();
		//发送缓冲区的字节数组
		socketChannel.register(selector, SelectionKey.OP_READ);
		socketChannel.write(writeBuffer);
	}

}
