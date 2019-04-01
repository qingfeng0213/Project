package com.example.small_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.small_project.DetailsActivity;
import com.example.small_project.R;
import com.example.small_project.bean.MyBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class Threeadapter extends RecyclerView.Adapter<Threeadapter.MyHodel> {

    private final Context context;
    private final List<MyBean.ResultBean.PzshBean.CommodityListBeanX> lastlist;


    public Threeadapter(Context context, List<MyBean.ResultBean.PzshBean.CommodityListBeanX> list1) {
        this.context = context;
        this.lastlist = list1;
    }

    @Override
    public MyHodel onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item2, null);
        MyHodel myHodel = new MyHodel(view);
        return myHodel;
    }

    @Override
    public void onBindViewHolder(MyHodel holder, final int position) {
        holder.textView1.setText(lastlist.get(position).getCommodityName());
        holder.textView2.setText("¥"+lastlist.get(position).getPrice()+"");
        holder.imageview.setImageURI(lastlist.get(position).getMasterPic());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EventBus.getDefault().postSticky(lastlist.get(position).getCommodityId());
                context.startActivity(new Intent(context,DetailsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return lastlist.size();
    }

    public class MyHodel extends RecyclerView.ViewHolder {
        private final SimpleDraweeView imageview;
        private final TextView textView1;
        private final TextView textView2;
        public MyHodel(View itemView) {
            super(itemView);
            imageview = itemView.findViewById(R.id.imageView);
            textView1 = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
        }
    }

}
