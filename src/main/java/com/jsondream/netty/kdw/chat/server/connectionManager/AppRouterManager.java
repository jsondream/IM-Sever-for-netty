package com.jsondream.netty.kdw.chat.server.connectionManager;

import com.jsondream.netty.kdw.chat.bean.MessageBean;
import com.jsondream.netty.kdw.chat.protocol.ErrorCode;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * app消息发送管理器
 *
 * @author jacklin
 */
public class AppRouterManager {

    /**
     * 生成日志对象
     */
    private final static Logger Log = LoggerFactory.getLogger(AppRouterManager.class);

    /**
     * 发送登录成功消息
     *
     * @param channel
     */
    public static void routeLoginSuccess(Channel channel) {

        MessageBean baseBean = new MessageBean();
        baseBean.setMessageType(ErrorCode.LOGIN_SUCCESS.getCode());
        baseBean.setErrorMessage(ErrorCode.LOGIN_SUCCESS.getMsg());
        routeMessage(channel, baseBean);
    }

    /**
     * 发送错误
     *
     * @param channel
     * @param errorCode
     */
    public static void routeError(Channel channel, ErrorCode errorCode) {
        MessageBean baseBean = new MessageBean();
        baseBean.setMessageType(errorCode.getCode());
        baseBean.setErrorMessage(errorCode.getMsg());

        routeMessage(channel, baseBean);
    }

    /**
     * 发送心跳请求
     *
     * @param channel
     */
    public static void routePing(Channel channel) {

        MessageBean baseBean = new MessageBean();
        baseBean.setMessageType(ErrorCode.PING.getCode());
        baseBean.setErrorMessage(ErrorCode.PING.getMsg());
        routeMessage(channel, baseBean);
    }

    /**
     * 发送心跳响应
     *
     * @param channel
     */
    public static void routePong(Channel channel) {

        MessageBean baseBean = new MessageBean();
        baseBean.setMessageType(ErrorCode.PONG.getCode());
        baseBean.setErrorMessage(ErrorCode.PONG.getMsg());
        routeMessage(channel, baseBean);
    }

    /**
     * 直接发送消息
     *
     * @param channel 通道
     * @param msgSend 要发送的消息
     */
    public static void routeMessage(Channel channel, MessageBean msgSend) {

        try {
            channel.writeAndFlush(msgSend);
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
    }

}
