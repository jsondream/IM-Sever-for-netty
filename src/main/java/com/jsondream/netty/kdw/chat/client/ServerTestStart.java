package com.jsondream.netty.kdw.chat.client;

import com.jsondream.netty.kdw.chat.server.AppChatServer;

import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 16/9/5
 */
public class ServerTestStart {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8012;
        }
        new AppChatServer(200).Start(port);
    }
}
