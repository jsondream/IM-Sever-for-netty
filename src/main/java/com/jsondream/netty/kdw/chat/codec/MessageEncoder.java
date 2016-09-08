package com.jsondream.netty.kdw.chat.codec;

import com.jsondream.netty.kdw.chat.bean.MessageBean;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;
import org.msgpack.packer.Packer;

import java.io.ByteArrayOutputStream;

/**
 * <p>
 *     messagePack编码器
 * </p>
 *
 * @author 王光东
 * @version 1.0
 * @date 2016年06月14日18:33:32
 */
public class MessageEncoder extends MessageToByteEncoder<MessageBean> {

    @Override
    protected void encode(ChannelHandlerContext ctx, MessageBean msg, ByteBuf out) throws Exception {
        MessagePack messagePack = new MessagePack();
//        byte[] data = messagePack.write(msg);
//        out.writeBytes(data);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Packer packer = messagePack.createPacker(outputStream);
        packer.write(msg);
        packer.write(msg.getMessage());
        byte[] bs = outputStream.toByteArray();
        out.writeBytes(bs);
    }

}
