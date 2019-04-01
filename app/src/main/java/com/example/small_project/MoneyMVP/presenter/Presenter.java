package com.example.small_project.MoneyMVP.presenter;

import com.example.small_project.Api.ApiService;
import com.example.small_project.MoneyMVP.model.IModel;
import com.example.small_project.MoneyMVP.model.Model;
import com.example.small_project.MyActivity.MyDataActivity;
import com.example.small_project.MyActivity.MyWalletActivity;
import com.example.small_project.bean.MoneyBean;
import com.example.small_project.bean.NameBean;
import com.example.small_project.bean.WordBean;

import java.util.HashMap;

public class Presenter implements IPresenter {
    MyWalletActivity myWalletActivity;
    private final Model model;

    public Presenter(MyWalletActivity myWalletActivity) {
        this.myWalletActivity = myWalletActivity;
        model = new Model();
    }
    @Override
    public void getyu(String newname, HashMap<String, Object> hashMap) {
        model.getmoney(ApiService.MONEY_URL, hashMap, new IModel.ModelCallBack() {
            @Override
            public void onSuccess(Object obj) {
                MoneyBean moneyBean = (MoneyBean) obj;
                myWalletActivity.getPremoney(moneyBean);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }
}
