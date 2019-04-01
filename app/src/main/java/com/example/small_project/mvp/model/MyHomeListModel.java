package com.example.small_project.mvp.model;

import android.util.Log;

import com.example.small_project.Api.UserApiService;
import com.example.small_project.bean.BannerBean;
import com.example.small_project.bean.GoodsLvOneBean;
import com.example.small_project.bean.GoodsLvTwoBean;
import com.example.small_project.bean.MyBean;
import com.example.small_project.network.RetrofitUtils;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MyHomeListModel implements IHomeListModel{

    private UserApiService userApiService;
    private UserApiService userApiService1;

    @Override
    public void getDate(final ModelCallBack callBack) {
        userApiService = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService.gethomedata()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<MyBean>() {
                    @Override
                    public void onNext(MyBean value) {
                        Log.d("", "onNext: ");
                        callBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("", "onError: ");
                        callBack.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d("", "onComplete: ");
                    }
                });
    }

    @Override
    public void getOneDate(final ModelCallBack callBack) {
        userApiService = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService.getLvOneData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<GoodsLvOneBean>() {
                    @Override
                    public void onNext(GoodsLvOneBean value) {
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
    public void getTwoDate(String categoryId, final ModelCallBack callBack) {
        userApiService = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService.getLvTwoData(categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<GoodsLvTwoBean>() {
                    @Override
                    public void onNext(GoodsLvTwoBean value) {
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
    public void getBannerDate(String url, final ModelCallBack callBack) {
        userApiService1 = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService1.getBannerData(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<BannerBean>() {
                    @Override
                    public void onNext(BannerBean value) {
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
