package com.jsondream.netty.testFrame;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.alibaba.fastjson.JSON;
import com.jsondream.netty.chat.business.Message;
import com.jsondream.netty.chat.client.SimpleChatClient;
import com.jsondream.netty.chat.client.SimpleChatClientInitializer;
import com.jsondream.netty.chat.util.ClientUtil;
import com.jsondream.netty.testFrame.codec.MsgpackDecoder;
import com.jsondream.netty.testFrame.codec.MsgpackEncoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoClient {

	private final String host;
	private final int port;
	private final int sendNumber;

	public EchoClient(String host, int port,int sendNumber) {
		this.host = host;
		this.port = port;
		this.sendNumber = sendNumber;
	}

	public void run() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap().group(group).channel(NioSocketChannel.class)
					.handler(new ChannelInitializer<SocketChannel>(){
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ChannelPipeline c = ch.pipeline();
							c.addLast(new MsgpackDecoder());
							c.addLast(new MsgpackEncoder());
							c.addLast(new EchoClientHandler(sendNumber));
						}
						
					});
			Channel channel = bootstrap.connect(host, port).sync().channel();
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
//				channel.writeAndFlush(id + "," + in.readLine() + "\r\n");
				String inputString = in.readLine();
				String[] inputObj = inputString.split(",");
				Message message = new Message(inputObj[0],inputObj[1],inputObj[2]);
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
	public static void main(String[] args) throws Exception {
		new EchoClient("localhost", 8011,2).run();
	}
}
