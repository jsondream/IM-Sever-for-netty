package com.jsondream.netty.testFrame;

import com.jsondream.netty.chat.business.Message;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class EchoServerHandler extends ChannelHandlerAdapter {

	 @Override
	    public void channelRead(ChannelHandlerContext ctx, Object msg)
		    throws Exception {
	     System.out.println("Server receive the msgpack message : " + msg);
		 ctx.write(msg);
	    }

	    @Override
	    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		 ctx.flush();
	    }

	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		ctx.close();
	    }
}
