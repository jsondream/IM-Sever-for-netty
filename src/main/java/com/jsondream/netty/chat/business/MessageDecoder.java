package com.jsondream.netty.chat.business;

import java.util.List;

import org.msgpack.MessagePack;

import com.jsondream.netty.chat.SerializeUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;

public class MessageDecoder extends MessageToMessageDecoder<ByteBuf> {

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

	/*public MessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		ByteBuf frame = (ByteBuf) super.decode(ctx, in);
		if (frame == null) {
		    return null;
		}

		MessagePack messagePack = new MessagePack();
		final byte[] array;
		final int length = frame.readableBytes();
		array = new byte[length];
		in.getBytes(frame.readerIndex(), array, 0, length);
		return messagePack.read(array);
		// TODO Auto-generated method stub
		
	}*/

}
