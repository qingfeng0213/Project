package com.example.small_project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.small_project.R;
import com.example.small_project.bean.AllOrderBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class RexyItemAdapter extends RecyclerView.Adapter<RexyItemAdapter.MyHodel> {

    private final Context context;
    private final List<AllOrderBean.OrderListBean.DetailListBean> detailList;

    public RexyItemAdapter(Context context, List<AllOrderBean.OrderListBean.DetailListBean> detailList) {
        this.context = context;
        this.detailList = detailList;
    }

    @NonNull
    @Override
    public MyHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.orderrecyitem, null);
        MyHodel myHodel = new MyHodel(inflate);
        return myHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHodel myHodel, int i) {
        myHodel.title.setText(detailList.get(i).getCommodityName());
        myHodel.price.setText(detailList.get(i).getCommodityPrice()+"");
        String commodityPic = detailList.get(i).getCommodityPic();
        String[] split = commodityPic.split("\\,");
        myHodel.image.setImageURI(split[0]);
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    public class MyHodel extends RecyclerView.ViewHolder {

        private final SimpleDraweeView image;
        private final TextView title;
        private final TextView price;

        public MyHodel(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.order_sdv_image);
            title = itemView.findViewById(R.id.order_txt_title);
            price = itemView.findViewById(R.id.order_txt_price);
        }
    }
}
