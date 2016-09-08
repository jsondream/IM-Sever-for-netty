package com.jsondream.netty.kdw.chat.client;

import com.jsondream.netty.kdw.chat.bean.ChatMessageBean;
import com.jsondream.netty.kdw.chat.bean.MessageBean;
import com.jsondream.netty.kdw.chat.protocol.ErrorCode;
import com.jsondream.netty.kdw.chat.server.AppAttrKeys;
import com.jsondream.netty.kdw.chat.server.connectionManager.AppRouterManager;
import com.jsondream.netty.kdw.chat.server.connectionManager.ChannelManager;
import com.jsondream.netty.kdw.chat.server.messageHandler.AppMessageHandlerFactory;
import com.jsondream.netty.kdw.chat.server.messageHandler.businessHandler.AppMessageHandler;
import com.jsondream.netty.kdw.chat.server.messageHandler.businessHandler.chatHandler.ChatType;
import com.jsondream.netty.kdw.chat.server.messageHandler.businessHandler.chatHandler.PrivateChatHandler;
import com.jsondream.netty.kdw.chat.utils.StringUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import org.msgpack.MessagePack;
import org.msgpack.type.Value;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("rawtypes")
public class AppChatClientHandler extends SimpleChannelInboundHandler<Object> {


    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object s) throws Exception { // (4)
        Channel incoming = ctx.channel();
        MessagePack msgPack = new MessagePack();

        final MessageBean message = new MessageBean();
        msgPack.convert((Value) s, message);
        if (message.getMessageType() == ErrorCode.CHAT_REQUEST.getCode()) {
            ChatMessageBean chatMessageBean = (ChatMessageBean) message.getMessage();
            String header = chatMessageBean.getChatType();
            if (header.equals(ChatType.CHAT.getType())) {
                System.out.println(
                    "id为[" + chatMessageBean.getFromUser() + "]的用户对你说:" + chatMessageBean
                        .getToUser());
            }
        }
        System.out.println(message);
    }



}
