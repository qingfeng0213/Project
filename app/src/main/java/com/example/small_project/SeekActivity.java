package com.example.small_project;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.small_project.MyView.CustomSearchView;
import com.example.small_project.MyView.XCFlowLayout;
import com.example.small_project.adapter.SeekAdapter;
import com.example.small_project.bean.SouBean;
import com.example.small_project.seekmvp.presenter.SeekPresenter;
import com.example.small_project.seekmvp.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeekActivity extends AppCompatActivity implements IView {

    @BindView(R.id.xrecyview)
    XRecyclerView xrecyview;
    @BindView(R.id.tishi)
    TextView tishi;
    @BindView(R.id.wusou)
    ImageView wusou;
    @BindView(R.id.errorSerch)
    LinearLayout errorSerch;
    @BindView(R.id.myedit)
    CustomSearchView myedit;
    private SeekPresenter seekPresenter;
    private ArrayList<String> strings = new ArrayList<>();
    private XCFlowLayout mFlowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek);
        ButterKnife.bind(this);
        //布局管理器
        xrecyview.setLayoutManager(new GridLayoutManager(SeekActivity.this, 2));
        //获取P层
        seekPresenter = new SeekPresenter(this);
        //自定义搜索框的点击事件
        myedit.setOnEditSearchClickListener(new CustomSearchView.setOnBtnSearchClickListener() {
            @Override
            public void setOnBtnSearchClick(String searchdata) {
                seekPresenter.getseekdata(searchdata);
                //显示无搜索的图片
                xrecyview.setVisibility(View.VISIBLE);
                //隐藏无搜索的图片
                errorSerch.setVisibility(View.GONE);
                if (strings.size()>0) {
                    strings.remove(0);
                }
                //将输入框内的值添加进集合
                strings.add(searchdata);

                if (strings != null) {
                    mFlowLayout = (XCFlowLayout) findViewById(R.id.flowlayout);
                    ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    lp.leftMargin = 5;
                    lp.rightMargin = 5;
                    lp.topMargin = 5;
                    lp.bottomMargin = 5;
                    for (int i = 0; i < strings.size(); i++) {
                        final TextView view = new TextView(SeekActivity.this);
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (v != null) {
                                    seekPresenter.getseekdata(view.getText().toString());
                                }
                            }
                        });
                        view.setText(strings.get(i));
                        //字体颜色
                        view.setTextColor(Color.WHITE);
                        //TextView样式
                        view.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_bg));
                        mFlowLayout.addView(view, lp);
                    }
                }
                Toast.makeText(SeekActivity.this, searchdata, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void getPreData(SouBean myBean) {
        List<SouBean.ResultBean> list = myBean.getResult();
        SeekAdapter seekAdapter = new SeekAdapter(this, list);
        xrecyview.setAdapter(seekAdapter);
    }

    @Override
    public void getjump() {

        xrecyview.setVisibility(View.GONE);
        errorSerch.setVisibility(View.VISIBLE);
    }
}
