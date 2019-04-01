package com.example.small_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.small_project.GoodsLvThreeActivity;
import com.example.small_project.R;
import com.example.small_project.bean.GoodsLvTwoBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsLvTwoAdapter extends RecyclerView.Adapter<GoodsLvTwoAdapter.ViewHolder> {
    private List<GoodsLvTwoBean.ResultBean> list;
    private Context context;
    int images[] = {R.drawable.category_icon_coat,R.drawable.category_icon_skirt,R.drawable.category_icon_sweater,
            R.drawable.category_icon_hoodie ,R.drawable.category_icon_pants};
    public GoodsLvTwoAdapter(List<GoodsLvTwoBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lv_two_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
//        if (i>5){
//            viewHolder.lv_two_item_rbtn.setButtonDrawable(images[i-5]);
//        }else {
//            viewHolder.lv_two_item_rbtn.setButtonDrawable(images[i]);
//        }
        viewHolder.lv_two_item_text.setText(list.get(i).getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(list.get(i).getId());
                context.startActivity(new Intent(context, GoodsLvThreeActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

//        @BindView(R.id.lv_two_item_rbtn)
//        RadioButton lv_two_item_rbtn;
        @BindView(R.id.lv_two_item_text)
        TextView lv_two_item_text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
