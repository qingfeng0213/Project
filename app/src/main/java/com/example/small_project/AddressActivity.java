package com.example.small_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.small_project.ShopMvp.presenter.Presenter;
import com.example.small_project.ShopMvp.view.IView;
import com.example.small_project.adapter.NewAddressBean;
import com.example.small_project.bean.AddRessBean;
import com.example.small_project.bean.FIndShopBean;
import com.lljjcoder.citypickerview.widget.CityPicker;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressActivity extends AppCompatActivity implements IView {

    @BindView(R.id.et_shouhuoren)
    EditText etShouhuoren;
    @BindView(R.id.et_Mobile)
    EditText etMobile;
    @BindView(R.id.et_youbian)
    EditText etYoubian;
    @BindView(R.id.tv_city1)
    TextView tvCity1;
    @BindView(R.id.et_xiangxidizhi)
    EditText etXiangxidizhi;
    @BindView(R.id.btn_save)
    Button btnSave;

    private SharedPreferences login;
    private int userId;
    private String sessionId;
    private CityPicker mCP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
    }

    //第三方集成
    public void mYunCityPicher() {
        mCP = new CityPicker.Builder(AddressActivity.this)
                .textSize(20)
                //地址选择
                .title("地址选择")
                .backgroundPop(0xa0000000)
                //文字的颜色
                .titleBackgroundColor("#0CB6CA")
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("xx省")
                .city("xx市")
                .district("xx区")
                //滑轮文字的颜色
                .textColor(Color.parseColor("#000000"))
                //省滑轮是否循环显示
                .provinceCyclic(true)
                //市滑轮是否循环显示
                .cityCyclic(false)
                //地区（县）滑轮是否循环显示
                .districtCyclic(false)
                //滑轮显示的item个数
                .visibleItemsCount(7)
                //滑轮item间距
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        //监听/把选择好的地址放在这个TextView上
        mCP.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省
                String province = citySelected[0];
                //市
                String city = citySelected[1];
                //区。县。（两级联动，必须返回空）
                String district = citySelected[2];
                //邮证编码
                String code = citySelected[3];
                tvCity1.setText(province + city + district);
            }
            @Override
            public void onCancel() {
            }
        });
    }


    @Override
    public void getPreShop(FIndShopBean fIndShopBean) {

    }

    @Override
    public void getPreRess(NewAddressBean newAddressBean) {

    }

    //查看是否添加成功
    @Override
    public void getPreAdd(AddRessBean addRessBean) {
        String message = addRessBean.getMessage();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.tv_city1, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //调用获取地址控件
            case R.id.tv_city1:
                mYunCityPicher();
                mCP.show();
                break;
                //保存地址
            case R.id.btn_save:
                String name = etShouhuoren.getText().toString().trim();
                String phone = etMobile.getText().toString().trim();
                String youbian = etYoubian.getText().toString().trim();
                String dizi = tvCity1.getText().toString().trim();
                login =getSharedPreferences("Login", Context.MODE_PRIVATE);
                HashMap<String, Object> map = new HashMap<>();
                userId = login.getInt("userId", 0);
                sessionId = login.getString("sessionId", "");
                map.put("userId", userId);
                map.put("sessionId", sessionId);
                HashMap<String, String> bodymap = new HashMap<>();
                bodymap.put("realName",name);
                bodymap.put("phone",phone);
                bodymap.put("address",tvCity1.getText().toString()+"  "+dizi);
                bodymap.put("zipCode",youbian);

                Presenter presenter = new Presenter(this);
                presenter.getSiteAdd(bodymap,map);
                finish();
                break;
        }
    }
}
