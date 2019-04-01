package com.example.small_project.mvp.presenter;

public interface IHomeLitsPresenter {
    //创建获取Model的数据的方法
    void getModelData();
    void getModelOneData();
    void getModelTwoData(String categoryId);
    void getBanner();
}
