package com.example.small_project.MyActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.example.small_project.Paymvp.presenter.Presenter;
import com.example.small_project.Paymvp.view.IView;
import com.example.small_project.R;
import com.example.small_project.bean.PayBean;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayActivity extends AppCompatActivity implements IView {

    @BindView(R.id.pag_bill_btn)
    Button pagBillBtn;
    private SharedPreferences login;
    private int userId;
    private String sessionId;
    private HashMap<String, Object> map;
    private Presenter presenter;
    private String orderId;
    private String totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        totalPrice = intent.getStringExtra("totalPrice");
        orderId = intent.getStringExtra("orderId");
        login = getSharedPreferences("Login", MODE_PRIVATE);
        map = new HashMap<>();
        userId = login.getInt("userId", 0);
        sessionId = login.getString("sessionId", "");
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        presenter = new Presenter(this);
        pagBillBtn.setText("支付"+totalPrice+"元");
    }

    @Override
    public void getPreData(PayBean payBean) {
        Toast.makeText(this, payBean.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.pag_bill_btn)
    public void onViewClicked() {
        presenter.getPay(orderId,1,map);
    }
}
