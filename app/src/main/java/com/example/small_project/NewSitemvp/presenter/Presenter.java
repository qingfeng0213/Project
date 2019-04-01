package com.example.small_project.NewSitemvp.presenter;

import com.example.small_project.AddressActivity;
import com.example.small_project.Api.ApiService;
import com.example.small_project.MyActivity.MySiteActivity;
import com.example.small_project.MyActivity.SelectActivity;
import com.example.small_project.NewSitemvp.model.IModel;
import com.example.small_project.NewSitemvp.model.Model;
import com.example.small_project.adapter.NewAddressBean;
import com.example.small_project.bean.AddRessBean;
import com.example.small_project.bean.CreateBean;
import com.example.small_project.bean.FIndShopBean;
import com.example.small_project.fragment.ShopFragment;

import java.util.HashMap;

public class Presenter implements IPresenter {

    Model model;
    SelectActivity selectActivity;

    public Presenter(SelectActivity selectActivity) {
        this.selectActivity = selectActivity;
        model = new Model();
    }


    @Override
    public void getNewAdd(HashMap<String, Object> hashMap) {
        model.getAddress(ApiService.ADDRESS_URL, hashMap, new IModel.ModelCallBack() {
            @Override
            public void onSuccess(Object obj) {
                NewAddressBean newAddressBean = (NewAddressBean) obj;
                selectActivity.getPreRess(newAddressBean);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }

    @Override
    public void getPayOrder(String orderInfo, double totalPrice, int addressId, HashMap<String, Object> hashMap) {
        model.getOrder(ApiService.CREATE_URL, orderInfo, totalPrice, addressId, hashMap, new IModel.ModelCallBack() {
            @Override
            public void onSuccess(Object obj) {
                CreateBean createBean = (CreateBean) obj;
                selectActivity.getPrePay(createBean);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }
}
