package com.jsondream.netty.chat.business;

import java.io.Serializable;

@SuppressWarnings("serial")
@org.msgpack.annotation.Message
public class Message implements Serializable {
	private String userId;
	private String toUserId;
	private String msg;

	public Message(){
		
	}
	public Message(String userId, String msg) {
		// TODO Auto-generated constructor stub
		this.userId = userId;
		this.msg = msg;
	}

	public Message(String userId, String toUserId, String msg) {
		// TODO Auto-generated constructor stub
		this.userId = userId;
		this.toUserId = toUserId;
		this.msg = msg;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	@Override
	public String toString() {
		return "Message [userId=" + userId + ", toUserId=" + toUserId + ", msg=" + msg + "]";
	}

	
}
