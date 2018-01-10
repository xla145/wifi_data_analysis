package com.wzxy.sorket;

import com.alibaba.fastjson.JSONObject;
import com.wzxy.base.constant.BaseConstant;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */

public class SocketMessageHandle extends TextWebSocketHandler {

    private static final Map<Integer,WebSocketSession> users ;//这个会出现性能问题，最好用Map来存储，key用userid

    static {
        users = new HashMap<Integer,WebSocketSession>();
    }

    /**
     * 连接成功后
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Integer userId = (Integer) session.getAttributes().get(BaseConstant.SYS_UID);
        users.put(userId,session);
    }

    /**
     * 连接关闭时
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Integer userId = (Integer) session.getAttributes().get(BaseConstant.SYS_UID);
        users.remove(userId);
    }

    /**
     * 接收信息
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }

    /**
     * 出错时
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        Integer userId = (Integer) session.getAttributes().get(BaseConstant.SYS_UID);
        if(session.isOpen()){session.close();}
        users.remove(userId);
    }

    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        for (Map.Entry<Integer,WebSocketSession> user : users.entrySet()) {
            if (user.getValue().getAttributes().get(BaseConstant.SYS_UID).equals(userName)) {
                try {
                    if (user.getValue().isOpen()) {
                        user.getValue().sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(String[] message) {
        for (Map.Entry<Integer,WebSocketSession> user : users.entrySet()) {
            try {
                if (user.getValue().isOpen()) {
                    user.getValue().sendMessage(new TextMessage(getChartJson(message)));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(String message) {
        for (Map.Entry<Integer,WebSocketSession> user : users.entrySet()) {
            try {
                if (user.getValue().isOpen()) {
                    user.getValue().sendMessage(new TextMessage(message));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public String getChartJson(String[] message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",message);
        return jsonObject.toJSONString();
    }
}
