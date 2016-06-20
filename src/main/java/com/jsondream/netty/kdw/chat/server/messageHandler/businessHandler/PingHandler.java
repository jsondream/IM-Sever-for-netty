package com.jsondream.netty.kdw.chat.server.messageHandler.businessHandler;

import com.jsondream.netty.kdw.chat.bean.BaseBodyBean;
import com.jsondream.netty.kdw.chat.server.connectionManager.AppRouterManager;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 16/6/15
 */
public class PingHandler implements AppMessageHandler<BaseBodyBean>{

    /**
     * 创建日志对象
     */
    private final Logger Log = LoggerFactory.getLogger(getClass());


    @Override
    public void process(Channel channel, BaseBodyBean msg) {
        // 对ping操作返回响应
        AppRouterManager.routePong(channel);
    }


}
