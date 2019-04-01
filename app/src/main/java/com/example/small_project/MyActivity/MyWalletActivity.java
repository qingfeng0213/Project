package com.example.small_project.MyActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.small_project.MoneyMVP.presenter.Presenter;
import com.example.small_project.MoneyMVP.view.IView;
import com.example.small_project.R;
import com.example.small_project.bean.MoneyBean;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyWalletActivity extends AppCompatActivity implements IView {

    @BindView(R.id.wall_money)
    TextView wallMoney;
    @BindView(R.id.wall_recy)
    RecyclerView wallRecy;

    private SharedPreferences login;
    private int userId;
    private String sessionId;
    private HashMap<String, Object> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        ButterKnife.bind(this);
        login = getSharedPreferences("Login", Context.MODE_PRIVATE);
        map = new HashMap<>();
        userId = login.getInt("userId", 0);
        sessionId = login.getString("sessionId", "");
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        Presenter presenter = new Presenter(this);
        presenter.getyu(wallMoney+"",map);
    }

    @Override
    public void getPremoney(MoneyBean moneyBean) {
        int balance = moneyBean.getResult().getBalance();
        wallMoney.setText(balance+"");
    }
}
