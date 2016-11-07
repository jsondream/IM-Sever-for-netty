package com.jsondream.test;

import com.jsondream.netty.kdw.chat.bean.ChatMessageBean;
import com.jsondream.netty.kdw.chat.bean.MessageBean;
import com.jsondream.netty.kdw.chat.protocol.ErrorCode;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.sctp.nio.NioSctpChannel;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.junit.BeforeClass;
import org.junit.Test;
import org.msgpack.MessagePack;
import org.msgpack.packer.Packer;
import org.msgpack.type.Value;
import org.msgpack.unpacker.Unpacker;
import sun.misc.Unsafe;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 16/9/6
 */
public class TestCas {
    private static Map<String, List<String>> groupConnMap = new ConcurrentHashMap<>();

    //Unsafe unsafe = Unsafe.getUnsafe();

    private static Unsafe unsafe;
    private static long valueOffset;

    @BeforeClass
    public static void before() {
        try {
            //通过反射获取rt.jar下的Unsafe类
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);

            valueOffset = unsafe.objectFieldOffset(TestCas.class.getDeclaredField("groupConnMap"));
        } catch (Exception e) {
            System.out.println("Get Unsafe instance occur error" + e);
        }
    }

    @Test
    public void test() throws Exception {

        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(0);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.parallelStream().forEach(v -> {
            List<String> list = groupConnMap.get("0");
            if (list == null) {
                list = new ArrayList<>();
                for (int i = 0; i <= v; i++) {
                    list.add(i + "");
                }
                System.out.println("当前的v值是[" + v + "]" + groupConnMap.get("0"));
                groupConnMap.put("0", list);
            }
        });

        System.out.println(groupConnMap.get("0"));

        //unsafe.compareAndSwapObject()

    }

    @Test
    public void testPutIfAbsent() {

        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(0);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.parallelStream().forEach(v -> {
            List<String> list = groupConnMap.get("0");
            if (list == null) {
                List<String> list1 = new ArrayList<>();
                for (int i = 0; i <= v; i++) {
                    list1.add(i + "");
                }
                System.out.println("当前的v值是[" + v + "]" + groupConnMap.get("0"));
                groupConnMap.putIfAbsent("0", list1);
            }
        });
        System.out.println(groupConnMap.get("0"));

    }


    @Test
    public void testPutIfAbsentResult() {

        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(0);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.parallelStream().forEach(v -> {
            List<String> list = groupConnMap.get("0");
            if (list == null) {
                List<String> list1 = new ArrayList<>();
//                for (int i = 0; i <= v; i++) {
//                    list1.add(i + "");
//                }
                System.out.println("当前的v值是[" + v + "]" + groupConnMap.get("0"));
                groupConnMap.putIfAbsent("0", list1);
            }

            List<String> list2 = groupConnMap.get("0");
            list2.add(v+"");
        });
        System.out.println(groupConnMap.get("0"));

    }
}
