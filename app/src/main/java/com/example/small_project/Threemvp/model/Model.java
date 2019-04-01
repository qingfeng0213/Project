package com.example.small_project.Threemvp.model;

import com.example.small_project.Api.UserApiService;
import com.example.small_project.bean.GoodsLvThreeBean;
import com.example.small_project.mvp.model.IHomeListModel;
import com.example.small_project.network.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Model implements IModel {
    @Override
    public void getTwoDate(String categoryId, int page, final IHomeListModel.ModelCallBack callBack) {
        UserApiService userApiService = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService.getLvThreeData(categoryId,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<GoodsLvThreeBean>() {
                    @Override
                    public void onNext(GoodsLvThreeBean value) {
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
