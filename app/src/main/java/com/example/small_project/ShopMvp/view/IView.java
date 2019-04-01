package com.example.small_project.ShopMvp.view;

import com.example.small_project.adapter.NewAddressBean;
import com.example.small_project.bean.AddRessBean;
import com.example.small_project.bean.FIndShopBean;

public interface IView {
    void getPreShop(FIndShopBean fIndShopBean);
    void getPreRess(NewAddressBean newAddressBean);
    void getPreAdd(AddRessBean addRessBean);
}
