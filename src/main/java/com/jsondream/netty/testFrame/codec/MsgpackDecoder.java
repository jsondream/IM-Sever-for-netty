package com.jsondream.netty.testFrame.codec;

import java.util.List;

import org.msgpack.MessagePack;

import com.jsondream.netty.chat.SerializeUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;

public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		// TODO Auto-generated method stub
		final byte[] array;
		final int length = in.readableBytes();
		array = new byte[length];
		in.getBytes(in.readerIndex(), array, 0, length);
		MessagePack messagePack = new MessagePack();
        out.add(messagePack.read(array));
	}


}
