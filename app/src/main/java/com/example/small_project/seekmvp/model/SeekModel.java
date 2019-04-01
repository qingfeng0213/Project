package com.example.small_project.seekmvp.model;

import com.example.small_project.Api.UserApiService;
import com.example.small_project.bean.SouBean;
import com.example.small_project.network.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SeekModel implements IModel{

    @Override
    public void gethome(String url, String keyword, final CteanView cteanView) {
        UserApiService userApiService = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService.getSeek(url,keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SouBean>() {
                    @Override
                    public void onNext(SouBean value) {
                        cteanView.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
