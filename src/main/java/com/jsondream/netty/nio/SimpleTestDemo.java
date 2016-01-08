package com.jsondream.netty.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

import javax.mail.internet.InternetAddress;

public class SimpleTestDemo {

	private final static int port  = 2002;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		// 打开serverChannel
		ServerSocketChannel acceptorSvr = ServerSocketChannel.open();
		// 绑定端口
		acceptorSvr.socket().bind(new InetSocketAddress(InetAddress.getByName("IP"),port));
		// 设置为非阻塞模式
		acceptorSvr.configureBlocking(false);
		// 创建reactor线程
		Selector selector = Selector.open();
//		new Thread(new ReactorTask().start());
		
	}

}
