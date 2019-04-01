package com.example.small_project.Paymvp.presenter;

import com.example.small_project.Paymvp.model.IModel;

import java.util.HashMap;

public interface IPresenter {
    void getPay(String orderId, int payType, HashMap<String, Object> hashMa);
}
