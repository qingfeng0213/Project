//package com.example.small_project.OrderFragment;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.OrientationHelper;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.small_project.AllOrderMVP.presenter.Presenter;
//import com.example.small_project.AllOrderMVP.view.IView;
//import com.example.small_project.R;
//import com.example.small_project.adapter.AllOrderAdapter;
//import com.example.small_project.bean.AllOrderBean;
//import com.jcodecraeer.xrecyclerview.XRecyclerView;
//
//import java.util.HashMap;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.Unbinder;
//
//public class AllOrderFrag extends Fragment implements IView {
//    @BindView(R.id.orderrexy)
//    XRecyclerView orderrexy;
//    Unbinder unbinder;
//    private SharedPreferences login;
//    private int userId;
//    private String sessionId;
//    private HashMap<String, Object> map;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View inflate = inflater.inflate(R.layout.allorder, container, false);
//        login = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
//        map = new HashMap<>();
//        userId = login.getInt("userId", 0);
//        sessionId = login.getString("sessionId", "");
//        map.put("userId", userId);
//        map.put("sessionId", sessionId);
//        Presenter presenter = new Presenter(this);
//        presenter.getorder(0,map);
//        unbinder = ButterKnife.bind(this, inflate);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
//        orderrexy.setLayoutManager(linearLayoutManager);
//        return inflate;
//    }
//
//    @Override
//    public void getPreorder(AllOrderBean allOrderBean) {
//        AllOrderAdapter allOrderAdapter = new AllOrderAdapter(getActivity(), allOrderBean);
//        orderrexy.setAdapter(allOrderAdapter);
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }
//}
