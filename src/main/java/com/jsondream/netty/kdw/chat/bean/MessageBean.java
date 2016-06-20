package com.jsondream.netty.kdw.chat.bean;

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

    public MessageBean(int messageType) {
        // TODO Auto-generated constructor stub
        this.messageType = messageType;
    }

    public MessageBean(int messageType, BaseBodyBean message) {
        this.messageType = messageType;
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
