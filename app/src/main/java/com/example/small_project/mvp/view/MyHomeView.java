package com.example.small_project.mvp.view;

import com.example.small_project.bean.BannerBean;
import com.example.small_project.bean.DetailsBean;
import com.example.small_project.bean.GoodsLvOneBean;
import com.example.small_project.bean.GoodsLvTwoBean;
import com.example.small_project.bean.MyBean;

public interface MyHomeView {
    void getViewData(MyBean myBean);
    void getOneData(GoodsLvOneBean goodsLvOneBean);
    void getTwoData(GoodsLvTwoBean goodsLvTwoBean);
    void getBanner(BannerBean bannerBean);
}
