package com.example.small_project.MyMessMVP.presenter;

import com.example.small_project.Api.ApiService;
import com.example.small_project.MyActivity.MyDataActivity;
import com.example.small_project.MyMessMVP.model.IModel;
import com.example.small_project.MyMessMVP.model.Model;
import com.example.small_project.bean.MyMessageBean;
import com.example.small_project.bean.NameBean;
import com.example.small_project.bean.WordBean;
import com.example.small_project.fragment.MyFragment;

import java.util.HashMap;

public class Presenter implements IPresenter {
    MyFragment myFragment;
    private final Model model;

    public Presenter(MyFragment myFragment) {
        this.myFragment = myFragment;
        model = new Model();
    }

    @Override
    public void getmess(HashMap<String, Object> hashMap) {
        model.getmymessage(ApiService.MY_URL, hashMap, new IModel.ModelCallBack() {
            @Override
            public void onSuccess(Object obj) {
                MyMessageBean myMessageBean = (MyMessageBean) obj;
                myFragment.getPremess(myMessageBean);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }
}
