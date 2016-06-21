package com.jsondream.netty.kdw.chat.bean;

import com.jsondream.netty.kdw.chat.protocol.ErrorCode;
import org.msgpack.annotation.Message;

import java.io.Serializable;

@SuppressWarnings("serial")
@Message
public class MessageBean implements Serializable {
    private int messageType;
    private String errorMessage;
    private BaseBodyBean message;

    public MessageBean() {

    }

    public MessageBean(ErrorCode errorCode, BaseBodyBean message) {
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

    public BaseBodyBean getMessage() {
        return message;
    }

    public void setMessage(BaseBodyBean message) {
        this.message = message;
    }

}
