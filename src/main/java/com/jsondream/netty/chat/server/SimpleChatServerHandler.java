package com.jsondream.netty.chat.server;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.msgpack.MessagePack;
import org.msgpack.type.Value;

import com.alibaba.fastjson.JSON;
import com.jsondream.netty.chat.business.Message;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

@SuppressWarnings("rawtypes")
public class SimpleChatServerHandler extends SimpleChannelInboundHandler<Object> {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static ConcurrentHashMap<String, Channel> channelMap = new ConcurrentHashMap<>();

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception { // (2)
        Channel incoming = ctx.channel();

        // Broadcast a message to multiple Channels
        channels.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入\n");
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception { // (3)
        Channel incoming = ctx.channel();

        // Broadcast a message to multiple Channels
        channels.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 离开\n");

        // A closed Channel is automatically removed from ChannelGroup,
        // so there is no need to do "channels.remove(ctx.channel());"
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object s) throws Exception { // (4)
        Channel incoming = ctx.channel();
        // for (Channel channel : channels) {
        // if (channel != incoming){
        // channel.writeAndFlush("[" + incoming.remoteAddress() + "]" + s +
        // "\n");
        // } else {
        // channel.writeAndFlush("[you]" + s + "\n");
        // }
        // }
        MessagePack msgPack = new MessagePack();

        final Message message = new Message();
        msgPack.convert((Value) s, message);
        if ("login".equals(message.getMsg())) {
            channelMap.put(message.getUserId(), incoming);

            Message sendMessage = new Message(message.getUserId(), message.getUserId(), "你已经登录成功");
            //			byte[] data = msgPack.write(sendMessage);
            incoming.writeAndFlush(sendMessage);
        } else {
            Channel channel = channelMap.get(message.getToUserId());
            if (channel == null) {
                return;
            }
            channel.writeAndFlush(message);
            //			channel.writeAndFlush("[来自" + message.getUserId() + "的消息]:"
            //			+ message.getMsg() + "\n");
        }

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
        throws Exception {


        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
	                /*读超时*/
                System.out.println("===服务端===(READER_IDLE 读超时)");
            } else if (event.state() == IdleState.WRITER_IDLE) {
	                /*写超时*/
                System.out.println("===服务端===(WRITER_IDLE 写超时)");
                ctx.channel().close();
            } else if (event.state() == IdleState.ALL_IDLE) {
	                /*总超时*/
                System.out.println("===服务端===(ALL_IDLE 总超时)");
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        Channel incoming = ctx.channel();
        InetSocketAddress add = (InetSocketAddress) incoming.remoteAddress();
        System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "在线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "掉线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (7)
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }

}
