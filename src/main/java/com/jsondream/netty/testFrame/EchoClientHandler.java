package com.jsondream.netty.testFrame;

import java.util.ArrayList;
import java.util.List;

import com.jsondream.netty.chat.business.Message;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class EchoClientHandler extends ChannelHandlerAdapter {

	private final int sendNumber;

	public EchoClientHandler(int sendNumber) {
		// TODO Auto-generated constructor stub
		this.sendNumber = sendNumber;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		List<Message> list = new ArrayList<>();
		for (int i = 0; i < sendNumber; i++) {
			Message message = new Message("" + i, "" + i, "login");
			list.add(message);
		}
		list.parallelStream().forEach(e -> ctx.write(e));
		ctx.flush();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object s) throws Exception {
		System.out.println(s);
		// ctx.write(s);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}
}
