package com.jsondream.netty.kdw.chat.client;

import com.alibaba.fastjson.JSON;
import com.jsondream.netty.kdw.chat.BusinessException;
import com.jsondream.netty.kdw.chat.bean.BaseBodyBean;
import com.jsondream.netty.kdw.chat.bean.ChatMessageBean;
import com.jsondream.netty.kdw.chat.bean.MessageBean;
import com.jsondream.netty.kdw.chat.protocol.ErrorCode;
import com.jsondream.netty.kdw.chat.server.AppAttrKeys;
import com.jsondream.netty.kdw.chat.server.connectionManager.AppRouterManager;
import com.jsondream.netty.kdw.chat.server.connectionManager.ChannelManager;
import com.jsondream.netty.kdw.chat.server.messageHandler.AppMessageBodyParseFactory;
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
import org.msgpack.type.ArrayValue;
import org.msgpack.type.Value;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("rawtypes")
public class AppChatClientHandler extends SimpleChannelInboundHandler<Value> {


    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Value value) throws Exception { // (4)
        Channel incoming = ctx.channel();
        MessagePack msgPack = new MessagePack();


        // 处理消息体没有遵循协议规范的情况
        if (!value.isArrayValue()) {
            AppRouterManager.routeProtocolError(ctx.channel());
            return;
        }
        try {
            ArrayValue arrayValue = value.asArrayValue();

            // 读取消息类型
            int headerType = arrayValue.get(0).asIntegerValue().getInt();

            // 获取消息体本身
            Value bodyValue = arrayValue.get(2);



            // 判断类型
            if (headerType == ErrorCode.CHAT_REQUEST.getCode()) {
                // 根据type来解析消息体内容
                ChatMessageBean chatMessageBean = msgPack.convert(bodyValue,ChatMessageBean.class);
                String header = chatMessageBean.getChatType();
                if (header.equals(ChatType.CHAT.getType())) {
                    System.out.println(
                        "id为[" + chatMessageBean.getFromUser() + "]的用户对你说:" + chatMessageBean
                            .getToUser());
                }
                return;
            }

            if(headerType == ErrorCode.LOGIN_SUCCESS.getCode()){
                System.out.println(value);
                return;
            }

            if(headerType == ErrorCode.ERROR_AUTHED.getCode()){
                System.out.println("登录失败");
                return;
            }

            System.out.println(value);

        } catch (Exception e) {
            e.printStackTrace();
        }



    }



}
