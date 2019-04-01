package com.example.small_project.AllOrderMVP.presenter;

import com.example.small_project.AllOrderMVP.model.IModel;
import com.example.small_project.AllOrderMVP.model.Model;
import com.example.small_project.Api.ApiService;
import com.example.small_project.MyActivity.MyWalletActivity;
import com.example.small_project.bean.AllOrderBean;
import com.example.small_project.bean.MoneyBean;
import com.example.small_project.fragment.MyListFragment;

import java.util.HashMap;

public class Presenter implements IPresenter {
    private final Model model;
    MyListFragment myListFragment;

    public Presenter(MyListFragment myListFragment) {
        this.myListFragment = myListFragment;
        model = new Model();
    }

    @Override
    public void getorder( int status,HashMap<String, Object> hashMap) {
        model.getOrder(ApiService.ORDER_URL, status,hashMap, new IModel.ModelCallBack() {
            @Override
            public void onSuccess(Object obj) {
                AllOrderBean allOrderBean = (AllOrderBean) obj;
                myListFragment.getPreorder(allOrderBean);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }
}
