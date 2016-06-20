package com.jsondream.netty.kdw.chat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <p>
 * java序列化工具类
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 15/12/24
 */
public class SerializeUtil {
    /**
     * 序列化一个对象
     *
     * @param object
     * @return
     * @throws
     */
    public static byte[] serialize(Object object) {
        byte[] bytes = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(object);
            bytes = baos.toByteArray();
            //            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 反序列化一个对象
     *
     * @param bytes
     * @return
     * @throws
     */
    public static Object unSerialize(byte[] bytes) {

        Object object = null;
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais)) {
            object = ois.readObject();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}
