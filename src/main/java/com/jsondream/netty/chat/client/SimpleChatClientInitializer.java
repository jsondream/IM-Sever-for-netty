package com.jsondream.netty.chat.client;

import com.jsondream.netty.chat.business.MessageDecoder;
import com.jsondream.netty.chat.business.MessageEncoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class SimpleChatClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("frameDecode", new LengthFieldBasedFrameDecoder(1024 * 1024, 0, 2, 0, 2));
        pipeline.addLast("decode", new MessageDecoder());
        //        pipeline.addLast("decode", new MessageDecoder(1024*1024,4,4));
        pipeline.addLast("FrameEncoder", new LengthFieldPrepender(2));
        pipeline.addLast("encode", new MessageEncoder());
        pipeline.addLast("handler", new SimpleChatClientHandler());
    }

}
