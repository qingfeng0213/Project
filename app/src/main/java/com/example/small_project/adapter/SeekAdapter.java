package com.example.small_project.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.small_project.R;
import com.example.small_project.SeekActivity;
import com.example.small_project.bean.SouBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class SeekAdapter extends XRecyclerView.Adapter<SeekAdapter.MyHodel> {
    private final List<SouBean.ResultBean> list;
    private final Context context;

    public SeekAdapter(Context context, List<SouBean.ResultBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHodel onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.seekitem, null);
        MyHodel myHodel = new MyHodel(view);
        return myHodel;
    }

    @Override
    public void onBindViewHolder(MyHodel holder, int position) {
        holder.textView1.setText(list.get(position).getCommodityName());
        holder.textView2.setText("¥"+list.get(position).getPrice()+"");
        holder.imageview.setImageURI(list.get(position).getMasterPic());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHodel extends RecyclerView.ViewHolder {
        private final SimpleDraweeView imageview;
        private final TextView textView1;
        private final TextView textView2;
        public MyHodel(View itemView) {
            super(itemView);
            imageview = itemView.findViewById(R.id.imageview);
            textView1 = itemView.findViewById(R.id.textview1);
            textView2 = itemView.findViewById(R.id.textview2);
        }
    }
}
