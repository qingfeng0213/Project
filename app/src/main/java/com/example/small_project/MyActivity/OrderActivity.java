//package com.example.small_project.MyActivity;
//
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.RecyclerView;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.small_project.CreateMap.presenter.Presenter;
//import com.example.small_project.CreateMap.view.IView;
//import com.example.small_project.R;
//import com.example.small_project.bean.CreateBean;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;
//
//import java.util.HashMap;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class OrderActivity extends AppCompatActivity implements IView {
//
//    @BindView(R.id.address_name)
//    TextView addressName;
//    @BindView(R.id.address_phone)
//    TextView addressPhone;
//    @BindView(R.id.address_address)
//    TextView addressAddress;
//    @BindView(R.id.bottom_put)
//    ImageView bottomPut;
//    @BindView(R.id.shoppingcar_recycleView)
//    RecyclerView shoppingcarRecycleView;
//    @BindView(R.id.all_price)
//    TextView allPrice;
//    @BindView(R.id.sum_price_txt)
//    TextView sumPriceTxt;
//    @BindView(R.id.sum_price)
//    RelativeLayout sumPrice;
//    @BindView(R.id.layout_buttom)
//    RelativeLayout layoutButtom;
//    private SharedPreferences login;
//    private int userId;
//    private String sessionId;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_order);
//        EventBus.getDefault().register(this);
//        Presenter presenter = new Presenter(this);
//        ButterKnife.bind(this);
//        //这是登陆凭证,userId和sessionId
//        login = getSharedPreferences("Login", MODE_PRIVATE);
//        HashMap<String, Object> headmap = new HashMap<>();
//        userId = login.getInt("userId", 0);
//        sessionId = login.getString("sessionId", "");
//        headmap.put("userId", userId);
//        headmap.put("sessionId", sessionId);
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
//    public void getEventBus(String s) {
//        //收货地址ID
//        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
//    }
//    @Override
//    public void getPreNew(CreateBean createBean) {
//        String message = createBean.getMessage();
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//    }
//}
