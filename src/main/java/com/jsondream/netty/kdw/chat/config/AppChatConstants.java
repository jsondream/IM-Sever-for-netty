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
public class AppChatConstants {
    /**
     * 读操作超时时间
     */
    public static final int READER_IDLE_TIME = 70;
    /**
     * 写操作超时时间
     */
    public static final int WRITE_IDLE_TIME = 60;
    /**
     * 读写操作的超时时间
     */
    public static final int ALL_IDLE_TIME = 100;
}
