package com.jsondream.netty.kdw.chat.bean;

import org.msgpack.annotation.Message;

/**
 * <p>
 * 聊天实体类
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 16/6/15
 */
@Message
public class ChatMessageBean extends BaseBodyBean {

    // 消息序列号
    private String id;
    // 发送者
    private String fromUser;
    // 接受者
    private String toUser;
    // 消息发送的时间戳
    private long timeStamp;
    // 用来判断单聊还是群聊。chat:单聊，groupchat:群聊
    private String chatType;
    // 聊天类型 video 视频，txt 文本，audio 语音，img 图片，site 位置，cmd 透传
    private String messageType;
    // 消息体
    private String msg;
    // url地址
    private String url;
    // 文件名
    private String fileName;
    // 上传缩略图地址
    private String thumb;
    // 获取url需要用密匙
    private String secret;
    // thumb_secret在上传缩略图后会返回
    private String thumb_secret;
    // 语音或者视频的时间长度
    private int length;
    // 文件大小
    private long fileLength;
    // 要发送的地址
    private String addr;
    // 纬度
    private double lat;
    // 经度
    private double lng;
    // 扩展消息体
    private String ext;

    public ChatMessageBean() {
    }

    public ChatMessageBean(String id, String fromUser, String toUser, long timeStamp,
        String chatType, String messageType, String msg, String url, String fileName, String thumb,
        String secret, String thumb_secret, int length, long fileLength, String addr, double lat,
        double lng, String ext) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.timeStamp = timeStamp;
        this.chatType = chatType;
        this.messageType = messageType;
        this.msg = msg;
        this.url = url;
        this.fileName = fileName;
        this.thumb = thumb;
        this.secret = secret;
        this.thumb_secret = thumb_secret;
        this.length = length;
        this.fileLength = fileLength;
        this.addr = addr;
        this.lat = lat;
        this.lng = lng;
        this.ext = ext;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getThumb_secret() {
        return thumb_secret;
    }

    public void setThumb_secret(String thumb_secret) {
        this.thumb_secret = thumb_secret;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
