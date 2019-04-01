package com.example.small_project.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.small_project.R;
import com.example.small_project.bean.CircleBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.CicleHolder> {
    private final Context context;
    private final LayoutInflater layoutInflater;
    private List<CircleBean.ResultBean> list;

    public CircleAdapter(Context context, List<CircleBean.ResultBean> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public CicleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.circleitem, parent, false);
        CicleHolder cicleHolder = new CicleHolder(view);
        return cicleHolder;
    }

    @Override
    public void onBindViewHolder(CicleHolder holder, int position) {
        holder.nc.setText(list.get(position).getNickName());
        holder.nr.setText(list.get(position).getContent());
        holder.fkt.setImageURI(list.get(position).getImage());
        holder.head.setImageURI(list.get(position).getHeadPic());
        //long类型转换成data类型
        Long aLong = Long.valueOf(list.get(position).getCreateTime());
        String format = new SimpleDateFormat("yyyy-MM-dd hh:ss").format(new Date(aLong));
        holder.circletime.setText(format);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


   public class CicleHolder extends RecyclerView.ViewHolder{

       private final TextView nc;
       private final TextView nr;
       private final SimpleDraweeView head;
       private final SimpleDraweeView fkt;
       private final TextView circletime;


       public CicleHolder(View itemView) {
           super(itemView);
           head = itemView.findViewById(R.id.head);
           nc = itemView.findViewById(R.id.nc);
           nr = itemView.findViewById(R.id.nr);
           fkt = itemView.findViewById(R.id.fkt);
           circletime = itemView.findViewById(R.id.circletime);
       }
   }
}
