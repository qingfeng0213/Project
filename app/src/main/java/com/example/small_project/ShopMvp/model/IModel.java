package com.example.small_project.ShopMvp.model;


import java.util.HashMap;

public interface IModel {
    //定义获取数据的方法
    void getShopDate(String url, HashMap<String ,Object> hashMap, ModelCallBack callBack);
    void getAddress(String url, HashMap<String ,Object> hashMap, ModelCallBack callBack);
    void getAddSite(String url,HashMap<String,String> bodymap, HashMap<String ,Object> hashMap, ModelCallBack callBack);
    interface  ModelCallBack{
        void  onSuccess(Object obj);
        void  onFail(Throwable e);
    }
}
