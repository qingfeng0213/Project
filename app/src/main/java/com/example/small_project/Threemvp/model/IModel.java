package com.example.small_project.Threemvp.model;

import com.example.small_project.mvp.model.IHomeListModel;

public interface IModel {
    void getTwoDate(String categoryId,int page,IHomeListModel.ModelCallBack callBack);

    interface  ModelCallBack{
        void  onSuccess(Object obj);
        void  onFail(Throwable e);
    }
}
