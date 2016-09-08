package com.jsondream.netty.kdw.chat.bean;

import com.jsondream.netty.kdw.chat.protocol.ErrorCode;
import org.msgpack.annotation.Message;

import java.io.Serializable;

@SuppressWarnings("serial")
@Message
public class MessageBean<T extends BaseBodyBean> implements Serializable {
    private int messageType;
    private String errorMessage;
    private T message;

    public MessageBean() {

    }

    public MessageBean(ErrorCode errorCode, T message) {
        this.messageType = errorCode.getCode();
        this.errorMessage = errorCode.getMsg();
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorMessage = errorCode.getMsg();
        this.messageType= errorCode.getCode();

    }


}
