package com.jsondream.netty.kdw.chat.server.messageHandler;

import com.jsondream.netty.kdw.chat.BusinessException;
import com.jsondream.netty.kdw.chat.bean.BaseBodyBean;
import com.jsondream.netty.kdw.chat.bean.ChatMessageBean;
import com.jsondream.netty.kdw.chat.bean.LoginBean;
import com.jsondream.netty.kdw.chat.protocol.ErrorCode;

import java.io.IOException;

/**
 * <p>
 *     用于解析消息协议中的消息体
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 16/9/9
 */
public class AppMessageBodyParseFactory {

    public static Class<? extends BaseBodyBean> parseBodyClass(int header)
        throws IOException {
        if (header == ErrorCode.LOGIN_REQUEST.getCode()) {
            return LoginBean.class;
        } else if (header == ErrorCode.CHAT_REQUEST.getCode()) {
            // 单聊
            return ChatMessageBean.class;
        } else if (header == ErrorCode.PING.getCode()) {
            // ping
            return BaseBodyBean.class;
        } else {
            throw new BusinessException(ErrorCode.PROTOCOL_ERROR);
        }
    }
}
