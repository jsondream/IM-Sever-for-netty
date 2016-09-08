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
        // 处理消息体没有遵循协议规范的情况
        if (!value.isArrayValue()) {
            //TODO 抛出一个异常让全局能够处理
        }
        ArrayValue arrayValue = value.asArrayValue();
        // 读取消息类型
        int type = arrayValue.get(0).asIntegerValue().getInt();

        BaseBodyBean baseBodyBean = null;
        if(type == ErrorCode.CHAT_REQUEST.getCode()){
                // 根据协议解析消息体
                baseBodyBean = messagePack.convert(arrayValue.get(2), ChatMessageBean.class);
        } else {
            //TODO 抛出一个异常让全局能够处理
        }
        ErrorCode errorCode = ErrorCode.getErrorCodeById(type);
        MessageBean messageBean = new MessageBean(errorCode, baseBodyBean);
        out.add(messageBean);
    }
}
