package com.example.small_project.login.model;


import com.example.small_project.Api.UserApiService;
import com.example.small_project.bean.LoginBean;

import com.example.small_project.network.RetrofitUtils;



import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginModel implements ILoginModel {

    @Override
    public void login(String url, String name, String pwd, final CteanView cteanView) {
        UserApiService userApiService = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService.getLoginData(url,name,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<LoginBean>() {
                    @Override
                    public void onNext(LoginBean value) {
                        cteanView.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        cteanView.onFailure();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
