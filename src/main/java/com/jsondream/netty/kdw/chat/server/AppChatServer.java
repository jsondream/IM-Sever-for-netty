package com.jsondream.netty.kdw.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 *     聊天服务器启动器
 * </p>
 *
 * @author 王光东
 * @version 1.0
 * @date 2016年06月14日18:33:32
 */
public class AppChatServer {

    private final Logger Log = LoggerFactory.getLogger(AppChatServer.class);
    private EventLoopGroup bossGroup = null;
    private EventLoopGroup workerGroup = null;

    public AppChatServer(int workerNum) {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup(workerNum);
    }

    public void Start(int port) throws Exception {

        try {
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class) // (3)
                .childHandler(new AppChatServerInitializer())  //(4)
                .option(ChannelOption.SO_BACKLOG, 1024)          // (5)
                .childOption(ChannelOption.SO_LINGER, 0)
                .childOption(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

            // 绑定端口，开始接收进来的连接
            ChannelFuture f = b.bind(port).sync(); // (7)

            if (f.isSuccess()) {
                Log.info("IM server start success---------------");
            }

        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();

            System.out.println("IM server close");
        }
    }

    /**
     * 停止服务器(一般用不到)
     */
    public void shoutdown() {
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();

    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8012;
        }
        new AppChatServer(200).Start(port);

    }
}
