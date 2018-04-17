package org.bd1.n;

import java.io.*; //引入Java.io包 
import java.net.*; //引入Java.net包 
import java.nio.ByteBuffer;
import java.nio.channels.*; //引入Java.nio.channels包 
import java.util.*; //引入Java.util包 

public class TestServerThread implements Runnable {
	/**
	 * 服务器Channel对象，负责接受用户连接
	 */
	private ServerSocketChannel serverChannel;
	/**
	 * Selector对象，负责监控所有的连接到服务器的网络事件的发生
	 */
	private Selector selector;
	/**
	 * 总的活动连接数
	 */
	private int activeSockets;
	/**
	 * 服务器Channel绑定的端口号
	 */
	private int port=9999;

	/**
	 * 
	 * 构造函数
	 */
	public TestServerThread() throws IOException {
		selector = Selector.open();
		// 打开监听通道
		serverChannel = ServerSocketChannel.open();
		// 如果为 true，则此通道将被置于阻塞模式；如果为 false，则此通道将被置于非阻塞模式
		serverChannel.configureBlocking(false);// 开启非阻塞模式
		// 绑定端口
		serverChannel.socket().bind(new InetSocketAddress(port));
		// 监听客户端连接请求
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);

		System.out.println("服务器已启动，端口号：" + port);
	}

	public void run() {
		while (true) {
			try {
				/**
				 * 应用Select机制轮循是否有用户感兴趣的新的网络事件发生，当没有 新的网络事件发生时，此方法会阻塞，直到有新的网络事件发生为止
				 */
				selector.select();

			/**
			 * 得到活动的网络连接选择键的集合
			 */
			Set<SelectionKey> keys = selector.selectedKeys();
			activeSockets = keys.size();// 获取活动连接的数目
			if (activeSockets == 0) {
				continue;// 如果连接数为0，则继续进行循环操作
			}
			/**
			 * /** 应用For—Each循环遍历整个选择键集合
			 */
			for (SelectionKey key : keys) {
				/**
				 * 如果关键字状态是为可接受，则接受连接，注册通道，以接受更多的* 事件，进行相关的服务器程序处理
				 */
				if (key.isAcceptable()) {
					doServerSocketEvent(key);
					continue;
				}
				/**
				 * 如果关键字状态为可读，则说明Channel是一个客户端的连接通道， 进行相应的读取客户端数据的操作
				 */
				if (key.isReadable()) {
					doClientReadEvent(key);
					continue;
				}
				/**
				 * 如果关键字状态为可写，则也说明Channel是一个客户端的连接通道， 进行相应的向客户端写数据的操作
				 */
				if (key.isWritable()) {
					doClinetWriteEvent(key);
					continue;
				}
			}
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * 处理服务器事件操作
	 * 
	 * @param key
	 *            服务器选择键对象
	 */
	private void doServerSocketEvent(SelectionKey key) {
		SocketChannel client = null;
		try {
			ServerSocketChannel server = (ServerSocketChannel) key.channel();
			client = server.accept();
			if (client == null) {
				return;
			}
			client.configureBlocking(false);// 将客户端Channel设置为非阻塞型
			/**
			 * /** 将客户端Channel注册到Selector对象上，并且指出客户端Channel所感 兴趣的事件为可读和可写
			 */
			client.register(selector, SelectionKey.OP_READ | SelectionKey.OP_READ);
		} catch (IOException e) {
			try {
				client.close();
			} catch (IOException e1) {
			}
		}
	}

	/**
	 * 进行向客户端写数据操作
	 * 
	 * @param key
	 *            客户端选择键对象
	 */
	private void doClinetWriteEvent(SelectionKey key) {
		// 代码实现略;
	}

	/**
	 * 进行读取客户短数据操作
	 * 
	 * @param key
	 *            客户端选择键对象
	 */
	private void doClientReadEvent(SelectionKey key) {
		try{
		SocketChannel sc = (SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		// 读取请求码流，返回读取到的字节数
		int readBytes = sc.read(buffer);
		// 读取到字节，对字节进行编解码
		if (readBytes > 0) {
			// 将缓冲区当前的limit设置为position=0，用于后续对缓冲区的读取操作
			buffer.flip();
			// 根据缓冲区可读字节数创建字节数组
			byte[] bytes = new byte[buffer.remaining()];
			// 将缓冲区可读字节数组复制到新建的数组中
			buffer.get(bytes);
			String expression = new String(bytes, "UTF-8");
			System.out.println("服务器收到消息：" + expression);

		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
