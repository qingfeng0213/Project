package com.example.small_project.MyMessMVP.model;


import java.util.HashMap;

public interface IModel {
    //定义获取数据的方法
    void getmymessage(String url, HashMap<String, Object> hashMap, ModelCallBack callBack);
    interface  ModelCallBack{
        void  onSuccess(Object obj);
        void  onFail(Throwable e);
    }
}
