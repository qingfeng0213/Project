package com.example.small_project.Cicle.presenter;

import com.example.small_project.Cicle.model.CircleModelList;
import com.example.small_project.Cicle.model.ICircleModelList;
import com.example.small_project.bean.CircleBean;
import com.example.small_project.fragment.CircleFragment;

public class CiclePresenter implements ICiclePresenter {
    CircleFragment cfrag;
    private final CircleModelList circleModelList;

    public CiclePresenter(CircleFragment cfrag) {
        this.cfrag = cfrag;
        circleModelList = new CircleModelList();
    }



    @Override
    public void getCirclePreDate(String url) {
        circleModelList.getCircleDate(url, new ICircleModelList.Cteanview() {
            @Override
            public void Succes(Object data) {
                CircleBean cicleBean = (CircleBean) data;
                cfrag.getcircleViewDate(cicleBean);
            }

            @Override
            public void Fuils() {

            }
        });
    }
}
