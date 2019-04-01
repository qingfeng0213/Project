package com.example.small_project.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.small_project.AllOrderMVP.presenter.Presenter;
import com.example.small_project.AllOrderMVP.view.IView;
import com.example.small_project.MyAdapter.SelectBillAdapter;
import com.example.small_project.R;
import com.example.small_project.adapter.AllOrderAdapter;
import com.example.small_project.bean.AllOrderBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyListFragment extends Fragment implements IView {
    @BindView(R.id.indent_but_one)
    RadioButton indentButOne;
    @BindView(R.id.indent_but_two)
    RadioButton indentButTwo;
    @BindView(R.id.indent_but_three)
    RadioButton indentButThree;
    @BindView(R.id.indent_but_four)
    RadioButton indentButFour;
    @BindView(R.id.indent_but_five)
    RadioButton indentButFive;
    @BindView(R.id.indent_group)
    RadioGroup indentGroup;
    Unbinder unbinder;
    @BindView(R.id.orderrexy)
    XRecyclerView orderrexy;
    private SharedPreferences login;
    private int userId;
    private String sessionId;
    private HashMap<String, Object> map;
    private Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listfrag, container, false);
        unbinder = ButterKnife.bind(this, view);
        login = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        map = new HashMap<>();
        userId = login.getInt("userId", 0);
        sessionId = login.getString("sessionId", "");
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        presenter = new Presenter(this);
        presenter.getorder(0, map);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        orderrexy.setLayoutManager(linearLayoutManager);
        return view;
    }


    @Override
    public void getPreorder(AllOrderBean allOrderBean) {
        List<AllOrderBean.OrderListBean> orderList = allOrderBean.getOrderList();
        SelectBillAdapter selectBillAdapter = new SelectBillAdapter(orderList,getActivity());
        orderrexy.setAdapter(selectBillAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.indent_but_one, R.id.indent_but_two, R.id.indent_but_three, R.id.indent_but_four, R.id.indent_but_five})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.indent_but_one:
                presenter.getorder(0, map);
                break;
            case R.id.indent_but_two:
                presenter.getorder(1, map);
                break;
            case R.id.indent_but_three:
                presenter.getorder(2, map);
                break;
            case R.id.indent_but_four:
                presenter.getorder(3, map);
                break;
            case R.id.indent_but_five:
                presenter.getorder(9, map);
                break;
        }
    }
}
