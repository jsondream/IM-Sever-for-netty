package com.jsondream.netty.nio;

public class TimeServer {

	public static int port = 2011;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
		new Thread(timeServer,"Nio-timerServer-001").start();;
	}

}
