package com.jsondream.netty.chat.business;

import com.jsondream.netty.chat.SerializeUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder extends MessageToByteEncoder<Message> {
	
	@Override
	protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
		// TODO Auto-generated method stub
		byte[] data = SerializeUtil.serialize(msg);
        out.writeInt(data.length);
        out.writeBytes(data);
	}

}
