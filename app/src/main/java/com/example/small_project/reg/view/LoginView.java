package com.example.small_project.reg.view;

import com.example.small_project.bean.RegBean;

public interface LoginView {
    //Toast形式提示
    public void showMsg(RegBean regBean);
    //页面跳转
    public void jumpActivity(RegBean regbean);
}
