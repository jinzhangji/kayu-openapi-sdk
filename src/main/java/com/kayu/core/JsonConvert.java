package com.kayu.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kayu.annotation.ResponseDataWrapper;
import com.kayu.result.OpenApiBaseResult;
import com.kayu.utils.BeanUtils;
import com.kayu.utils.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Jin.Z.J  2021/7/5
 */
public class JsonConvert extends OpenApiBaseResult implements Convert{

    private static final Map<String,Class<?>> classMapCache = new ConcurrentHashMap<>();

    private JsonConvert(){
    }
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public static Convert parse(String json){
        return JSONObject.parseObject(json,JsonConvert.class);
    }

    @Override
    public <T extends OpenApiBaseResult> T convert(Class<T> clazz) {
        T result;
        try {
            result = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        if(!isEmpty()){
            ResponseDataWrapper annotation = clazz.getAnnotation(ResponseDataWrapper.class);
            if (annotation != null) {
                String wrapperFieldName = annotation.value();
                Field wrapperField;
                try {
                    if(StringUtils.isEmpty(wrapperFieldName)){
                        Field[] declaredFields = clazz.getDeclaredFields();
                        wrapperField = (declaredFields == null || declaredFields.length == 0) ? null : declaredFields[0];
                    }else{
                        wrapperField = clazz.getDeclaredField(wrapperFieldName);
                    }
                    if(wrapperField == null){
                        throw new RuntimeException("not such wrapper field");
                    }
                    Object val = null;
                    if (wrapperField.getType() == Collections.class && this.data.startsWith("[") && this.data.endsWith("]")) {
                        Type genericType = wrapperField.getGenericType();
                        if (genericType instanceof ParameterizedType) {
                            ParameterizedType parameterizedType = (ParameterizedType) genericType;
                            String className = parameterizedType.getActualTypeArguments()[0].getTypeName();
                            Class<?> dataClass = classMapCache.putIfAbsent(className,Class.forName(className));
                            val = JSON.parseArray(this.data,dataClass);
                        }
                    }
                    if (val == null) {
                        val = JSON.parseObject(this.data, wrapperField.getType());
                    }
                    if (!wrapperField.isAccessible()) {
                        wrapperField.setAccessible(true);
                    }
                    wrapperField.set(result, val);
                } catch (NoSuchFieldException | ClassNotFoundException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }else{
                JSONObject jsonObject = JSONObject.parseObject(this.data);
                BeanUtils.foreachDesc(clazz,d -> {
                    String name = d.getName();
                    Object object = jsonObject.getObject(name, d.getField().getType());
                    try {
                        d.setter().invoke(result,object);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        System.err.println(e.getMessage());
                    }
                });
            }
        }
        result.setStatus(this.getStatus());
        result.setMessage(this.getMessage());
        return result;
    }

    private boolean isEmpty(){
        return StringUtils.isEmpty(this.data) || this.data.equals("[]") || this.data.equals("{}") || this.data.equals("null");
    }

}
