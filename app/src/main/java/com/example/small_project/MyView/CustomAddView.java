package com.example.small_project.MyView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.small_project.R;
import com.example.small_project.adapter.ShopAdapter;
import com.example.small_project.bean.FIndShopBean;

import java.util.List;

public class CustomAddView extends RelativeLayout implements View.OnClickListener {
    Context mContext;
    private EditText mEditCar;
    private List<FIndShopBean.ResultBean> list;
    private ShopAdapter shopAdapter;
    private int i;

    public CustomAddView(Context context) {
        super(context);
        init(context);
    }

    public CustomAddView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }



    public CustomAddView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context) {
        this.mContext = context;
        View view = View.inflate(context, R.layout.shop_car_price_layout, null);
        ImageView addIamge = view.findViewById(R.id.shop_add_car);
        ImageView jianIamge = view.findViewById(R.id.jian_car);
        mEditCar = view.findViewById(R.id.edit_shop_car);
        addIamge.setOnClickListener(this);
        jianIamge.setOnClickListener(this);
        addView(view);
    }

    private int num;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shop_add_car:
                //改变数量，设置数量，改变对象内容，回调，局部刷新
                num++;

                mEditCar.setText(num + "");
                list.get(i).setCount(num);
                mCallBackListener.callBack();
                shopAdapter.notifyItemChanged(i);
                break;
            case R.id.jian_car:
                if (num > 1) {
                    num--;
                } else {
                    Toast.makeText(mContext, "商品数量不能小于1", Toast.LENGTH_LONG).show();
                }
                mEditCar.setText(num + "");
                list.get(i).setCount(num);
                mCallBackListener.callBack();
                shopAdapter.notifyItemChanged(i);
                break;
            default:
                break;
        }
    }
    public void setData(ShopAdapter shopAdapter, List<FIndShopBean.ResultBean> list, int position) {
        this.list = list;
        this.shopAdapter = shopAdapter;
        i = position;
        num = list.get(i).getCount();
        mEditCar.setText(this.num + "");
    }
    private CallBackListener mCallBackListener;

    public void setOnCallBack(CallBackListener listener) {
        this.mCallBackListener = listener;
    }

    public interface CallBackListener {
        void callBack();
    }
}
