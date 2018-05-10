package com.example.helloketty.util;

import com.example.helloketty.entity.BaseBeans;

/**
 * Created by HelloKetty on 2018/5/4.
 */

public class FromJsonUtils {
    private Class cls;
    private String json;

    public FromJsonUtils(Class cls,String json){
        this.cls=cls;
        this.json=json;
    }
    public BaseBeans fromJson(){
        BaseBeans result=null;
        try {
            result=BaseBeans.fromJson(json,cls);
        } catch (Exception e) {

            result=null;
            e.printStackTrace();
        }
        return result;
    }
}
