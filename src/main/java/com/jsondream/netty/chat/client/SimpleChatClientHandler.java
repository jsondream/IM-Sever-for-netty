package com.jsondream.netty.chat.client;

import com.jsondream.netty.chat.business.Message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SimpleChatClientHandler extends  SimpleChannelInboundHandler<Message> {
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Message s) throws Exception {
        System.out.println("["+s.getUserId()+"]对你说:"+s.getMsg());
    }
}
