package com.jsondream.netty.kdw.chat.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;
/**
 * <p>
 *     messagePack解码器
 * </p>
 *
 * @author 王光东
 * @version 1.0
 * @date 2016年06月14日18:33:32
 */
public class MessageDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out)
        throws Exception {
        // TODO Auto-generated method stub
        final byte[] array;
        final int length = in.readableBytes();
        array = new byte[length];
        in.getBytes(in.readerIndex(), array, 0, length);
        MessagePack messagePack = new MessagePack();
        out.add(messagePack.read(array));
    }
}
