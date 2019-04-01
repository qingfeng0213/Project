package com.example.small_project.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.small_project.MyView.CustomAddView;
import com.example.small_project.MyView.CustomView;
import com.example.small_project.R;
import com.example.small_project.bean.FIndShopBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.MyHodel> {
    private Context context;
    private  List<FIndShopBean.ResultBean> list;

    public ShopAdapter(Context context, List<FIndShopBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    //接收全选按钮状态
    public void getCheckBoxAllState(Boolean checked){
        for (int i=0;i<list.size();i++){
            list.get(i).setCheck(checked);
        }
        //刷新适配器
        notifyDataSetChanged();
    }

    @Override
    public MyHodel onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.shopcaritem, null);
        MyHodel myHodel = new MyHodel(inflate);
        return myHodel;
    }

    @Override
    public void onBindViewHolder(MyHodel holder, final int position) {
        holder.nei.setText(list.get(position).getCommodityName());
        holder.price.setText("￥："+list.get(position).getPrice());
        holder.imageView.setImageURI(list.get(position).getPic());
        //设置自定义View里的Edit
        holder.mCustomShopCarPrice.setData(this, list, position);
        holder.mCustomShopCarPrice.setOnCallBack(new CustomAddView.CallBackListener() {
            @Override
            public void callBack() {
                if (mShopCallBackListener != null) {
                    mShopCallBackListener.callBack();
                }
            }
        });
        //根据我记录的状态，改变勾选
        holder.xuan2.setChecked(list.get(position).isCheck());
        //商品的跟商家的有所不同，商品添加了选中改变的监听
        holder.xuan2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.get(position).setCheck(isChecked);
                for (int j=0;j<list.size();j++){
                    if (!list.get(j).isCheck()){
                        listener.onCheck(false);
                        setAllPriceListenter.setAllPrice(list);
                        return;
                    }
                }

                listener.onCheck(true);
                setAllPriceListenter.setAllPrice(list);

            }
        });

        holder.xuan2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.get(position).setCheck(isChecked);
                for (int j=0;j<list.size();j++){
                    if (!list.get(j).isCheck()){
                        listener.onCheck(false);
                        setAllPriceListenter.setAllPrice(list);
                        return;
                    }
                }

                listener.onCheck(true);
                setAllPriceListenter.setAllPrice(list);

            }
        });
        holder.mCustomShopCarPrice.setData(this,list,position);
        setAllPriceListenter.setAllPrice(list);
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHodel extends RecyclerView.ViewHolder {
        private final CheckBox xuan2;
        private final SimpleDraweeView imageView;
        private final TextView nei;
        private final TextView price;
        private final CustomAddView mCustomShopCarPrice;

        public MyHodel(View itemView) {
            super(itemView);
            xuan2 = itemView.findViewById(R.id.xuan2);
            imageView = itemView.findViewById(R.id.imageView);
            nei = itemView.findViewById(R.id.nei);
            price = itemView.findViewById(R.id.price);
            mCustomShopCarPrice = itemView.findViewById(R.id.custom_product_counter);
        }
    }

    //定义CheckBox点击的接口回调
    private OnCheckListener listener;

    public interface OnCheckListener {
        void onCheck(boolean isCheck);
    }
    public void setOnCheckListener(OnCheckListener listener) {
        this.listener = listener;
    }

    //计算总价
    setAllPriceListenter setAllPriceListenter;

    public interface setAllPriceListenter{
        void setAllPrice(List<FIndShopBean.ResultBean> list);
    }
    public void setSetAllPriceListenter(ShopAdapter.setAllPriceListenter setAllPriceListenter) {
        this.setAllPriceListenter = setAllPriceListenter;
    }
    //加减
    private ShopCallBackListener mShopCallBackListener;

    public void setListener(ShopCallBackListener listener) {
        this.mShopCallBackListener = listener;
    }

    public interface ShopCallBackListener {
        void callBack();
    }
}
