package com.jsondream.netty.kdw.chat;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 16/6/16
 */
public enum HeaderCode {
    LOGIN_REQUEST(1), LOGIN_SUCCESS(2),
    CHAT_REQUEST(100), SEND_MESSAGE(101), RECEIVE_MESSAGE(102),
    PING(200), PONG(201),;
    int code;

    HeaderCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
