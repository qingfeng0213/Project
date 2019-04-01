package com.example.small_project;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.small_project.Detailsmvp.presenter.MyHomeListPresenter;
import com.example.small_project.Detailsmvp.view.DetailsView;
import com.example.small_project.bean.BondBean;
import com.example.small_project.bean.DetailsBean;
import com.example.small_project.bean.FIndShopBean;
import com.example.small_project.bean.IdBean;
import com.example.small_project.bean.SyncShopBean;
import com.google.gson.JsonArray;
import com.recker.flybanner.FlyBanner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends AppCompatActivity implements DetailsView{
    String id;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.detailsfly)
    FlyBanner detailsfly;
    @BindView(R.id.qian)
    TextView qian;
    @BindView(R.id.sold)
    TextView sold;
    @BindView(R.id.particulars)
    TextView particulars;
    @BindView(R.id.myshang)
    TextView myshang;
    //private String details;
    String a;
    @BindView(R.id.back_home)
    ImageView backHome;
    @BindView(R.id.add_car)
    ImageView addCar;
    @BindView(R.id.add_buy)
    ImageView addBuy;
    private MyHomeListPresenter myHomeListPresenter;
    private ArrayList<String> imageurl = new ArrayList<>();
    private SharedPreferences login;
    private int userId;
    private String sessionId;
    String s;
    private String id1;
    private List<FIndShopBean.ResultBean> findlist;
    private ArrayList<BondBean> bondBeans;
    private HashMap<String, Object> hashMap;
    private HashMap<String, Object> map;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        //获取P层
        myHomeListPresenter = new MyHomeListPresenter(this);
        //注册EventBus
        EventBus.getDefault().register(this);
        //获取SP
        login = getSharedPreferences("Login", MODE_PRIVATE);
        userId = this.login.getInt("userId", 0);
        sessionId = this.login.getString("sessionId", "");
        map = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        //返回按钮
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(DetailsActivity.this, "返回上一界面", Toast.LENGTH_SHORT).show();
            }
        });
        bondBeans = new ArrayList<>();
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getEventBus(String s) {
        //首页传入详情的ID
        id1 = s;
        myHomeListPresenter.getIData(s);
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.add_car, R.id.add_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //点击同步
            case R.id.add_car:
            try {
                //相当于中括号
                JSONArray jsonArray=new JSONArray();
                //相当于大括号
                JSONObject jsonObject=null;
                jsonObject = new JSONObject();
                jsonObject.put("commodityId",id1);
                jsonObject.put("count",1);
                jsonArray.put(jsonObject);

                hashMap = new HashMap<>();
                hashMap.put("userId",userId);
                hashMap.put("sessionId",sessionId);
                String data = jsonArray.toString();
                myHomeListPresenter.getSync(data,hashMap);
                myHomeListPresenter.getShopData(hashMap);
            }catch (JSONException e){
                e.printStackTrace();
            }
                break;
            case R.id.add_buy:
                break;
        }
    }

    //展示详情界面
    @Override
    public void getViewData(DetailsBean detailsBean) {
        String picture = detailsBean.getResult().getPicture();
        String[] split = picture.split("\\,");
        for (String a : split) {
            imageurl.add(a);
        }
        detailsfly.setImagesUrl(imageurl);
        //获取价格
        int price = detailsBean.getResult().getPrice();
        qian.setText("¥" + price);
        //获取已售数量
        int stock = detailsBean.getResult().getStock();
        sold.setText("已售" + stock + "件");
        //获取商品介绍
        String commodityName = detailsBean.getResult().getCommodityName();
        particulars.setText(commodityName);
        //获取WebView网页
        String details = detailsBean.getResult().getDetails();
        webview.loadDataWithBaseURL(null, details, "text/html", "UTF-8", null);
    }

    //吐司同步是否成功
    @Override
    public void getPreSync(SyncShopBean syncShopBean) {
        String message = syncShopBean.getMessage();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    //查询购物车
    @Override
    public void getPreShop(FIndShopBean fIndShopBean) {
        Log.i("TAG", "getPreShop: "+fIndShopBean.getMessage());
        findlist = fIndShopBean.getResult();
        //判断购物车内容不为空,不为空的话循环遍历将数据放入自己新建的Bean层
        if (findlist != null) {
            for (int i =0; i<findlist.size();i++) {
                BondBean bondBean = new BondBean();
                bondBean.setCount(findlist.get(i).getCount());
                bondBean.setCommodityId(findlist.get(i).getCommodityId());
                //添加进自己的Bean层
                bondBeans.add(bondBean);
            }
            //实例化Bean层
            BondBean bondBean1 = new BondBean();
            bondBean1.setCommodityId(Integer.parseInt(id1));
            bondBean1.setCount(1);
            bondBeans.add(bondBean1);
                try {
                    JSONArray jsonArray = new JSONArray();
                    for (int i = 0;i<bondBeans.size();i++) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("commodityId",bondBeans.get(i).getCommodityId());
                    jsonObject.put("count",1);
                    jsonArray.put(jsonObject);
                }
                    String s = jsonArray.toString();
                    Log.i("xxx",s);
                    myHomeListPresenter.getSync(s,hashMap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

