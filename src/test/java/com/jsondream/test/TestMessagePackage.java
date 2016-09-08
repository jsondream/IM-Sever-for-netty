package com.jsondream.test;

import com.jsondream.netty.kdw.chat.bean.ChatMessageBean;
import com.jsondream.netty.kdw.chat.bean.MessageBean;
import com.jsondream.netty.kdw.chat.protocol.ErrorCode;
import org.junit.Test;
import org.msgpack.MessagePack;
import org.msgpack.packer.Packer;
import org.msgpack.type.Value;
import org.msgpack.unpacker.Converter;
import org.msgpack.unpacker.Unpacker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 16/9/6
 */
public class TestMessagePackage {

    @Test
    public void test() throws Exception {
        ChatMessageBean message = new ChatMessageBean();
        message.setId(UUID.randomUUID().toString());
        message.setFromUser("1");
        message.setToUser("2");
        message.setMsg("nihao");
        message.setChatType("chat");
        message.setMessageType("txt");
        message.setTimeStamp(System.currentTimeMillis());
        //                                String messageString = JSON.toJSONString(message);
        //                                byte[] messageByte = ClientUtil.getMessageByte(messageString);

        MessageBean messageBean = new MessageBean(ErrorCode.CHAT_REQUEST,message);

        MessagePack messagePack = new MessagePack();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Packer packer = messagePack.createPacker(outputStream);
        packer.write(messageBean);
        packer.write(message);
        byte[] bs = outputStream.toByteArray();

        // 反序列化
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bs);
        Unpacker unpacker = messagePack.createUnpacker(inputStream);
        Value value= unpacker.readValue();
        int a= unpacker.readInt();
        String b= unpacker.readString();
//        MessageBean unMessage= unpacker.read(MessageBean.class);
//        // 测试反序列化结果
//        assertNotNull(unMessage);
//        assertTrue(unMessage.getMessageType() == messageBean.getMessageType());
        ChatMessageBean messageUn = unpacker.read(ChatMessageBean.class);
        assertNotNull(messageUn);
        assertTrue(messageUn.getMsg().equals(message.getMsg()));

        String[] str = "aa、bb、cc、dd".split("、");
        Arrays.sort(str, (s1, s2) -> s1.compareTo(s2)
        );

    }
}
