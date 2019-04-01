package com.example.small_project.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.small_project.MyActivity.SelectActivity;
import com.example.small_project.R;
import com.example.small_project.ShopMvp.presenter.Presenter;
import com.example.small_project.ShopMvp.view.IView;
import com.example.small_project.adapter.NewAddressBean;
import com.example.small_project.adapter.ShopAdapter;
import com.example.small_project.bean.AddRessBean;
import com.example.small_project.bean.FIndShopBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;

public class ShopFragment extends Fragment implements IView {
    @BindView(R.id.shopxrecy)
    RecyclerView shopxrecy;
    @BindView(R.id.iv_cricle)
    CheckBox ivCricle;
    @BindView(R.id.txt_all)
    TextView txtAll;
    @BindView(R.id.layout_all)
    RelativeLayout layoutAll;
    @BindView(R.id.all_price)
    TextView allPrice;
    @BindView(R.id.sum_price_txt)
    TextView sumPriceTxt;
    @BindView(R.id.sum_price)
    RelativeLayout sumPrice;
    @BindView(R.id.layout_buttom)
    RelativeLayout layoutButtom;
    Unbinder unbinder;

    private SharedPreferences login;
    private int userId;
    private String sessionId;
    private ShopAdapter shopAdapter;
    private Presenter presenter;
    private List<FIndShopBean.ResultBean> list;
    private HashMap<String, Object> map;
    private int priceAll = 0;
    private int countAll = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopfrag, container, false);

        unbinder = ButterKnife.bind(this, view);
        //RecycleView的布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        shopxrecy.setLayoutManager(linearLayoutManager);
        //获取在登陆界面储存的userId和sessionId
        login = getActivity().getSharedPreferences("Login", MODE_PRIVATE);
        userId = this.login.getInt("userId", 0);
        sessionId = this.login.getString("sessionId", "");
        map = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);

        presenter = new Presenter(this);
        //全选的点击事件
        ivCricle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shopAdapter != null) {
                    shopAdapter.getCheckBoxAllState(ivCricle.isChecked());
                }
            }
        });
//        //创建订单的点击跳转
//        sumPrice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        //点击结算 判断后进行跳转
        sumPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sumPriceTxt.getText().toString().equals("0")){
                    EventBus.getDefault().postSticky(list);
                    startActivity(new Intent(getActivity(),SelectActivity.class));
                }else {
                    Toast.makeText(getActivity(), "选中商品后结算", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (map != null) {
            presenter.getShopData(map);
        }
    }

    //购物车的数据
    @Override
    public void getPreShop(FIndShopBean fIndShopBean) {
        if (fIndShopBean != null) {
            list = fIndShopBean.getResult();
            shopAdapter = new ShopAdapter(getActivity(), list);
            shopxrecy.setAdapter(shopAdapter);
            shopAdapter.notifyDataSetChanged();
            shopAdapter.setOnCheckListener(new ShopAdapter.OnCheckListener() {
                @Override
                public void onCheck(boolean isCheck) {
                    ivCricle.setChecked(isCheck);
                }
            });
            shopAdapter.setSetAllPriceListenter(new ShopAdapter.setAllPriceListenter() {
                @Override
                public void setAllPrice(final List<FIndShopBean.ResultBean> list) {
                    int totalPrice = 0;
                    int totalNum = 0;
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).isCheck()) {
                            int price = list.get(i).getPrice();
                            int count = list.get(i).getCount();
                            priceAll = price * count;
                            countAll = count;
                        } else {
                            priceAll = 0;
                            countAll = 0;
                        }
                        totalPrice += priceAll;
                        totalNum += countAll;
                    }
                    allPrice.setText(totalPrice + "");
                    sumPriceTxt.setText("结算(" + totalNum + ")");

                }
            });
//            shopAdapter.setListener(new ShopAdapter.ShopCallBackListener() {
//                @Override
//                public void callBack() {
//                    //在这里重新遍历已经改状态后的数据，
//                    // 这里不能break跳出，因为还需要计算后面点击商品的价格和数目，所以必须跑完整个循环
//                    double totalPrice = 0;
//                    //勾选商品的数量，不是该商品购买的数量
//                    int num = 0;
//                    //所有商品总数，和上面的数量做比对，如果两者相等，则说明全选
//                    int totalNum = 0;
//                    for (int a = 0; a < list.size(); a++) {
//                            totalNum = totalNum + list.get(a).getCount();
//                            //取选中的状态
//                            if (list.get(a).isCheck()) {
//                                totalPrice = totalPrice + (list.get(a).getPrice() * list.get(a).getCount());
//                                num = num + list.get(a).getCount();
//                        }
//                    }
//
//                    if (num < totalNum) {
//                        //不是全部选中
//                        ivCricle.setChecked(false);
//                    } else {
//                        //是全部选中
//                        ivCricle.setChecked(true);
//                    }
//
//                    allPrice.setText("合计：" + totalPrice);
//                    sumPriceTxt.setText("去结算(" + num + ")");
//                }
//            });
        }
    }

    @Override
    public void getPreRess(NewAddressBean newAddressBean) {

    }

    @Override
    public void getPreAdd(AddRessBean addRessBean) {

    }

//    /**
//     * 修改选中状态，获取价格和数量
//     */
//    private void checkSeller(boolean bool) {
//        double totalPrice = 0;
//        int num = 0;
//        for (int a = 0; a < list.size(); a++) {
//            //遍历商家，改变状态
//                list.get(a).setCheck(bool);
//                totalPrice = totalPrice + (list.get(a).getPrice() * list.get(a).getCount());
//                num = num + list.get(a).getCount();
//        }
//        if (bool) {
//            allPrice.setText("合计：" + totalPrice);
//            sumPriceTxt.setText("去结算(" + num + ")");
//        } else {
//            allPrice.setText("合计：0.00");
//            sumPriceTxt.setText("去结算(0)");
//        }
//    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
