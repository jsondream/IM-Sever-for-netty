package com.jsondream.netty.kdw.chat.server.messageHandler.businessHandler.chatHandler;

import com.jsondream.netty.kdw.chat.bean.ChatMessageBean;
import io.netty.channel.Channel;

/**
 * <p>
 * 聊天业务handler处理
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 16/6/20
 */
public abstract class AbstractChatHandler {
    // 聊天接口
    public abstract void chat(Channel channel, ChatMessageBean chatMessage);
}
