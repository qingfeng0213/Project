package com.example.small_project.mvp.presenter;

import com.example.small_project.Api.ApiService;
import com.example.small_project.DetailsActivity;
import com.example.small_project.MainActivity;
import com.example.small_project.bean.BannerBean;
import com.example.small_project.bean.DetailsBean;
import com.example.small_project.bean.GoodsLvOneBean;
import com.example.small_project.bean.GoodsLvTwoBean;
import com.example.small_project.bean.MyBean;
import com.example.small_project.fragment.HomeFragment;
import com.example.small_project.mvp.model.IHomeListModel;
import com.example.small_project.mvp.model.MyHomeListModel;

//继承Presenter的接口,继承Model类的内部接口
public class MyHomeListPresenter implements IHomeLitsPresenter{

    HomeFragment mview;
    MyHomeListModel myHomeListModel;
    DetailsActivity detailsActivity;

    public MyHomeListPresenter(DetailsActivity detailsActivity) {
        this.detailsActivity = detailsActivity;
        myHomeListModel = new MyHomeListModel();
    }

    public MyHomeListPresenter(HomeFragment mview) {
        this.mview = mview;
        //获取Model层
        myHomeListModel = new MyHomeListModel();
    }
    //实现Presenter接口内的方法
    @Override
    public void getModelData() {
        myHomeListModel.getDate(new IHomeListModel.ModelCallBack() {
            @Override
            public void onSuccess(Object obj) {
                MyBean bean = (MyBean) obj;
                mview.getViewData(bean);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }

    @Override
    public void getModelOneData() {
        myHomeListModel.getOneDate(new IHomeListModel.ModelCallBack() {
            @Override
            public void onSuccess(Object obj) {
                GoodsLvOneBean goodsLvOneBean = (GoodsLvOneBean) obj;
                mview.getOneData(goodsLvOneBean);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }

    @Override
    public void getModelTwoData(String categoryId) {
                myHomeListModel.getTwoDate(categoryId, new IHomeListModel.ModelCallBack() {
                    @Override
                    public void onSuccess(Object obj) {
                        GoodsLvTwoBean goodsLvTwoBean = (GoodsLvTwoBean) obj;
                        mview.getTwoData(goodsLvTwoBean);
                    }

                    @Override
                    public void onFail(Throwable e) {

                    }
                });
    }

    @Override
    public void getBanner() {
        myHomeListModel.getBannerDate(ApiService.BANNER_URL, new IHomeListModel.ModelCallBack() {
            @Override
            public void onSuccess(Object obj) {
                BannerBean bannerBean = (BannerBean) obj;
                mview.getBanner(bannerBean);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }
}
