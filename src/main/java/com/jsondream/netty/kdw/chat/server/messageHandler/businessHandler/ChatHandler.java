package com.jsondream.netty.kdw.chat.server.messageHandler.businessHandler;

import com.jsondream.netty.kdw.chat.BusinessException;
import com.jsondream.netty.kdw.chat.bean.ChatMessageBean;
import com.jsondream.netty.kdw.chat.protocol.ErrorCode;
import com.jsondream.netty.kdw.chat.server.messageHandler.businessHandler.chatHandler.AbstractChatHandler;
import com.jsondream.netty.kdw.chat.server.messageHandler.businessHandler.chatHandler.ChatTypeHandlerFactory;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 16/6/15
 */
public class ChatHandler implements AppMessageHandler<ChatMessageBean> {

    /**
     * 创建日志对象
     */
    private final Logger Log = LoggerFactory.getLogger(getClass());

    @Override
    public void process(Channel channel, ChatMessageBean msg) {
        AbstractChatHandler chatHandler = ChatTypeHandlerFactory.getChatTypeHandler(msg);
        chatHandler.chat(channel,msg);
    }

}
