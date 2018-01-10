package com.wzxy.controller;

import com.alibaba.fastjson.JSON;
import com.wzxy.sorket.SocketMessageHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestSocketController {

    @Autowired
    private SocketMessageHandle testSorketHandle;

    @RequestMapping("/websocket/send")
    @ResponseBody
    public String send(HttpServletRequest request) {
        String username = request.getParameter("username");
        testSorketHandle.sendMessageToUser(username, new TextMessage("你好，测试！！！！"));
        return "";
    }


    @RequestMapping("/websocket/sendAll")
    @ResponseBody
    public String sendAll(HttpServletRequest request) {
        String[] data = request.getParameterValues("data");
        System.out.println(JSON.toJSON(data));
        testSorketHandle.sendMessageToUsers(data);
        return "";
    }
}
