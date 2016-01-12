package com.jsondream.netty.chat.client;

import org.msgpack.MessagePack;
import org.msgpack.type.Value;

import com.jsondream.netty.chat.business.Message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SimpleChatClientHandler extends  SimpleChannelInboundHandler<Object> {
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object message) throws Exception {
    	MessagePack msgPack = new MessagePack();
    	final Message s = new Message();
		msgPack.convert((Value)message, s);
        System.out.println("["+((Message) s).getUserId()+"]对你说:"+((Message) s).getMsg());
    }
}
