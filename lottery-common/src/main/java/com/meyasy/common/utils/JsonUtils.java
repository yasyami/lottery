package com.meyasy.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;


public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper objectMapper;

    static {
        objectMapper=new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public static ObjectMapper getObjectMapper(){
        return objectMapper;
    }

    /**
     * JSON串转换为Java泛型对象，可以是各种类型，此方法最为强大。用法看测试用例。
     * @param jsonSting JSON字符串
     * @param reference  TypeReference,例如: new TypeReference< List<FamousUser> >(){}
     * @param <T>
     * @return
     */
    public static <T> T json2GenericObjcet(String jsonSting, TypeReference<T> reference){
        if(jsonSting==null||"".equals(jsonSting)){
            return null;
        }else {
            try {
                objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
                return (T)objectMapper.readValue(jsonSting,reference);
            } catch (IOException e) {
                logger.warn("json error:"+e.getMessage());
            }
        }
        return null;
    }

    /***
     * 将对象转化为json字符串
     * @param o java 对象
     * @return
     */
    public static String toJson(Object o){
        String jsonString="";
        try {
            jsonString=objectMapper.writeValueAsString(o);
        } catch (IOException e) {
            logger.warn("json error:"+e.getMessage());
        }
        return jsonString;
    }

    /**
     * json字符串转java对象
     * @param jsonString
     * @param clazz
     * @return
     */
    public static Object json2Object(String jsonString , Class<?> clazz){
        if(StringUtils.isEmpty(jsonString)){
            return "";
        }else {
            try {
                return objectMapper.readValue(jsonString,clazz);
            } catch (IOException e) {
                logger.warn("json error:"+e.getMessage());
            }
        }
        return "";
    }

    /**
     * 根据json串返回节点
     * @param jsonString
     * @param nodeName
     * @return
     */
    public static JsonNode getNode(String jsonString ,String nodeName){
        JsonNode node = null;
        try {
            node = objectMapper.readTree(jsonString);
           return node.get(nodeName);
        } catch (JsonProcessingException e) {
            logger.warn("json error:" + e.getMessage());
        }catch (IOException e) {
            logger.warn("json error:" + e.getMessage());
        }
        return node;
    }

    /**
     * JsonNode转换为Java泛型对象，可以是各种类型，此方法最为强大。用法看测试用例。
     * @param node JsonNode
     * @param reference  TypeReference,例如: new TypeReference< List<FamousUser> >(){}
     * @param <T>
     * @return
     */
    public static <T> T jsonNode2GnericObjcet(JsonNode node,TypeReference reference){
        if(node==null||"".equals(node)){
            return null;
        }else {
            try {
                return (T)objectMapper.readValue(node,reference);
            } catch (IOException e) {
                logger.warn("json error:"+e.getMessage());
            }
        }
        return null;
    }


    public static  String getValueFromJsonString(String jsonString, String paramName){
        if(StringUtils.isNotBlank(jsonString)&&StringUtils.isNotBlank(paramName)){
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            try{
                if(jsonObject!=null){
                    return jsonObject.getString(paramName);
                }
            }catch (Exception e){
                logger.error("get param["+paramName+"] value from json["+jsonString+"] error:"+e);
            }
        }
        return null;
    }



}
