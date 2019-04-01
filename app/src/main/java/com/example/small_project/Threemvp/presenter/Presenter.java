package com.example.small_project.Threemvp.presenter;

import com.example.small_project.GoodsLvThreeActivity;
import com.example.small_project.Threemvp.model.Model;
import com.example.small_project.bean.GoodsLvThreeBean;
import com.example.small_project.mvp.model.IHomeListModel;

public class Presenter implements IPresenter{
    GoodsLvThreeActivity goodsLvThreeActivity;
    private final Model model;

    public Presenter(GoodsLvThreeActivity goodsLvThreeActivity) {
        this.goodsLvThreeActivity = goodsLvThreeActivity;
        model = new Model();
    }

    @Override
    public void getThree(String categoryId, int page) {
        model.getTwoDate(categoryId, page, new IHomeListModel.ModelCallBack() {
            @Override
            public void onSuccess(Object obj) {
                GoodsLvThreeBean goodsLvThreeBean = (GoodsLvThreeBean) obj;
                goodsLvThreeActivity.getPreThree(goodsLvThreeBean);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }
}
