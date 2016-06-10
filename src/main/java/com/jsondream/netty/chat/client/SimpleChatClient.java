package com.jsondream.netty.chat.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.alibaba.fastjson.JSON;
import com.jsondream.netty.chat.business.Message;
import com.jsondream.netty.chat.util.ClientUtil;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class SimpleChatClient {
    public static void main(String[] args) throws Exception {
        new SimpleChatClient("localhost", 8012).run();
    }

    private final String host;
    private final int port;

    public SimpleChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap().group(group).channel(NioSocketChannel.class)
                .handler(new SimpleChatClientInitializer());
            Channel channel = bootstrap.connect(host, port).sync().channel();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                //				channel.writeAndFlush(id + "," + in.readLine() + "\r\n");
                String inputString = in.readLine();
                String[] inputObj = inputString.split(",");
                Message message = new Message(inputObj[0], inputObj[1], inputObj[2]);
                String messageString = JSON.toJSONString(message);
                byte[] messageByte = ClientUtil.getMessageByte(messageString);
                channel.writeAndFlush(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }

}
