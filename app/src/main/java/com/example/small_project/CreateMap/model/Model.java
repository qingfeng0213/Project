//package com.example.small_project.CreateMap.model;
//
//import android.util.Log;
//
//import com.example.small_project.Api.UserApiService;
//import com.example.small_project.adapter.NewAddressBean;
//import com.example.small_project.bean.AddRessBean;
//import com.example.small_project.bean.CreateBean;
//import com.example.small_project.bean.FIndShopBean;
//import com.example.small_project.network.RetrofitUtils;
//
//import java.util.HashMap;
//
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.observers.DisposableObserver;
//import io.reactivex.schedulers.Schedulers;
//
//public class Model implements IModel {
//
//    @Override
//    public void getCreate(String url, HashMap<String, String> objmap, HashMap<String, String> bodymap, HashMap<String, Object> hashMap, final ModelCallBack callBack) {
//        UserApiService userApiService = RetrofitUtils.getInstance().create(UserApiService.class);
//        userApiService.getCreate(url,objmap,bodymap,hashMap)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DisposableObserver<CreateBean>() {
//                    @Override
//                    public void onNext(CreateBean value) {
//                        callBack.onSuccess(value);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        callBack.onFail(e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
//}
