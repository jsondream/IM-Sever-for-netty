package com.jsondream.netty.chat.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.jsondream.util.Utils;

public class ClientUtil {

    StringBuffer strContext = new StringBuffer();
    Socket sConnect = null;
    DataOutputStream dos = null;
    DataInputStream dis = null;
    boolean bConnected = false;
    String strIp = null;
    int port = 0;

    /**
     * 拼装要发送的数据
     *
     * @param s
     * @return
     */
    public static byte[] getMessageByte(String s) {
        if (s == null || s.length() == 0)
            throw new NullPointerException();
        try {
            byte[] bytes = s.getBytes("UTF-8");
            byte[] sizeBytes = Utils.intToByteArray(bytes.length);

            byte[] messageByte = Utils.byteMerger(sizeBytes, bytes);
            return messageByte;
        } catch (IOException e) {
            return null;
        }
    }
}
