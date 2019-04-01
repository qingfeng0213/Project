package com.example.small_project.MoneyMVP.model;


import java.util.HashMap;

public interface IModel {
    //定义获取数据的方法
    void getmoney(String url,HashMap<String, Object> hashMap, ModelCallBack callBack);
    interface  ModelCallBack{
        void  onSuccess(Object obj);
        void  onFail(Throwable e);
    }
}
