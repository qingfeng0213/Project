//package com.example.small_project.CreateMap.presenter;
//
//import com.example.small_project.AddressActivity;
//import com.example.small_project.Api.ApiService;
//import com.example.small_project.CreateMap.model.IModel;
//import com.example.small_project.CreateMap.model.Model;
//import com.example.small_project.MyActivity.MySiteActivity;
//import com.example.small_project.MyActivity.OrderActivity;
//import com.example.small_project.adapter.NewAddressBean;
//import com.example.small_project.bean.AddRessBean;
//import com.example.small_project.bean.CreateBean;
//import com.example.small_project.bean.FIndShopBean;
//import com.example.small_project.fragment.ShopFragment;
//
//import java.util.HashMap;
//
//public class Presenter implements IPresenter {
//    OrderActivity orderActivity;
//    private final Model model;
//
//    public Presenter(OrderActivity orderActivity) {
//        this.orderActivity = orderActivity;
//        model = new Model();
//    }
//
//    @Override
//    public void getCrea(HashMap<String, String> objmap, HashMap<String, String> bodymap, HashMap<String, Object> hashMap) {
//        model.getCreate(ApiService.CREATE_URL, objmap, bodymap, hashMap, new IModel.ModelCallBack() {
//            @Override
//            public void onSuccess(Object obj) {
//                CreateBean createBean = (CreateBean) obj;
//                orderActivity.getPreNew(createBean);
//            }
//
//            @Override
//            public void onFail(Throwable e) {
//
//            }
//        });
//    }
//}
