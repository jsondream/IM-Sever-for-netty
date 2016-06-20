package com.jsondream.netty.kdw.chat.server.messageHandler.businessHandler.chatHandler;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 16/6/20
 */
public enum ChatType {
    CHAT("chat", "单聊"), GROUP_CHAT("groupChat", "群聊"), CHAT_ROOM("chatRoom", "聊天室");
    String type;
    String description;

    ChatType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
