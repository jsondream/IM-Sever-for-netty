package com.jsondream.netty.nio;

public class TimeClient implements Runnable {

	public TimeClient(String string, int port) {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        new Thread(new TimeClient("127.0.0.1",TimeServer.port)).start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
