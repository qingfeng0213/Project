package com.example.small_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.small_project.DetailsActivity;
import com.example.small_project.R;
import com.example.small_project.bean.MyBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyHodel> {


    private final Context context;
    private final List<MyBean.ResultBean.RxxpBean.CommodityListBean> toplist;


    public Myadapter(Context context, List<MyBean.ResultBean.RxxpBean.CommodityListBean> list) {
        this.context = context;
        this.toplist = list;
    }

    @Override
    public Myadapter.MyHodel onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item, null);
        MyHodel myHodel = new MyHodel(view);
        return myHodel;
    }

    @Override
    public void onBindViewHolder(Myadapter.MyHodel holder, final int position) {
        holder.textView1.setText(toplist.get(position).getCommodityName());
        holder.textView2.setText("¥"+toplist.get(position).getPrice()+"");
        holder.imageview.setImageURI(toplist.get(position).getMasterPic());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EventBus.getDefault().postSticky(toplist.get(position).getCommodityId());
                context.startActivity(new Intent(context,DetailsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return toplist.size();
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
//    public OnItemClickListener onItemClickListener;
//    public interface OnItemClickListener{
//        void onItemClick(String i);
//    }
//
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }
}
