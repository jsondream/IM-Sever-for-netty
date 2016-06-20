package com.jsondream.netty.kdw.chat.server.connectionManager;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * APP连接管理器
 * </p>
 *
 * @author 王光东
 * @version 1.0
 * @date 2016年06月14日18:33:32
 */
public class ChannelManager {

    /**
     * 存放所有客户端登陆连接
     */
    private static Map<String, Channel> appConnMap = new ConcurrentHashMap<>();

    /**
     * 添加连接
     *
     * @param userId
     * @param channel
     */
    public static void addConn(String userId, Channel channel) {
        appConnMap.put(userId, channel);
    }

    /**
     * 移除连接
     *
     * @param userId
     */
    public static void removeConn(String userId) {
        if (appConnMap.containsKey(userId)) {
            appConnMap.remove(userId);
        }
    }

    /**
     * 获取用户Channel
     *
     * @param userId
     * @return
     */
    public static Channel getConn(String userId) {
        return appConnMap.get(userId);
    }

}
