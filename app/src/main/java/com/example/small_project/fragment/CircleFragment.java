package com.example.small_project.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.small_project.Api.ApiService;
import com.example.small_project.Cicle.presenter.CiclePresenter;
import com.example.small_project.Cicle.view.ICicleView;
import com.example.small_project.R;
import com.example.small_project.adapter.CircleAdapter;
import com.example.small_project.bean.CircleBean;
import com.example.small_project.network.MyUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class CircleFragment extends Fragment implements ICicleView {

    private XRecyclerView xrecy;
    private LinearLayoutManager linearLayoutManager;
    private View view;
    private List<CircleBean.ResultBean> list;
    int i = 1;
    private ImageView network_image;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.circlefrag, container, false);
        xrecy = view.findViewById(R.id.xrecy);
        network_image = view.findViewById(R.id.network_image);
        //布局管理器
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        xrecy.setLayoutManager(linearLayoutManager);



        final CiclePresenter ciclePresenter = new CiclePresenter(this);
        ciclePresenter.getCirclePreDate(ApiService.CICLE_URL+"?page="+i+"&count="+20);


        xrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
            i=1;
            //判断网络状态
                if(!MyUtils.isConn(getActivity())){
                    MyUtils.setNetworkMethod(getActivity());
                    ciclePresenter.getCirclePreDate(ApiService.CICLE_URL+"?page="+i+"&count="+20);
                    Toast.makeText(getActivity(), "无网", Toast.LENGTH_SHORT).show();
                    network_image.setVisibility(View.VISIBLE);
                    ObjectAnimator rotation = ObjectAnimator.ofFloat(network_image, "rotation", 1f, 1080f);
                    rotation.setDuration(6000);
                    //rotation.setInterpolator(new BounceInterpolator());
                    rotation.setInterpolator(new LinearInterpolator());
                    //rotation.setRepeatMode();
                    rotation.start();
                }else {
                    network_image.setVisibility(View.GONE);
                }
            xrecy.refreshComplete();
            }

            @Override
            public void onLoadMore() {
            i++;
                ciclePresenter.getCirclePreDate(ApiService.CICLE_URL+"?page="+i+"&count="+20);
                xrecy.loadMoreComplete();
            }
        });

        return view;
    }


    @Override
    public void getcircleViewDate(CircleBean circleBean) {
        list = circleBean.getResult();
        CircleAdapter circleAdapter = new CircleAdapter(getActivity(), list);
        xrecy.setAdapter(circleAdapter);
    }
}
