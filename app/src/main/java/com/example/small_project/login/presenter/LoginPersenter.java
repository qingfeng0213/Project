package com.example.small_project.login.presenter;


import android.widget.Toast;

import com.example.small_project.Api.ApiService;
import com.example.small_project.bean.LoginBean;
import com.example.small_project.login.LoginActivity;
import com.example.small_project.login.model.ILoginModel;
import com.example.small_project.login.model.LoginModel;

public class LoginPersenter implements ILoginPersenter {
    LoginActivity loginActivity;
    LoginModel loginModel;

    public LoginPersenter(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
        loginModel = new LoginModel();
    }


    @Override
    public void loginPre(String name, String pswd) {
        loginModel.login(ApiService.LOGIN_URL, name, pswd, new ILoginModel.CteanView() {
            @Override
            public void onSuccess(Object obj) {
                LoginBean loginBean = (LoginBean) obj;
                loginActivity.jumpActivity(loginBean);
                loginActivity.showMsg(loginBean);
            }

            @Override
            public void onFailure() {

            }
        });
    }
}
