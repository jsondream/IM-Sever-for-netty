package com.jsondream.netty.kdw.chat;

import com.jsondream.netty.kdw.chat.protocol.ErrorCode;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 16/6/20
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private ErrorCode errorCode;
    private String[] params;

    public BusinessException(ErrorCode errorCode, String... params) {
        super();
        this.errorCode = errorCode;
        this.params = params;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public String[] getParams() {
        return this.params;
    }
}
