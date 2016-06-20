package com.jsondream.netty.kdw.chat.server.messageHandler.businessHandler.chatHandler;

import com.jsondream.netty.kdw.chat.BusinessException;
import com.jsondream.netty.kdw.chat.bean.ChatMessageBean;
import com.jsondream.netty.kdw.chat.protocol.ErrorCode;

/**
 * <p>
 * 消息类型区分器
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 16/6/15
 */
public class ChatTypeHandlerFactory {

    public static AbstractChatHandler getChatTypeHandler(ChatMessageBean msg) {

        String header = msg.getChatType();
        if (header.equals(ChatType.CHAT.getType())) {
            // 单聊
            return new PrivateChatHandler();
        } else if (header.equals(ChatType.GROUP_CHAT.getType())) {
            // 群聊
            return new GroupChatHandler();
        } else if (header.equals(ChatType.CHAT_ROOM.getType())) {
            // 聊天室
            return new ChatRoomHandler();
        } else {
            throw new BusinessException(ErrorCode.CHAT_TYPE_ERROR);
        }
    }

}
