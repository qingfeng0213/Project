package com.example.small_project.ShopMvp.model;

import android.util.Log;

import com.example.small_project.Api.UserApiService;
import com.example.small_project.adapter.NewAddressBean;
import com.example.small_project.bean.AddRessBean;
import com.example.small_project.bean.FIndShopBean;
import com.example.small_project.network.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Model implements IModel {
    @Override
    public void getShopDate(String url, HashMap<String, Object> hashMap, final ModelCallBack callBack) {
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

    @Override
    public void getAddress(String url, HashMap<String, Object> hashMap, final ModelCallBack callBack) {
        UserApiService userApiService = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService.getAddress(url,hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<NewAddressBean>() {
                    @Override
                    public void onNext(NewAddressBean value) {
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
    public void getAddSite(String url,HashMap<String,String> bodymap, HashMap<String, Object> hashMap, final ModelCallBack callBack) {
        UserApiService userApiService = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService.getAddSite(url,bodymap,hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<AddRessBean>() {
                    @Override
                    public void onNext(AddRessBean value) {
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
}
