package com.example.small_project.mvp.model;

//定义接口
public interface IHomeListModel {
    //定义获取数据的方法
    void getDate(ModelCallBack callBack);
    void getOneDate(ModelCallBack callBack);
    void getTwoDate(String categoryId,ModelCallBack callBack);
    void getBannerDate(String url,ModelCallBack callBack);

    interface  ModelCallBack{
        void  onSuccess(Object obj);
        void  onFail(Throwable e);
    }
}
