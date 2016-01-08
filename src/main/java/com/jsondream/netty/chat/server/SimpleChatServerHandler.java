package com.jsondream.netty.chat.server;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;
import com.jsondream.netty.chat.business.Message;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class SimpleChatServerHandler extends SimpleChannelInboundHandler<String> {

	public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	public static ConcurrentHashMap<String, Channel> channelMap = new ConcurrentHashMap<>();

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception { // (2)
		Channel incoming = ctx.channel();

		// Broadcast a message to multiple Channels
		channels.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入\n");
		channels.add(ctx.channel());
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception { // (3)
		Channel incoming = ctx.channel();

		// Broadcast a message to multiple Channels
		channels.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 离开\n");

		// A closed Channel is automatically removed from ChannelGroup,
		// so there is no need to do "channels.remove(ctx.channel());"
	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, String s) throws Exception { // (4)
		Channel incoming = ctx.channel();
		// for (Channel channel : channels) {
		// if (channel != incoming){
		// channel.writeAndFlush("[" + incoming.remoteAddress() + "]" + s +
		// "\n");
		// } else {
		// channel.writeAndFlush("[you]" + s + "\n");
		// }
		// }
		Message message = JSON.parseObject(s, Message.class);
		if ("login".equals(message.getMsg())) {
			channelMap.put(message.getUserId(), incoming);
			incoming.writeAndFlush("你已经登录成功\n");
		} else {
			Channel channel = channelMap.get(message.getToUserId());
			if (channel == null) {
				return;
			}
			channel.writeAndFlush("[来自" + message.getUserId() + "的消息]:" 
			+ message.getMsg() + "\n");
		}

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
		Channel incoming = ctx.channel();
		InetSocketAddress add = (InetSocketAddress) incoming.remoteAddress();
		System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "在线");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
		Channel incoming = ctx.channel();
		System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "掉线");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (7)
		Channel incoming = ctx.channel();
		System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "异常");
		// 当出现异常就关闭连接
		cause.printStackTrace();
		ctx.close();
	}

}
