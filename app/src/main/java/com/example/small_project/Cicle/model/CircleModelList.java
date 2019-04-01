package com.example.small_project.Cicle.model;

import com.example.small_project.Api.UserApiService;
import com.example.small_project.bean.CircleBean;
import com.example.small_project.network.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CircleModelList implements ICircleModelList {

    private UserApiService userApiService;

    @Override
    public void getCircleDate(String url, final Cteanview cteanview) {
        userApiService = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService.getCircle(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<CircleBean>() {
                    @Override
                    public void onNext(CircleBean value) {
                        cteanview.Succes(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        cteanview.Fuils();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
