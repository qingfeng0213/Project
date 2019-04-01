package com.example.small_project.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.small_project.MyView.CustomSearchView;
import com.example.small_project.R;
import com.example.small_project.SeekActivity;
import com.example.small_project.adapter.GoodsLvOneAdapter;
import com.example.small_project.adapter.GoodsLvTwoAdapter;
import com.example.small_project.adapter.RecyclerAdapter;
import com.example.small_project.bean.BannerBean;
import com.example.small_project.bean.GoodsLvOneBean;
import com.example.small_project.bean.GoodsLvTwoBean;
import com.example.small_project.bean.MyBean;
import com.example.small_project.mvp.presenter.MyHomeListPresenter;
import com.example.small_project.mvp.view.MyHomeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment implements MyHomeView {

    @BindView(R.id.RecyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.myedit)
    CustomSearchView myedit;
    private ArrayList<MyBean.ResultBean> list;
    private List<BannerBean.ResultBean> bannerlist;
    private MyHomeListPresenter myHomeListPresenter;
    private RecyclerView lv_one_recyclerView;
    private RecyclerView lv_two_recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bighomefrag, container, false);
        recyclerView = view.findViewById(R.id.RecyclerView);
        EventBus.getDefault().register(this);
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //获取P层
        myHomeListPresenter = new MyHomeListPresenter(this);
        //轮播
        myHomeListPresenter.getBanner();
        //首页数据
        myHomeListPresenter.getModelData();
        unbinder = ButterKnife.bind(this, view);

        myedit.setSetOnImgSearchClickListener(new CustomSearchView.setOnImgSearchClickListener() {
            @Override
            public void setOnImgSearchClick() {
                //初始化PopupWindow
                initPopupWindow();
            }
        });
        myedit.setOnEditSearchClickListener(new CustomSearchView.setOnBtnSearchClickListener() {
            @Override
            public void setOnBtnSearchClick(String searchdata) {
                Intent intent = new Intent(getActivity(), SeekActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    //获取一级类目ID
    @Subscribe(sticky = true)
    public void getMsy(String firstCategoryId) {
        if (!TextUtils.isEmpty(firstCategoryId)) {
            myHomeListPresenter.getModelTwoData(firstCategoryId);
        }
    }
    @Override
    public void getViewData(MyBean myBean) {
        if (myBean != null) {
            MyBean.ResultBean result = myBean.getResult();
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getActivity(), result, bannerlist);
            recyclerView.setAdapter(recyclerAdapter);
        }
    }

    @Override
    public void getBanner(BannerBean bannerBean) {

        if (bannerBean != null) {
            bannerlist = bannerBean.getResult();
        }
    }

    //获取一级类目
    @Override
    public void getOneData(GoodsLvOneBean goodsLvOneBean) {
        List<GoodsLvOneBean.ResultBean> result = goodsLvOneBean.getResult();
        GoodsLvOneAdapter goodsLvOneAdapter = new GoodsLvOneAdapter(result, getActivity());
        lv_one_recyclerView.setAdapter(goodsLvOneAdapter);
    }

    //获取二级类目
    @Override
    public void getTwoData(GoodsLvTwoBean goodsLvTwoBean) {
        List<GoodsLvTwoBean.ResultBean> result = goodsLvTwoBean.getResult();
        if (result != null) {
            GoodsLvTwoAdapter goodsLvTwoAdapter = new GoodsLvTwoAdapter(result, getActivity());
            lv_two_recyclerView.setAdapter(goodsLvTwoAdapter);
        }
    }

    //初始化PopupWindow
    public void initPopupWindow() {
        View popupWindow_layout = LayoutInflater.from(getActivity()).inflate(R.layout.popupwindowd_layout, null, false);
        //设置弹窗透明度
        popupWindow_layout.setAlpha(0.9f);
        PopupWindow popupWindow = new PopupWindow(popupWindow_layout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 设置PopupWindow的弹出和消失效果
        //自定义文件设置动画
        popupWindow.setAnimationStyle(R.style.popupAnimation);
        //设置背景色
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        popupWindow.setTouchable(true);
        //设置显示位置
        popupWindow.showAsDropDown(myedit.findViewById(R.id.img_search));
        //popupWindow控件
        lv_one_recyclerView = popupWindow_layout.findViewById(R.id.lv_One_RecyclerView);
        lv_two_recyclerView = popupWindow_layout.findViewById(R.id.lv_Two_RecyclerView);
        //布局
        lv_one_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), OrientationHelper.HORIZONTAL, false));
        lv_two_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), OrientationHelper.HORIZONTAL, false));
        //默认加载数据
        myHomeListPresenter.getModelOneData();
        //默认加载二级类目
        myHomeListPresenter.getModelTwoData("1001002");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

}
