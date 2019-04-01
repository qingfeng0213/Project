package com.example.small_project.Paymvp.presenter;

import com.example.small_project.Api.ApiService;
import com.example.small_project.MyActivity.PayActivity;
import com.example.small_project.MyActivity.SelectActivity;
import com.example.small_project.Paymvp.model.IModel;
import com.example.small_project.Paymvp.model.Model;
import com.example.small_project.adapter.NewAddressBean;
import com.example.small_project.bean.CreateBean;
import com.example.small_project.bean.PayBean;

import java.util.HashMap;

public class Presenter implements IPresenter {

    Model model;
    PayActivity payActivity;

    public Presenter(PayActivity payActivity) {
        this.payActivity = payActivity;
        model = new Model();
    }

    @Override
    public void getPay(String orderId, int payType, HashMap<String, Object> hashMap) {
        model.getPayData(ApiService.PAY_URL, orderId, payType, hashMap, new IModel.ModelCallBack() {
            @Override
            public void onSuccess(Object obj) {
                PayBean payBean = (PayBean) obj;
                payActivity.getPreData(payBean);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }
}
