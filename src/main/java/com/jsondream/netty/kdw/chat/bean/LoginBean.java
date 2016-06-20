package com.jsondream.netty.kdw.chat.bean;

import org.msgpack.annotation.Message;

/**
 * <p>
 * 登录实体类
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 16/6/15
 */
@Message
public class LoginBean extends BaseBodyBean {

    private String userId;
    private String userName;
    // 密码
    private String passWord;
    private String authKey;
    // 客户端平台:android,ios,web
    private String platform;
    // APP版本号
    private String appVersion;

    public LoginBean() {
    }

    public LoginBean(String userId, String userName, String passWord, String authKey,
        String platform, String appVersion) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
        this.authKey = authKey;
        this.platform = platform;
        this.appVersion = appVersion;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}
