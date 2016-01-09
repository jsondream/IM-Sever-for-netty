package com.jsondream.netty.chat.client;

import com.jsondream.netty.chat.business.Message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SimpleChatClientHandler extends  SimpleChannelInboundHandler<Object> {
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object s) throws Exception {
        System.out.println((String)s);
    }
}
