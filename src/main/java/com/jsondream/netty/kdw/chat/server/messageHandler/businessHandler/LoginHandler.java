package com.jsondream.netty.kdw.chat.server.messageHandler.businessHandler;

import com.jsondream.netty.kdw.chat.bean.LoginBean;
import com.jsondream.netty.kdw.chat.mock.ChatRoomMockServiceImpl;
import com.jsondream.netty.kdw.chat.protocol.ErrorCode;
import com.jsondream.netty.kdw.chat.server.AppAttrKeys;
import com.jsondream.netty.kdw.chat.server.connectionManager.AppRouterManager;
import com.jsondream.netty.kdw.chat.server.connectionManager.ChannelManager;
import com.jsondream.netty.kdw.chat.utils.StringUtils;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 16/6/15
 */
public class LoginHandler implements AppMessageHandler<LoginBean> {

    /**
     * 创建日志对象
     */
    private final Logger Log = LoggerFactory.getLogger(getClass());

    @Override
    public void process(Channel channel, LoginBean loginInfo) {

        try {
            String userId = loginInfo.getUserId();
            String authKey = loginInfo.getAuthKey();
            String platform = loginInfo.getPlatform().toLowerCase();
            String appVersion = loginInfo.getAppVersion();

            //获取客户端Header中提交的authKey

            // TODO:token验证
            boolean canConnection = authKey.equals("authKey");

            // authKey验证失效的处理
            if ((StringUtils.isEmpty(authKey) || !canConnection)) {
                AppRouterManager.routeError(channel, ErrorCode.ERROR_AUTHED);
                channel.close();
                return;
            }

            // 判断是否有旧连接
            Channel oldChannel = ChannelManager.getConn(userId);
            if (oldChannel != null) {
                // 发送错误码，关闭旧连接
                ChannelManager.removeConn(userId);
                oldChannel.attr(AppAttrKeys.USER_ID).set("");
                AppRouterManager.routeError(oldChannel, ErrorCode.HAS_LOGIN);
                oldChannel.close();
            }

            // 添加连接
            ChannelManager.addConn(userId, channel);
            channel.attr(AppAttrKeys.USER_ID).set(userId);
            channel.attr(AppAttrKeys.AUTH_KEY).set(authKey);
            channel.attr(AppAttrKeys.APP_VERSION).set(appVersion);
            channel.attr(AppAttrKeys.PLATFORM).set(platform);

            // 增加聊天室功能
            joinGroupChannel(userId,channel);
            // 返回登录成功
            AppRouterManager.routeLoginSuccess(channel);

        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }

    }

    public void joinGroupChannel(String userId,Channel channel){
        ChatRoomMockServiceImpl chatRoomMockService = new ChatRoomMockServiceImpl();
        List<String> roomList = chatRoomMockService.getRoomList(userId);
        roomList.stream().forEach(roomId -> ChannelManager.addConnGroup(roomId, channel));

    }

}
