package com.jsondream.netty.kdw.chat.client;

import com.jsondream.netty.chat.server.SimpleChatServerHandler;
import com.jsondream.netty.kdw.chat.codec.MessageDecoder;
import com.jsondream.netty.kdw.chat.codec.MessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * <p>
 *     聊天服务器通道链
 * </p>
 *
 * @author 王光东
 * @version 1.0
 * @date 2016年06月14日18:33:32
 */
public class AppChatClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 沾包粘包解码器
        pipeline.addLast("frameDecode", new LengthFieldBasedFrameDecoder(1024 * 1024, 0, 2, 0, 2));
        // 解码器
        pipeline.addLast("decode", new MessageDecoder());
        // 沾包粘包编码器
        pipeline.addLast("FrameEncoder", new LengthFieldPrepender(2));
        // 编码器
        pipeline.addLast("encode", new MessageEncoder());
        // 业务处理器
        pipeline.addLast("messageHandler", new AppChatClientHandler());

    }
}
