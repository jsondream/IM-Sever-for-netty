package com.jsondream.netty.kdw.chat.config;

/**
 * <p>
 *     聊天相关参数
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 16/6/14
 */
public class AppHeaderConstants {
    /**
     * 心跳检测
     */
    public static int HEARTBEAT = 1000;

    /**
     * 登录请求
     */
    public static int LOGIN_REQUEST = 1001;

    /**
     * 发送聊天请求
     */
    public static int CHAT_REQUEST = 1003;

    /**
     * 发送聊天响应
     */
    public static int CHAT_RESPONSE = 1004;

    /**
     * 聊天推送请求
     */
    public static int CHATPUSH_REQUEST = 1005;

    /**
     * 发送推送响应
     */
    public static int CHATPUSH_RESPONSE = 1006;

    /**
     * 添加好友请求
     */
    public static int ADDFRIEND_REQUEST = 1007;

    /**
     * 添加好友响应
     */
    public static int ADDFRIEND_RESPONSE = 1008;

    /**
     * 邀请好友入群请求
     */
    public static int INVITEFRIEND_REQUEST = 1009;

    /**
     * 邀请好友入群响应
     */
    public static int INVITEFRIEND_RESPONSE = 1010;

    /**
     * 申请入群请求
     */
    public static int APPLYGROUPS_REQUEST = 1011;

    /**
     * 申请入群响应
     */
    public static int APPLYGROUPS_RESPONSE = 1012;

    /**
     * 注销请求
     */
    public static int LOGOUT_REQUEST = 1013;

    /**
     * 注销响应
     */
    public static int LOGOUT_RESPONSE = 1014;

    /**
     * 群消息
     */
    public static int GROUP_MSG_TYPE = 1;

    /**
     * 个人消息
     */
    public static int PERSONAL_MSG_TYPE = 0;
}
