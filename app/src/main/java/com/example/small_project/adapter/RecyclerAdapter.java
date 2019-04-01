package com.example.small_project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.small_project.R;
import com.example.small_project.bean.BannerBean;
import com.example.small_project.bean.MyBean;
import com.recker.flybanner.FlyBanner;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int RXXP_VIEW = 1;
    private final int PZSH_VIEW = 2;
    private final int MLSS_VIEW = 3;
    private final MyBean.ResultBean list;
    private  Context context;

    private  List<BannerBean.ResultBean> bannerlist;


    public RecyclerAdapter(Context context, MyBean.ResultBean list, List<BannerBean.ResultBean> bannerlist) {
        this.context = context;
        this.list = list;
        this.bannerlist = bannerlist;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case RXXP_VIEW:
                return RXXP_VIEW;
            case PZSH_VIEW:
                return PZSH_VIEW;
            case MLSS_VIEW:
                return MLSS_VIEW;
        }
        return 4;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view;
        if (RXXP_VIEW == i) {
            view = View.inflate(context, R.layout.recycler_sh, null);
            return new RecyclerHolder1(view);
        } else if (PZSH_VIEW == i) {

            view = View.inflate(context, R.layout.recycler_zh, null);
            return new RecyclerHolder2(view);
        } else if (MLSS_VIEW == i) {

            view = View.inflate(context, R.layout.recycler_x, null);
            return new RecyclerHolder3(view);
        } else {
            view = View.inflate(context, R.layout.item_banner, null);
            return new RecyclerHolder0(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecyclerHolder0) {
            ArrayList<String> imagelist = new ArrayList<>();
            for (int j =0; j<bannerlist.size(); j++) {
                imagelist.add(bannerlist.get(j).getImageUrl());
            }
            ((RecyclerHolder0) holder).flyBanner.setImagesUrl(imagelist);
        }
        if (holder instanceof RecyclerHolder1) {
            List<MyBean.ResultBean.RxxpBean.CommodityListBean> list1 = list.getRxxp().getCommodityList();
            ((RecyclerHolder1) holder).toprecy.setLayoutManager(new GridLayoutManager(context,3));
            Myadapter myadapter = new Myadapter(context, list1);
            ((RecyclerHolder1) holder).toprecy.setAdapter(myadapter);
        }
        if (holder instanceof RecyclerHolder2) {
            List<MyBean.ResultBean.MlssBean.CommodityListBeanXX> list1 = list.getMlss().getCommodityList();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
            ((RecyclerHolder2) holder).centerrecy.setLayoutManager(linearLayoutManager);
            ((RecyclerHolder2) holder).centerrecy.setAdapter(new Twoadapter(context,list1));
        }
        if (holder instanceof RecyclerHolder3) {
            List<MyBean.ResultBean.PzshBean.CommodityListBeanX> list1 = list.getPzsh().getCommodityList();
            ((RecyclerHolder3) holder).lastrecy.setLayoutManager(new GridLayoutManager(context,2));
            ((RecyclerHolder3) holder).lastrecy.setAdapter(new Threeadapter(context,list1));
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class RecyclerHolder0 extends RecyclerView.ViewHolder {

        private FlyBanner flyBanner;

        public RecyclerHolder0(@NonNull View itemView) {
            super(itemView);
            flyBanner = itemView.findViewById(R.id.flybanner);
        }
    }
    class RecyclerHolder1 extends RecyclerView.ViewHolder {

        private RecyclerView toprecy;

        public RecyclerHolder1(@NonNull View itemView) {
            super(itemView);
            toprecy = itemView.findViewById(R.id.toprecy);
        }
    }

    class RecyclerHolder2 extends RecyclerView.ViewHolder {

        private RecyclerView centerrecy;

        public RecyclerHolder2(@NonNull View itemView) {
            super(itemView);
            centerrecy = itemView.findViewById(R.id.centerrecy);
        }
    }

    class RecyclerHolder3 extends RecyclerView.ViewHolder {

        private RecyclerView lastrecy;

        public RecyclerHolder3(@NonNull View itemView) {
            super(itemView);
            lastrecy = itemView.findViewById(R.id.lastrecy);
        }
    }
}
