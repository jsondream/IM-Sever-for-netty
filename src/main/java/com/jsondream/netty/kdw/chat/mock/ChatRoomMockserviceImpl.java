package com.jsondream.netty.kdw.chat.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wangguangdong
 * @version 1.0
 * @Date 16/9/10
 */
public class ChatRoomMockServiceImpl implements  MockService {

    // userID - roomId
    private static Map<String,List<String>> roomRelation = new ConcurrentHashMap<>();

    static {
        //
        roomRelation.put("1",Arrays.asList(new String[]{"1001","1002","1003"}));
        roomRelation.put("2",Arrays.asList(new String[]{"1001"}));
        roomRelation.put("3",Arrays.asList(new String[]{"1001"}));
        roomRelation.put("4",Arrays.asList(new String[]{"1001"}));
        roomRelation.put("5",Arrays.asList(new String[]{"1001"}));
        roomRelation.put("6",Arrays.asList(new String[]{"1001"}));
        roomRelation.put("7",Arrays.asList(new String[]{"1001"}));
        roomRelation.put("8",Arrays.asList(new String[]{"1002"}));
        roomRelation.put("9",Arrays.asList(new String[]{"1003"}));
    }
    public List<String> getRoomList(String userId){
        return roomRelation.get(userId);
    }


}
