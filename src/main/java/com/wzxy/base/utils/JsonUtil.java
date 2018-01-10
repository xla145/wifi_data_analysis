package com.wzxy.base.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.util.Date;

public class JsonUtil {
    private static SerializeConfig mapping = new SerializeConfig();
    private static String dateFormat;  
    static {  
        dateFormat = "yyyy-MM-dd HH:mm:ss";  
    }  
  
    /** 
     * 默认的处理时间 
     *  
     * @param jsonText 
     * @return 
     */  
    public static String toJSON(Object jsonText) {  
        return JSON.toJSONString(jsonText,
                SerializerFeature.WriteDateUseDateFormat);
    }  
  
    /** 
     * 自定义时间格式 
     *  
     * @param jsonText 
     * @return 
     */  
    public static String toJSON(String dateFormat, String jsonText) {  
        mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
        return JSON.toJSONString(jsonText, mapping);  
    }  
}  