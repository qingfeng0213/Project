package com.example.small_project.reg.presenter;


import android.widget.Toast;

import com.example.small_project.Api.ApiService;
import com.example.small_project.bean.RegBean;
import com.example.small_project.login.LoginActivity;
import com.example.small_project.reg.RegActivity;
import com.example.small_project.reg.model.ILoginModel;
import com.example.small_project.reg.model.LoginModel;

public class LoginPersenter implements ILoginPersenter
{
    RegActivity regActivity;
    LoginModel loginModel;

    public LoginPersenter(RegActivity regActivity) {
        this.regActivity = regActivity;
        loginModel = new LoginModel();
    }

    @Override
    public void loginPre(String name, String pswd) {
        loginModel.login(ApiService.REG_URL, name, pswd, new ILoginModel.CteanView() {
            @Override
            public void onSuccess(Object obj) {
                RegBean regBean = (RegBean) obj;
                regActivity.jumpActivity(regBean);
                regActivity.showMsg(regBean);
            }

            @Override
            public void onFailure() {

            }
        });
    }
}
