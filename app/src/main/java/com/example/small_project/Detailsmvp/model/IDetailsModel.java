package com.example.small_project.Detailsmvp.model;

import com.example.small_project.ShopMvp.model.IModel;

import java.util.HashMap;

//定义接口
public interface IDetailsModel {
    //定义获取数据的方法
    void getImDate(String url, ModelCallBack callBack);
    void getSyncShop(String url, String data, HashMap<String ,Object> hashMap, ModelCallBack modelCallBack);
    void getShopDate(String url, HashMap<String ,Object> hashMap, IModel.ModelCallBack callBack);
    interface  ModelCallBack{
        void  onSuccess(Object obj);
        void  onFail(Throwable e);
    }
}
