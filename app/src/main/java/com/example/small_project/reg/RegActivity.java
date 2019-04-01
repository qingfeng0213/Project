package com.example.small_project.reg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.small_project.R;
import com.example.small_project.bean.RegBean;
import com.example.small_project.login.LoginActivity;
import com.example.small_project.reg.presenter.LoginPersenter;
import com.example.small_project.reg.view.LoginView;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegActivity extends AppCompatActivity implements LoginView {
    @BindView(R.id.login_name_et)
    EditText loginNameEt;
    @BindView(R.id.login_pswd_et)
    EditText loginPswdEt;
    @BindView(R.id.fanhui)
    TextView fanhui;
    @BindView(R.id.login_rebt)
    Button loginRebt;
    private RegBean regBean;
    private Gson gson;

    String mLoginName, mRegisterPswd;
    private LoginPersenter loginPersenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
        //初始persenter
        loginPersenter = new LoginPersenter(this);
        loginRebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mLoginName = loginNameEt.getText().toString();
                mRegisterPswd = loginPswdEt.getText().toString();
                if (!mLoginName.isEmpty() && !mRegisterPswd.isEmpty()) {
                    loginPersenter.loginPre(mLoginName, mRegisterPswd);
                }
            }
        });
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showMsg(final RegBean regBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String message = regBean.getMessage();
                Toast.makeText(RegActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void jumpActivity(final RegBean regbean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String status = regbean.getStatus();
                if (status.equals("0000")) {
                    Intent intent = new Intent(RegActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @OnClick({R.id.fanhui, R.id.login_rebt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                break;
            case R.id.login_rebt:
                break;
        }
    }
}
