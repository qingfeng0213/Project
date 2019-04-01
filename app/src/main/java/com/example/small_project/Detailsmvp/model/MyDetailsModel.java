package com.example.small_project.Detailsmvp.model;


import android.util.Log;

import com.example.small_project.Api.UserApiService;
import com.example.small_project.ShopMvp.model.IModel;
import com.example.small_project.bean.DetailsBean;
import com.example.small_project.bean.FIndShopBean;
import com.example.small_project.bean.SyncShopBean;
import com.example.small_project.network.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MyDetailsModel implements IDetailsModel {

    private UserApiService userApiService;
    private UserApiService userApiService1;


    @Override
    public void getImDate(String url, final ModelCallBack callBack) {
        userApiService = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService.getDetails(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<DetailsBean>() {
                    @Override
                    public void onNext(DetailsBean value) {
                        callBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getSyncShop(String url, String data, HashMap<String, Object> hashMap, final ModelCallBack modelCallBack) {
        userApiService1 = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService1.getSyncShop(url,data,hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SyncShopBean>() {
                    @Override
                    public void onNext(SyncShopBean value) {
                        modelCallBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getShopDate(String url, HashMap<String, Object> hashMap, final IModel.ModelCallBack callBack) {
        UserApiService userApiService = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService.getFindShop(url,hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<FIndShopBean>() {
                    @Override
                    public void onNext(FIndShopBean value) {
                        callBack.onSuccess(value);
                        Log.i("TAG", "onNext: "+value.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
