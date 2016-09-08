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
 * 聊天服务器启动器
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
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .childHandler(new AppChatServerInitializer());
            // 设置网络参数
            setServerOption(bootstrap);
            // 绑定端口
            ChannelFuture f = bootstrap.bind(port).sync();

            if (f.isSuccess()) {
                Log.info("IM server start success---------------");
                System.out.println("IM server start success---------------");
                // 开始接收进来的连接
                f.channel().closeFuture().sync();
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

    /**
     * 设置服务器网络参数
     *
     * @param bootstrap
     */
    public void setServerOption(ServerBootstrap bootstrap) {
        bootstrap.option(ChannelOption.SO_BACKLOG, 1024).childOption(ChannelOption.SO_LINGER, 0)
            .childOption(ChannelOption.SO_REUSEADDR, true).option(ChannelOption.TCP_NODELAY, true)
            .childOption(ChannelOption.SO_KEEPALIVE, true);
    }
}
