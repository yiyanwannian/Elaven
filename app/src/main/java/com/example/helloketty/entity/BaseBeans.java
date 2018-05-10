package com.example.helloketty.entity;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import com.google.gson.Gson;

/**
 * Created by HelloKetty on 2018/5/4.
 */

public class BaseBeans<T> implements Serializable {
    /**
     * 解析json基础类
     */
    private static final long serialVersionUID = 1L;
    private T content;
    private String info;
    private int retcode;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public static BaseBeans fromJson(String json, Class clazz) {
        Gson gson = new Gson();
        Type objectType = type(BaseBeans.class, clazz);


        return gson.fromJson(json, objectType);
    }

    public String toJson(Class<T> clazz) {
        Gson gson = new Gson();
        Type objectType = type(BaseBeans.class, clazz);
        return gson.toJson(this, objectType);
    }

    static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }
}
