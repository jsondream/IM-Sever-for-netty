package com.jsondream.netty.kdw.chat.server.messageHandler.businessHandler.chatHandler;

import com.jsondream.netty.kdw.chat.bean.ChatMessageBean;
import com.jsondream.netty.kdw.chat.bean.MessageBean;
import com.jsondream.netty.kdw.chat.protocol.ErrorCode;
import com.jsondream.netty.kdw.chat.server.connectionManager.AppRouterManager;
import com.jsondream.netty.kdw.chat.server.connectionManager.ChannelManager;
import io.netty.channel.Channel;

/**
 * <p>
 * 私聊业务
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 16/6/20
 */
public class PrivateChatHandler extends AbstractChatHandler {

    @Override
    public void chat(Channel channel, ChatMessageBean chatMessage) {
        // 找到接收人对应的channel
        String toUser = chatMessage.getToUser();
        Channel toUserChannel = ChannelManager.getConn(toUser);
        // 构造参数
        MessageBean<ChatMessageBean> messageBean =
            new MessageBean<>(ErrorCode.CHAT_REQUEST, chatMessage);
        // 发送数据
        AppRouterManager.routeMessage(toUserChannel, messageBean);
    }

}
