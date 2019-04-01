package com.example.small_project.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.small_project.MainActivity;
import com.example.small_project.R;
import com.example.small_project.bean.LoginBean;
import com.example.small_project.login.presenter.LoginPersenter;
import com.example.small_project.login.view.LoginView;
import com.example.small_project.reg.RegActivity;
import com.google.gson.Gson;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;

import java.util.ArrayList;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements LoginView {
    EditText mLogin,mRegister;
    Button mLoginBut;
    TextView mRegisterBut;
    String mLoginName,mRegisterPswd;
    private LoginPersenter loginPersenter;
    private CheckBox jz;
    private SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLogin = findViewById(R.id.login_name_et);
        mRegister = findViewById(R.id.login_pswd_et);
        mLoginBut= findViewById(R.id.login_lgbt);
        mRegisterBut= findViewById(R.id.login_rebt);
        jz = findViewById(R.id.jz);
        //初始persenter
        loginPersenter = new LoginPersenter(this);
        //获取SP
        sp = getSharedPreferences("Login", Context.MODE_PRIVATE);
        boolean y = sp.getBoolean("记住", false);
        if (y) {
            String user = sp.getString("user", "");
            String pass = sp.getString("pass", "");
            mLogin.setText(user);
            mRegister.setText(pass);
            jz.setChecked(true);
        }
        //登陆按钮的点击事件
        mLoginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    mLoginName = mLogin.getText().toString();
                    mRegisterPswd = mRegister.getText().toString();

                    if(!mLoginName.isEmpty()&&!mRegisterPswd.isEmpty()){

                        loginPersenter.loginPre(mLoginName,mRegisterPswd);

                    }else {
                        Toast.makeText(LoginActivity.this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
                    }
            }
        });
        //注册按钮的点击事件
        mRegisterBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(intent);
            }
        });
    }





    @Override
    public void showMsg(final LoginBean loginBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String message = loginBean.getMessage();
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void jumpActivity(final LoginBean loginBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (loginBean.getStatus().equals("0000")){
                    int userId = loginBean.getResult().getUserId();
                    String headPic = loginBean.getResult().getHeadPic();
                    String nickName = loginBean.getResult().getNickName();
                    String sessionId = loginBean.getResult().getSessionId();
                    String phone = loginBean.getResult().getPhone();
                    // 4:如果 不为空 存值
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putBoolean("记住",jz.isChecked());
                    edit.putString("user",mLoginName);
                    edit.putString("pass",mRegisterPswd);

                    edit.putString("headPic",headPic);
                    edit.putString("nickName",nickName);

                    edit.putString("sessionId",sessionId);
                    edit.putInt("userId", userId);

                    edit.putString("phone",phone);
                    edit.commit();
                    finish();
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);
                }
            }
        });
    }
}
