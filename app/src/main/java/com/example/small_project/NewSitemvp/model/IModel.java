package com.example.small_project.NewSitemvp.model;


import java.util.HashMap;


public interface IModel {
    //定义获取数据的方法
    void getAddress(String url, HashMap<String, Object> hashMap, ModelCallBack callBack);
    void getOrder(String url,String orderInfo,double totalPrice,int addressId ,HashMap<String, Object> hashMap, ModelCallBack callBack);
    interface  ModelCallBack{
        void  onSuccess(Object obj);
        void  onFail(Throwable e);
    }
}
