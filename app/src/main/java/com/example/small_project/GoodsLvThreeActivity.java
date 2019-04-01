package com.example.small_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.small_project.Threemvp.presenter.Presenter;
import com.example.small_project.Threemvp.view.IGoodsLvThreeView;
import com.example.small_project.adapter.GoodsLvThreeAdapter;
import com.example.small_project.bean.GoodsLvThreeBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsLvThreeActivity extends AppCompatActivity implements IGoodsLvThreeView {

    @BindView(R.id.lv_three_recyclerview)
    RecyclerView lvThreeRecyclerview;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    private int page = 1;
    private Handler handler = new Handler();
    private List<GoodsLvThreeBean.ResultBean> list = new ArrayList<>();
    private GoodsLvThreeAdapter goodsLvThreeAdapter;
    private String categoryId1;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_lv_three);
        ButterKnife.bind(this);
        presenter = new Presenter(this);
        //布局
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        lvThreeRecyclerview.setLayoutManager(gridLayoutManager);
        EventBus.getDefault().register(this);
    }

    //获取二级类目ID
    @Subscribe(sticky = true)
    public void getMsy(String categoryId) {
        if (!TextUtils.isEmpty(categoryId)) {
            categoryId1 = categoryId;
            presenter.getThree(categoryId, page);
        }
    }

    @Override
    public void getPreThree(GoodsLvThreeBean goodsLvThreeBean) {
        List<GoodsLvThreeBean.ResultBean> result = goodsLvThreeBean.getResult();
        list.addAll(result);
        goodsLvThreeAdapter = new GoodsLvThreeAdapter(list,this);
        lvThreeRecyclerview.setAdapter(goodsLvThreeAdapter);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
