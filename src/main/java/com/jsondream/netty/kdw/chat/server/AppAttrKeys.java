package com.jsondream.netty.kdw.chat.server;

import io.netty.util.AttributeKey;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 16/6/16
 */
public interface AppAttrKeys {

    AttributeKey<String> USER_ID = AttributeKey.valueOf("userId");
    AttributeKey<String> AUTH_KEY = AttributeKey.valueOf("authKey");
    AttributeKey<String> PLATFORM = AttributeKey.valueOf("platform");
    AttributeKey<String> APP_VERSION = AttributeKey.valueOf("appVersion");

}

