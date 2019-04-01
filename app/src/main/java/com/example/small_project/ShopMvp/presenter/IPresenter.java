package com.example.small_project.ShopMvp.presenter;

import java.util.HashMap;

public interface IPresenter {
    void getShopData(HashMap<String ,Object> hashMap);
    void getNewAdd(HashMap<String ,Object> hashMap);
    void getSiteAdd(HashMap<String,String> bodymap,HashMap<String ,Object> hashMap);
}
