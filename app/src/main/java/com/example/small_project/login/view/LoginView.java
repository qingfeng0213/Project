package com.example.small_project.login.view;

import com.example.small_project.bean.LoginBean;

public interface LoginView {
    //Toast形式提示
    void showMsg(LoginBean loginBean);
    //页面跳转
    void jumpActivity(LoginBean loginBean);
}
