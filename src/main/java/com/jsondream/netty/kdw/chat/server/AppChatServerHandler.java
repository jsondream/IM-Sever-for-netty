package com.jsondream.netty.kdw.chat.server;

import com.jsondream.netty.kdw.chat.bean.MessageBean;
import com.jsondream.netty.kdw.chat.server.messageHandler.AppMessageHandlerFactory;
import com.jsondream.netty.kdw.chat.server.messageHandler.businessHandler.AppMessageHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.msgpack.MessagePack;
import org.msgpack.type.Value;

import java.net.InetSocketAddress;

@SuppressWarnings("rawtypes")
public class AppChatServerHandler extends SimpleChannelInboundHandler<Object> {


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception { // (2)
        Channel incoming = ctx.channel();

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception { // (3)
        Channel incoming = ctx.channel();

    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object s) throws Exception { // (4)
        Channel incoming = ctx.channel();
        MessagePack msgPack = new MessagePack();

        final MessageBean message = new MessageBean();
        msgPack.convert((Value) s, message);
        // 先查看是什么类型的业务
        AppMessageHandler handler= AppMessageHandlerFactory.getAppMsgHandler(message);
        // 进行相应的业务处理
        if( handler != null){
            handler.process(incoming,message.getMessage());
        }else {
            incoming.writeAndFlush(message);
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
