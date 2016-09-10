package com.jsondream.netty.kdw.chat.server.messageHandler.businessHandler.chatHandler;

import com.jsondream.netty.kdw.chat.bean.ChatMessageBean;
import com.jsondream.netty.kdw.chat.bean.MessageBean;
import com.jsondream.netty.kdw.chat.protocol.ErrorCode;
import com.jsondream.netty.kdw.chat.server.connectionManager.AppRouterManager;
import com.jsondream.netty.kdw.chat.server.connectionManager.ChannelManager;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

/**
 * <p>
 * 群组聊天业务
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 16/6/20
 */
public class GroupChatHandler extends AbstractChatHandler {

    @Override
    public void chat(Channel channel, ChatMessageBean chatMessage) {
        // 获取群组id
        String groupId = chatMessage.getToUser();
        // 获取ChannelGroup
        ChannelGroup channels = ChannelManager.getConnGroup(groupId);
        MessageBean<ChatMessageBean> messageBean =
            new MessageBean<>(ErrorCode.CHAT_REQUEST, chatMessage);
        // 发送消息
        AppRouterManager.routeGroupMessage(channels, messageBean);
    }
}
