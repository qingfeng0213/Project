package com.example.small_project.MyMessMVP.model;

import com.example.small_project.Api.UserApiService;
import com.example.small_project.bean.MyMessageBean;
import com.example.small_project.bean.NameBean;
import com.example.small_project.bean.WordBean;
import com.example.small_project.network.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Model implements IModel {
    @Override
    public void getmymessage(String url, HashMap<String, Object> hashMap, final ModelCallBack callBack) {
        UserApiService userApiService = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService.getMessage(url,hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<MyMessageBean>() {
                    @Override
                    public void onNext(MyMessageBean value) {
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
