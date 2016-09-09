package com.jsondream.netty.kdw.chat.codec;

import com.jsondream.netty.kdw.chat.bean.BaseBodyBean;
import com.jsondream.netty.kdw.chat.bean.ChatMessageBean;
import com.jsondream.netty.kdw.chat.bean.MessageBean;
import com.jsondream.netty.kdw.chat.protocol.ErrorCode;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;
import org.msgpack.type.ArrayValue;
import org.msgpack.type.Value;
import org.msgpack.unpacker.Unpacker;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * <p>
 * messagePack解码器
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
        final byte[] array;
        final int length = in.readableBytes();
        array = new byte[length];
        in.getBytes(in.readerIndex(), array, 0, length);
        MessagePack messagePack = new MessagePack();
        //        out.add(messagePack.read(array));

        ByteArrayInputStream inputStream = new ByteArrayInputStream(array);
        Unpacker unpacker = messagePack.createUnpacker(inputStream);
        // 读取value值
        Value value = unpacker.readValue();
        out.add(value);
    }
}
