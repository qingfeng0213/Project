package com.example.small_project.MyActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.small_project.NewSitemvp.presenter.Presenter;
import com.example.small_project.NewSitemvp.view.IView;
import com.example.small_project.R;
import com.example.small_project.adapter.CreateBillAdapter;
import com.example.small_project.adapter.NewAddressBean;
import com.example.small_project.bean.CreateBean;
import com.example.small_project.bean.FIndShopBean;
import com.example.small_project.bean.NumBean;

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

public class SelectActivity extends AppCompatActivity implements IView{


    List<FIndShopBean.ResultBean> list2 = new ArrayList<>();
    //    @BindView(R.id.address_name)
//    TextView addressName;
//    @BindView(R.id.address_phone)
//    TextView addressPhone;
//    @BindView(R.id.address_address)
//    TextView addressAddress;
//    @BindView(R.id.bottom_put)
//    ImageView bottomPut;
    @BindView(R.id.shoppingcar_recycleView)
    RecyclerView shoppingcarRecycleView;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.bill_goods_count)
    TextView billGoodsCount;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.bill_goods_price)
    TextView billGoodsPrice;
    @BindView(R.id.commit_bill_btn)
    Button commitBillBtn;
    @BindView(R.id.sitename)
    TextView sitename;
    @BindView(R.id.sitenum)
    TextView sitenum;
    @BindView(R.id.mysite)
    TextView mysite;
    private SharedPreferences login;
    private int userId;
    private String sessionId;
    private int id;
    private Presenter presenter;
    private HashMap<String, Object> map;
    private int totalPrice;
    private HashMap<String, Object> hashMap;
    private List<NumBean> numlist = new ArrayList<>();
    private String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        //RecycleView的布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        shoppingcarRecycleView.setLayoutManager(linearLayoutManager);
        login = getSharedPreferences("Login", MODE_PRIVATE);
        map = new HashMap<>();
        userId = login.getInt("userId", 0);
        sessionId = login.getString("sessionId", "");
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        presenter = new Presenter(this);
        presenter.getNewAdd(map);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getEventBus(List<FIndShopBean.ResultBean> list) {
        totalPrice = 0;
        int totalNum = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isCheck()) {
                FIndShopBean.ResultBean resultBean = new FIndShopBean.ResultBean();
                resultBean.setCommodityId(list.get(i).getCommodityId());
                resultBean.setCount(list.get(i).getCount());
                resultBean.setCommodityName(list.get(i).getCommodityName());
                resultBean.setPic(list.get(i).getPic());
                resultBean.setPrice(list.get(i).getPrice());
                list2.add(resultBean);
            }
        }
        for (int i = 0; i < list2.size(); i++) {
            totalPrice += list2.get(i).getPrice();
            totalNum += list2.get(i).getCount();
        }
        billGoodsPrice.setText(totalPrice + "");
        billGoodsCount.setText(totalNum + "");
        shoppingcarRecycleView.setAdapter(new CreateBillAdapter(list2, this));

        for (int i = 0; i<list2.size();i++) {
            NumBean numBean = new NumBean();
            numBean.setCommodityId(list2.get(i).getCommodityId());
            numBean.setAmount(list2.get(i).getCount());
            numlist.add(numBean);
        }
    }

    @Override
    public void getPreRess(NewAddressBean newAddressBean) {
        //获取了一个固定地址
        List<NewAddressBean.ResultBean> result = newAddressBean.getResult();
        sitename.setText(result.get(0).getRealName());
        sitenum.setText(result.get(0).getPhone());
        mysite.setText(result.get(0).getAddress());
        id = result.get(0).getId();
    }

    @Override
    public void getPrePay(CreateBean createBean) {
        orderId = createBean.getOrderId();
        if (createBean.getStatus().equals("0000")) {
            Intent intent = new Intent(SelectActivity.this, PayActivity.class);
            intent.putExtra("totalPrice",billGoodsPrice.getText().toString());
            intent.putExtra("orderId",orderId);
            startActivity(intent);
            Toast.makeText(this, createBean.getMessage(), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, createBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick(R.id.commit_bill_btn)
    public void onViewClicked() {
        try {
            //相当于中括号
            JSONArray jsonArray=new JSONArray();
            //相当于大括号
            for (int i = 0; i<numlist.size();i++) {
                JSONObject jsonObject=null;
                jsonObject = new JSONObject();
                jsonObject.put("commodityId",numlist.get(i).getCommodityId());
                jsonObject.put("amount",numlist.get(i).getAmount());
                jsonArray.put(jsonObject);
            }

            hashMap = new HashMap<>();
            hashMap.put("userId",userId);
            hashMap.put("sessionId",sessionId);
            String data = jsonArray.toString();
            presenter.getPayOrder(data,totalPrice,id,map);
        }catch (JSONException e){
            e.printStackTrace();
        }
        
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
