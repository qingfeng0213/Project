package com.example.small_project.login.model;

public interface ILoginModel {
     void login(String url, String name, String pwd,CteanView cteanView);

    interface CteanView {
        void onSuccess(Object obj);
        void onFailure();
    }
}
