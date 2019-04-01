package com.example.small_project.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.small_project.MyActivity.MyCircleActivity;
import com.example.small_project.MyActivity.MyDataActivity;
import com.example.small_project.MyActivity.MyFootActivity;
import com.example.small_project.MyActivity.MySiteActivity;
import com.example.small_project.MyActivity.MyWalletActivity;
import com.example.small_project.MyMessMVP.presenter.Presenter;
import com.example.small_project.MyMessMVP.view.IView;
import com.example.small_project.R;
import com.example.small_project.bean.MyMessageBean;
import com.example.small_project.login.LoginActivity;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyFragment extends Fragment implements IView {
    @BindView(R.id.My_Data)
    TextView MyData;
    @BindView(R.id.My_Circle)
    TextView MyCircle;
    @BindView(R.id.My_Footmark)
    TextView MyFootmark;
    @BindView(R.id.My_Wallet)
    TextView MyWallet;
    @BindView(R.id.My_Site)
    TextView MySite;
    @BindView(R.id.my_Hand)
    ImageView myHand;
    @BindView(R.id.my_name)
    TextView myName;
    Unbinder unbinder;
    private SharedPreferences login;
    private int userId;
    private String sessionId;
    private HashMap<String, Object> map;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myfrag, container, false);
        unbinder = ButterKnife.bind(this, view);

        Presenter presenter = new Presenter(this);
        //获取SharePreferences
        login = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        map = new HashMap<>();
        userId = login.getInt("userId", 0);
        sessionId = login.getString("sessionId", "");
        map.put("userId", userId);
        map.put("sessionId", sessionId);
            presenter.getmess(map);
        return view;
    }
    @OnClick({R.id.My_Data, R.id.My_Circle, R.id.My_Footmark, R.id.My_Wallet, R.id.My_Site, R.id.my_Hand, R.id.my_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.My_Data:
                startActivity(new Intent(getActivity(),MyDataActivity.class));
                break;
            case R.id.My_Circle:
                startActivity(new Intent(getActivity(),MyCircleActivity.class));
                break;
            case R.id.My_Footmark:
                startActivity(new Intent(getActivity(),MyFootActivity.class));
                break;
            case R.id.My_Wallet:
                startActivity(new Intent(getActivity(),MyWalletActivity.class));
                break;
            case R.id.My_Site:
                startActivity(new Intent(getActivity(),MySiteActivity.class));
                break;
            case R.id.my_Hand:

                break;
            case R.id.my_name:
                startActivity(new Intent(getActivity(),LoginActivity.class));
                break;
        }
    }

    @Override
    public void getPremess(MyMessageBean myMessageBean) {
            String headPic1 = myMessageBean.getResult().getHeadPic();
            String nickName1 = myMessageBean.getResult().getNickName();
            myName.setText(nickName1);
            //圆角
            RequestOptions requestOptions = RequestOptions.circleCropTransform();
            Glide.with(getActivity()).load(headPic1).apply(requestOptions).into(myHand);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
