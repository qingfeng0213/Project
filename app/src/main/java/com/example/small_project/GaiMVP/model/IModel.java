package com.example.small_project.GaiMVP.model;


import java.util.HashMap;

public interface IModel {
    //定义获取数据的方法
    void getnewname(String url, String newname,HashMap<String, Object> hashMap, ModelCallBack callBack);
    void getpassword(String url, String oldpwd,String newpwd,HashMap<String, Object> hashMap, ModelCallBack callBack);
    interface  ModelCallBack{
        void  onSuccess(Object obj);
        void  onFail(Throwable e);
    }
}
