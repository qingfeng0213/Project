package com.example.small_project.Detailsmvp.presenter;

import java.util.HashMap;

public interface IDetailsPresenter {
    //创建获取Model的数据的方法
    void getIData(String id);
    void getSync(String data, HashMap<String ,Object > hashMap);
    void getShopData(HashMap<String ,Object> hashMap);
}
