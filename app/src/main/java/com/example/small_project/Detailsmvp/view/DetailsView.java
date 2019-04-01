package com.example.small_project.Detailsmvp.view;

import com.example.small_project.bean.DetailsBean;
import com.example.small_project.bean.FIndShopBean;
import com.example.small_project.bean.SyncShopBean;

public interface DetailsView {
    void getViewData(DetailsBean detailsBean);
    void getPreSync(SyncShopBean syncShopBean);
    void getPreShop(FIndShopBean fIndShopBean);
}
