package com.example.small_project.NewSitemvp.presenter;

import java.util.HashMap;

public interface IPresenter {
    void getNewAdd(HashMap<String, Object> hashMap);
    void getPayOrder(String orderInfo,double totalPrice,int addressId ,HashMap<String, Object> hashMap);
}
