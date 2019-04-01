package com.example.small_project.ShopMvp.presenter;

import android.util.Log;

import com.example.small_project.AddressActivity;
import com.example.small_project.Api.ApiService;
import com.example.small_project.MyActivity.MySiteActivity;
import com.example.small_project.MyActivity.SelectActivity;
import com.example.small_project.ShopMvp.model.IModel;
import com.example.small_project.ShopMvp.model.Model;
import com.example.small_project.ShopMvp.view.IView;
import com.example.small_project.adapter.NewAddressBean;
import com.example.small_project.bean.AddRessBean;
import com.example.small_project.bean.FIndShopBean;
import com.example.small_project.fragment.ShopFragment;

import java.util.HashMap;

public class Presenter implements IPresenter{
    ShopFragment shopFragment;
    Model model;
    MySiteActivity mySiteActivity;
    AddressActivity addressActivity;

    public Presenter(AddressActivity addressActivity) {
        this.addressActivity = addressActivity;
        model = new Model();
    }

    public Presenter(MySiteActivity mySiteActivity) {
        this.mySiteActivity = mySiteActivity;
        model = new Model();
    }

    public Presenter(ShopFragment shopFragment) {
        this.shopFragment = shopFragment;
        model = new Model();
    }

    @Override
    public void getShopData(HashMap<String, Object> hashMap) {
        model.getShopDate(ApiService.SELECTCAT_URL, hashMap, new IModel.ModelCallBack() {
            @Override
            public void onSuccess(Object obj) {
                FIndShopBean fIndShopBean = (FIndShopBean) obj;
                shopFragment.getPreShop(fIndShopBean);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }

    @Override
    public void getNewAdd(HashMap<String, Object> hashMap) {
        model.getAddress(ApiService.ADDRESS_URL, hashMap, new IModel.ModelCallBack() {
            @Override
            public void onSuccess(Object obj) {
                NewAddressBean newAddressBean = (NewAddressBean) obj;
                mySiteActivity.getPreRess(newAddressBean);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }

    @Override
    public void getSiteAdd(HashMap<String,String> bodymap, HashMap<String, Object> hashMap) {
        model.getAddSite(ApiService.ADDSITE_URL, bodymap, hashMap, new IModel.ModelCallBack() {
            @Override
            public void onSuccess(Object obj) {
                AddRessBean addRessBean = (AddRessBean) obj;
                addressActivity.getPreAdd(addRessBean);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }
}
