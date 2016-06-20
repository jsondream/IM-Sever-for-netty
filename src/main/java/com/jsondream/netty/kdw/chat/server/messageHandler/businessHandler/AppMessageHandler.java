package com.jsondream.netty.kdw.chat.server.messageHandler.businessHandler;

import com.jsondream.netty.kdw.chat.bean.BaseBodyBean;
import io.netty.channel.Channel;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 16/6/15
 */
public interface AppMessageHandler<T extends BaseBodyBean> {
    public void process(Channel channel, T msg);
}
