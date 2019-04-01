package com.example.small_project.GaiMVP.presenter;

import com.example.small_project.Api.ApiService;
import com.example.small_project.GaiMVP.model.IModel;
import com.example.small_project.GaiMVP.model.Model;
import com.example.small_project.MyActivity.MyDataActivity;
import com.example.small_project.bean.NameBean;
import com.example.small_project.bean.WordBean;

import java.util.HashMap;

public class Presenter implements IPresenter {

    MyDataActivity myDataActivity;
    private final Model model;

    public Presenter(MyDataActivity myDataActivity) {
        this.myDataActivity = myDataActivity;
        model = new Model();
    }

    @Override
    public void getname(String newname, HashMap<String, Object> hashMap) {
        model.getnewname(ApiService.NAME_URL, newname, hashMap, new IModel.ModelCallBack() {
            @Override
            public void onSuccess(Object obj) {
                NameBean nameBean = (NameBean) obj;
                myDataActivity.getPrename(nameBean);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }

    @Override
    public void getword(String oldpwd, String newpwd, HashMap<String, Object> hashMap) {
        model.getpassword(ApiService.WORD_URL, oldpwd, newpwd, hashMap, new IModel.ModelCallBack() {
            @Override
            public void onSuccess(Object obj) {
                WordBean wordBean = (WordBean) obj;
                myDataActivity.getnewword(wordBean);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }
}
