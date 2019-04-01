package com.example.small_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.small_project.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class ShouAdapter extends RecyclerView.Adapter<ShouAdapter.MyHodel> {
    private final Context context;
    private final List<NewAddressBean.ResultBean> list;
    public ShouAdapter(Context context, List<NewAddressBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyHodel onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.query_site_item, null);
        MyHodel myHodel = new MyHodel(inflate);
        return myHodel;
    }

    @Override
    public void onBindViewHolder(MyHodel holder, int position) {
        holder.name.setText(list.get(position).getRealName());
        holder.phone.setText(list.get(position).getPhone());
        holder.count.setText(list.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHodel extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView phone;
        private final TextView count;

        public MyHodel(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.site_show_name);
            phone = itemView.findViewById(R.id.site_show_phone);
            count = itemView.findViewById(R.id.site_show_count);
        }
    }
    public OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(String i);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
