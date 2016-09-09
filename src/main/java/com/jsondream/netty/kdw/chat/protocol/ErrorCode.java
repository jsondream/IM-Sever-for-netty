package com.jsondream.netty.kdw.chat.protocol;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 16/6/16
 */
public enum ErrorCode {

    OK(0, "OK"), fail(-1, "FAIL"),

    SYSTEM_ERROR(1, "系统错误"), SYSTEM_BUSY(2, "系统繁忙"), PARAM_ERROR(3, "参数错误"), CFG_ERROR(4, "配置文件错误"),
    PROTOCOL_ERROR(5,"协议错误"),
    /**
     * 账号相关 1001~2000
     */
    NOT_AUTHED(1001, "未登录"), ERROR_AUTHED(1002, "账号或密码错误"), EXIST_AUTHED(1003,
        "该账户已经被占用，换个试试"), ERROR_VALIDCODE(1004, "验证码错误"), LIMIT_AUTHED(1005,
        "用户已被锁定"), NOT_FIND_USER(1006, "用户不存在"), ERROR_NICK_NAME(1007,
        "内容包含敏感字符"), VERIFY_EXPIRE_CODE(1008, "验证码已过期，请重新发送"), ERROR_VERIFY_CODE(1009, "验证码错误"),
    HAS_LOGIN(1010, "您已经在另外一台设备上登录"), SYSTEM_USER_NOT_EXIST(1011, "用户不存在"), USER_OPERATE_ERROR(1012,
        "操作用户不匹配"),

    /**
     * 好友相关 2001~3000
     */
    NOT_FIND_FRIEND(2001, "未找到该好友"), NOT_FIND_GROUP(2002, "未找到该群组"), ALREADY_INVITE_FRIEND(2003,
        "已邀请该好友"), ALREADY_APPLY_FRIEND(2004, "已申请添加该好友"), NOT_FIND_INVITE(2005,
        "未找到该申请"), FRIEND_ALREADY_EXIST(2006, "好友已存在"), ALREADY_APPLY_GROUP(2007,
        "已申请加入该群"), NOT_FIND_APPLY_GROUP(2008, "未找到入群申请"), NOT_FIND_INVITE_GROUP(2009,
        "未找到入群邀请"), NO_PRIVIJE_APPLY_GROUP(2010, "无权执行该操作"), NOT_FIND_APPLY_FRIEND(2011,
        "未找到添加好友申请"), GROUP_LEVEL_NOT_FIND(2011, "群等级不存在"), HAD_IN_GROUP(2012,
        "已是该群成员"), HAD_IS_FRIEND(2013, "已是好友"), NOT_APPLAY_FRIEND(2014, "不能添加自己为好友"), HAD_RESUSE(
        2015, "已拒绝"), HAD_OUT_GROUP_LIMIT(2016, "该群已满"),

    /**
     * 个人信息相关 3001~4000
     */
    NOT_FOLLOW_SELF(3001, "不能关注自己"),

    /**
     * 聊天相关 4001~5000
     */
    CAN_NOT_CHAT_WITH_YOUR_SELF(4001, "不能给您自己发送消息"), CHAT_TYPE_ERROR(4002, "聊天类型错误"),

    /**
     * IM信号量 5001~6000
     */
    LOGIN_REQUEST(5001, "登录请求"), LOGIN_SUCCESS(5002, "登录成功"),
    CHAT_REQUEST(5003, "聊天请求"), SEND_MESSAGE(5004, "发送消息"), RECEIVE_MESSAGE(5005, "接收消息"),
    PING(5006, "ping请求"), PONG(5007, "pong响应"),;

    private int code;
    private String msg;

    /**
     * 构造函数
     *
     * @param code 错误号
     * @param msg  错误信息
     */
    private ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * <p>Title: getCode<／p>
     * <p>Description: 返回错误号 <／p>
     *
     * @return code
     */
    public int getCode() {
        return code;
    }

    /**
     * <p>Title: getMsg<／p>
     * <p>Description: 返回错误信息<／p>
     *
     * @return msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 通过id来获得ErrorCode
     *
     * @param id
     * @return
     */
    public static ErrorCode getErrorCodeById(int id) {
        ErrorCode[] array = ErrorCode.values();
        for (ErrorCode errorCode : array) {
            if (errorCode.getCode() == id)
                return errorCode;
        }
        return null;
    }
}
