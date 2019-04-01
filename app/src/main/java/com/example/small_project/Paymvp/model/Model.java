package com.example.small_project.Paymvp.model;

import com.example.small_project.Api.UserApiService;
import com.example.small_project.adapter.NewAddressBean;
import com.example.small_project.bean.CreateBean;
import com.example.small_project.bean.PayBean;
import com.example.small_project.network.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Model implements IModel {

    @Override
    public void getPayData(String url, String orderId, int payType, HashMap<String, Object> hashMap, final ModelCallBack callBack) {
        UserApiService userApiService = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService.getPayData(url,orderId,payType,hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<PayBean>() {
                    @Override
                    public void onNext(PayBean value) {
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
