package com.jsondream.netty.kdw.chat.server.connectionManager;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
     * 存放所有聊天室连接  groupId-channelGroup
     */
    private static Map<String, DefaultChannelGroup> groupConnMap = new ConcurrentHashMap<>();

    private static Lock lock = new ReentrantLock();

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

    /**
     * 像群组中添加链接
     * @param roomId
     * @param channel
     */
    public static void addConnGroup(String roomId, Channel channel) {
        DefaultChannelGroup channelGroup = groupConnMap.get(roomId);
        if(channelGroup == null){
            lock.lock();
            try {
                channelGroup = new DefaultChannelGroup(roomId + ":ChannelGroups", GlobalEventExecutor.INSTANCE);
                groupConnMap.put(roomId,channelGroup);
            } finally {
                lock.unlock();
            }
        }
        channelGroup.add(channel);
    }

    /**
     * 移除连接
     *
     * @param roomId
     */
    public static DefaultChannelGroup getConnGroup(String roomId) {
        return groupConnMap.get(roomId);
    }
}
