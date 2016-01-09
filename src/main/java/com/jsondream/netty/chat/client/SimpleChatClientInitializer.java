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

//        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
//        pipeline.addLast("encoder", new StringEncoder());
//        pipeline.addLast("decoder", new StringDecoder());
//        pipeline.addLast("handler", new SimpleChatClientHandler());
        
        pipeline.addLast("LengthFieldBasedFrameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,1,0,1));
//        pipeline.addLast("decode", new StringDecoder());
        pipeline.addLast("LengthFieldPrepender", new LengthFieldPrepender(1));
//        pipeline.addLast("encode", new StringEncoder()); 
        pipeline.addLast("handler", new SimpleChatClientHandler());
    }

}
