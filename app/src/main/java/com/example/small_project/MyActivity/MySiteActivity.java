package com.example.small_project.MyActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import com.example.small_project.AddressActivity;
import com.example.small_project.MainActivity;
import com.example.small_project.R;
import com.example.small_project.ShopMvp.presenter.Presenter;
import com.example.small_project.ShopMvp.view.IView;
import com.example.small_project.adapter.NewAddressBean;
import com.example.small_project.adapter.ShouAdapter;
import com.example.small_project.bean.AddRessBean;
import com.example.small_project.bean.FIndShopBean;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MySiteActivity extends AppCompatActivity implements IView {

    Button newAddressTextSave;
    @BindView(R.id.my_site_recy)
    RecyclerView mySiteRecy;
    @BindView(R.id.my_new_but)
    Button myNewBut;
    private SharedPreferences login;
    private int userId;
    private String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_address);
        ButterKnife.bind(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        mySiteRecy.setLayoutManager(linearLayoutManager);
        login = getSharedPreferences("Login", MODE_PRIVATE);
        HashMap<String, Object> map = new HashMap<>();
        userId = login.getInt("userId", 0);
        sessionId = login.getString("sessionId", "");
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        Presenter presenter = new Presenter(this);
        presenter.getNewAdd(map);
    }

    @Override
    public void getPreShop(FIndShopBean fIndShopBean) {

    }
    @Override
    public void getPreRess(NewAddressBean newAddressBean) {
        List<NewAddressBean.ResultBean> list = newAddressBean.getResult();
        if (list != null) {
            ShouAdapter shouAdapter = new ShouAdapter(MySiteActivity.this,list);
            mySiteRecy.setAdapter(shouAdapter);
            Toast.makeText(this, newAddressBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getPreAdd(AddRessBean addRessBean) {

    }

    @OnClick(R.id.my_new_but)
    public void onViewClicked() {
        startActivity(new Intent(MySiteActivity.this,AddressActivity.class));
    }
}
