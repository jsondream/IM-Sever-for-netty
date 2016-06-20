package com.jsondream.netty.kdw.chat.utils;

/**
 * <p>
 * 字符串工具类
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 16/6/20
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        if (str == null)
            return true;
        return "".equals(str.trim());
    }
}
