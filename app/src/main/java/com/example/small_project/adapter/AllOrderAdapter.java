package com.example.small_project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.small_project.R;
import com.example.small_project.bean.AllOrderBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class AllOrderAdapter extends XRecyclerView.Adapter<AllOrderAdapter.MyHodel> {

    private final Context context;
    private final AllOrderBean allOrderBean;
    private List<AllOrderBean.OrderListBean> orderList;

    public AllOrderAdapter(Context context, AllOrderBean allOrderBean) {
        this.context = context;
        this.allOrderBean = allOrderBean;
    }

    @NonNull
    @Override
    public MyHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.orderitem, null);
        MyHodel myHodel = new MyHodel(inflate);
        return myHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHodel myHodel, int i) {
        orderList = allOrderBean.getOrderList();
        myHodel.dingdan.setText(orderList.get(i).getOrderId());

        List<AllOrderBean.OrderListBean.DetailListBean> detailList = orderList.get(i).getDetailList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        myHodel.rexyitem.setLayoutManager(linearLayoutManager);
        myHodel.rexyitem.setAdapter(new RexyItemAdapter(context,detailList));
    }

    @Override
    public int getItemCount() {
        return allOrderBean.getOrderList().size();
    }

    public class MyHodel extends RecyclerView.ViewHolder {
        private final TextView dingdan;
        private final TextView time;
        private final TextView sum;
        private final RecyclerView rexyitem;
        public MyHodel(@NonNull View itemView) {
            super(itemView);
            dingdan = itemView.findViewById(R.id.allorder_txt_dindan);
            time = itemView.findViewById(R.id.allorder_txt_date);
            sum = itemView.findViewById(R.id.allorder_txt_sum);
            rexyitem = itemView.findViewById(R.id.allorder_recy_item);
        }
    }
}
