package com.example.small_project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.small_project.MyView.CustomAddView;
import com.example.small_project.R;
import com.example.small_project.bean.FIndShopBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;



public class CreateBillAdapter extends RecyclerView.Adapter<CreateBillAdapter.MyHodel> {
    List<FIndShopBean.ResultBean> list2;
    Context context;
    public CreateBillAdapter(List<FIndShopBean.ResultBean> list2, Context context) {
        this.context= context;
        this.list2 = list2;
    }

    @NonNull
    @Override
    public MyHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.shopcaritem, null);
        MyHodel myHodel = new MyHodel(inflate);
        return myHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHodel myHodel, int i) {
        myHodel.nei.setText(list2.get(i).getCommodityName());
        myHodel.price.setText("￥："+list2.get(i).getPrice()+"");
        myHodel.imageView.setImageURI(list2.get(i).getPic());
    }

    @Override
    public int getItemCount() {
        return list2.size();
    }

    public class MyHodel extends RecyclerView.ViewHolder {
        private final CheckBox xuan2;
        private final SimpleDraweeView imageView;
        private final TextView nei;
        private final TextView price;
        private final CustomAddView mCustomShopCarPrice;
        public MyHodel(@NonNull View itemView) {
            super(itemView);
            xuan2 = itemView.findViewById(R.id.xuan2);
            imageView = itemView.findViewById(R.id.imageView);
            nei = itemView.findViewById(R.id.nei);
            price = itemView.findViewById(R.id.price);
            mCustomShopCarPrice = itemView.findViewById(R.id.custom_product_counter);
        }
    }
}
