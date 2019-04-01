package com.example.small_project.Detailsmvp.presenter;


import com.example.small_project.Api.ApiService;
import com.example.small_project.DetailsActivity;
import com.example.small_project.Detailsmvp.model.IDetailsModel;
import com.example.small_project.Detailsmvp.model.MyDetailsModel;
import com.example.small_project.ShopMvp.model.IModel;
import com.example.small_project.bean.DetailsBean;
import com.example.small_project.bean.FIndShopBean;
import com.example.small_project.bean.SyncShopBean;

import java.util.HashMap;

//继承Presenter的接口,继承Model类的内部接口
public class MyHomeListPresenter implements IDetailsPresenter {

    MyDetailsModel myHomeListModel;
    DetailsActivity detailsActivity;

    public MyHomeListPresenter(DetailsActivity detailsActivity) {
        this.detailsActivity = detailsActivity;
        myHomeListModel = new MyDetailsModel();
    }

    @Override
    public void getIData(String id) {
        myHomeListModel.getImDate(ApiService.DETAILS_URL+id, new IDetailsModel.ModelCallBack() {
            @Override
            public void onSuccess(Object obj) {
                DetailsBean detailsBean = (DetailsBean) obj;
                detailsActivity.getViewData(detailsBean);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }

    @Override
    public void getSync(String data, HashMap<String, Object> hashMap) {
        myHomeListModel.getSyncShop(ApiService.SYNCSHOP_URL, data, hashMap, new IDetailsModel.ModelCallBack() {
            @Override
            public void onSuccess(Object obj) {
                SyncShopBean syncShopBean = (SyncShopBean) obj;
                detailsActivity.getPreSync(syncShopBean);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }

    @Override
    public void getShopData(HashMap<String, Object> hashMap) {
        myHomeListModel.getShopDate(ApiService.SELECTCAT_URL, hashMap, new IModel.ModelCallBack() {
            @Override
            public void onSuccess(Object obj) {
                FIndShopBean fIndShopBean = (FIndShopBean) obj;
                detailsActivity.getPreShop(fIndShopBean);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }
}
