package com.wzxy.sorket.handler;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wzxy.service.vo.UserVisitBean;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Created by maicius on 2017/6/18.
 */
public class UserFlowEncoder implements Encoder.Text<UserVisitBean> {
    public String encode(UserVisitBean userDiagramData) throws EncodeException {
        JSONObject json = new JSONObject();
        json.put("time", String.valueOf(userDiagramData.getTime()));
        json.put("totalFlow", userDiagramData.getTotalFlow());
        json.put("checkInFlow", userDiagramData.getCheckInFlow());
        json.put("checkInRatio", userDiagramData.getCheckInRate());
        json.put("deepVisitRatio", userDiagramData.getDeepVisitRate());
        json.put("jumpRatio", userDiagramData.getShallowVisitRate());
        return JSON.toJSON(json).toString();
    }

    public void init(EndpointConfig endpointConfig) {

    }

    public void destroy() {

    }
}
