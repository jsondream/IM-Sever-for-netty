package com.jsondream.netty.kdw.chat.codec;

import com.jsondream.netty.chat.business.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * <p>
 *     messagePack编码器
 * </p>
 *
 * @author 王光东
 * @version 1.0
 * @date 2016年06月14日18:33:32
 */
public class MessageEncoder extends MessageToByteEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        // TODO Auto-generated method stub
        MessagePack messagePack = new MessagePack();
        byte[] data = messagePack.write(msg);
        out.writeBytes(data);
    }

}
