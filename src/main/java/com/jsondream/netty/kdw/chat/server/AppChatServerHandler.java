package com.jsondream.netty.kdw.chat.server;

import com.jsondream.netty.kdw.chat.bean.MessageBean;
import com.jsondream.netty.kdw.chat.server.connectionManager.AppRouterManager;
import com.jsondream.netty.kdw.chat.server.connectionManager.ChannelManager;
import com.jsondream.netty.kdw.chat.server.messageHandler.AppMessageHandlerFactory;
import com.jsondream.netty.kdw.chat.server.messageHandler.businessHandler.AppMessageHandler;
import com.jsondream.netty.kdw.chat.utils.StringUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import org.msgpack.MessagePack;
import org.msgpack.type.Value;

import java.net.InetSocketAddress;

@SuppressWarnings("rawtypes")
public class AppChatServerHandler extends SimpleChannelInboundHandler<MessageBean> {


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception { // (2)
        Channel incoming = ctx.channel();

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception { // (3)
        Channel incoming = ctx.channel();

    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, MessageBean message) throws Exception { // (4)
        Channel incoming = ctx.channel();
//        MessagePack msgPack = new MessagePack();
//
//        final MessageBean message = new MessageBean();
//        msgPack.convert((Value) s, message);
        // 先查看是什么类型的业务
        AppMessageHandler handler= AppMessageHandlerFactory.getAppMsgHandler(message);
        // 进行相应的业务处理
        handler.process(incoming,message.getMessage());
    }

    /**
     * 心跳超时处理
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
        throws Exception {


        if (evt instanceof IdleStateEvent) {
            String userId = ctx.channel().attr(AppAttrKeys.USER_ID).get();
            // 验证连接可信度
            if ( StringUtils.isEmpty(userId) || ChannelManager.getConn(userId) == null) {// 连接不可信
                // 关闭客户端连接
                ctx.channel().close();
                return;
            }
            // 空闲事件(读或者写)
            IdleStateEvent event = (IdleStateEvent) evt;
            // Log.info("event:" + event.state() + "--" + event.isFirst());
            if (event.isFirst()) {
                // 如果是第一次触发,发送心跳包
                AppRouterManager.routePing(ctx.channel());
            } else {
                // 移除连接
                ChannelManager.removeConn(userId);
                ctx.channel().close();
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
