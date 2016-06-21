package com.jsondream.netty.kdw.chat.server.messageHandler;

import com.jsondream.netty.kdw.chat.BusinessException;
import com.jsondream.netty.kdw.chat.bean.MessageBean;
import com.jsondream.netty.kdw.chat.protocol.ErrorCode;
import com.jsondream.netty.kdw.chat.server.messageHandler.businessHandler.AppMessageHandler;
import com.jsondream.netty.kdw.chat.server.messageHandler.businessHandler.ChatHandler;
import com.jsondream.netty.kdw.chat.server.messageHandler.businessHandler.LoginHandler;
import com.jsondream.netty.kdw.chat.server.messageHandler.businessHandler.PingHandler;

/**
 * <p>
 * 消息类型区分器
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 16/6/15
 */
public class AppMessageHandlerFactory {

    public static AppMessageHandler getAppMsgHandler(MessageBean msg) {

        int header = msg.getMessageType();
        if (header == ErrorCode.LOGIN_REQUEST.getCode()) {
            // 登录
            return new LoginHandler();
        } else if (header == ErrorCode.CHAT_REQUEST.getCode()) {
            // 单聊
            return new ChatHandler();
        } else if (header == ErrorCode.PING.getCode()) {
            // ping
            return new PingHandler();
        } else {
            throw new BusinessException(ErrorCode.PARAM_ERROR);
        }
    }

}
