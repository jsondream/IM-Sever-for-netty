package com.jsondream.netty.kdw.chat.client;

import com.alibaba.fastjson.JSON;
import com.jsondream.netty.chat.business.Message;
import com.jsondream.netty.chat.client.SimpleChatClientInitializer;
import com.jsondream.netty.chat.util.ClientUtil;
import com.jsondream.netty.kdw.chat.bean.ChatMessageBean;
import com.jsondream.netty.kdw.chat.bean.LoginBean;
import com.jsondream.netty.kdw.chat.bean.MessageBean;
import com.jsondream.netty.kdw.chat.protocol.ErrorCode;
import com.jsondream.netty.kdw.chat.server.AppChatServerInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.UUID;

/**
 * <p>
 * 聊天服务器启动器
 * </p>
 *
 * @author 王光东
 * @version 1.0
 * @date 2016年06月14日18:33:32
 */
public class AppChatClient {

    private final Logger Log = LoggerFactory.getLogger(AppChatClient.class);

    public void Start(int port) throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap().group(group).channel(NioSocketChannel.class)
                .handler(new AppChatClientInitializer());
            Channel channel = bootstrap.connect("127.0.0.1", port).sync().channel();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            for (; ; ) {
                String inputString = in.readLine();
                String[] inputObj = inputString.split(",");

                switch (inputObj[0]){
                    case "login":
                        loginServer(channel,inputObj[1],inputObj[2]);
                        break;
                    case "chat":
                        sendChatMessage(channel,inputObj[1],inputObj[2],inputObj[3]);
                        break;
                    default:break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("连接服务器失败,程序退出");
            System.exit(1);
        } finally {
            group.shutdownGracefully();
        }
    }


    public static void sendChatMessage(Channel channel,String from,String to ,String msg){
        ChatMessageBean message = new ChatMessageBean();
        message.setId(UUID.randomUUID().toString());
        message.setFromUser(from);
        message.setToUser(to);
        message.setMsg(msg);
        message.setChatType("chat");
        message.setMessageType("txt");
        message.setTimeStamp(System.currentTimeMillis());
        //                                String messageString = JSON.toJSONString(message);
        //                                byte[] messageByte = ClientUtil.getMessageByte(messageString);

        MessageBean messageBean = new MessageBean(ErrorCode.CHAT_REQUEST,message);
        ChannelFuture channelFuture = channel.writeAndFlush(messageBean);
    }

    public static void loginServer(Channel channel,String userId,String userName){
        LoginBean loginBean = new LoginBean();
        loginBean.setUserId(userId);
        loginBean.setAuthKey("authKey");
        loginBean.setAppVersion("v1");
        loginBean.setPlatform("pc");
        loginBean.setUserName(userName);
        channel.writeAndFlush(new MessageBean(ErrorCode.LOGIN_REQUEST,loginBean));

    }

    public static void main(String[] args) throws Exception {
        new AppChatClient().Start(8012);
    }
}
