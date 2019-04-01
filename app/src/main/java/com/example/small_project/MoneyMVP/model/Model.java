package com.example.small_project.MoneyMVP.model;

import com.example.small_project.Api.UserApiService;
import com.example.small_project.bean.MoneyBean;
import com.example.small_project.bean.NameBean;
import com.example.small_project.bean.WordBean;
import com.example.small_project.network.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Model implements IModel {

    @Override
    public void getmoney(String url, HashMap<String, Object> hashMap, final ModelCallBack callBack) {
        UserApiService userApiService = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService.getMoney(url,hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<MoneyBean>() {
                    @Override
                    public void onNext(MoneyBean value) {
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
