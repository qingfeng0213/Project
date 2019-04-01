package com.example.small_project.MyActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.small_project.GaiMVP.presenter.Presenter;
import com.example.small_project.GaiMVP.view.IView;
import com.example.small_project.R;
import com.example.small_project.adapter.ShopAdapter;
import com.example.small_project.bean.NameBean;
import com.example.small_project.bean.WordBean;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyDataActivity extends AppCompatActivity implements IView {

    @BindView(R.id.my_Hand)
    ImageView myHand;
    @BindView(R.id.My_Mame)
    TextView MyMame;
    @BindView(R.id.my_password)
    TextView myPassword;
    private Presenter presenter;

    private SharedPreferences login;
    private int userId;
    private String sessionId;
    private HashMap<String, Object> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_data);
        ButterKnife.bind(this);
        login = getSharedPreferences("Login", Context.MODE_PRIVATE);
        map = new HashMap<>();
        userId = login.getInt("userId", 0);
        sessionId = login.getString("sessionId", "");
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        presenter = new Presenter(this);
    }
    @OnClick({R.id.My_Mame, R.id.my_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.My_Mame:
                new MaterialDialog.Builder(this)
                        .title("修改昵称")
                        .content("昵称")
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .input("请输入要修改的昵称", null, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                String newname = input.toString();
                                MyMame.setText(newname);
                                presenter.getname(newname,map);

                            }
                        })
                        .positiveText("确定")
                        .show();
                break;
            case R.id.my_password:
                // 创建对话框构建器
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                // 获取布局
                View view2 = View.inflate(this, R.layout.updata_pwd, null);
                // 获取布局中的控件
                final EditText old_pwd = (EditText) view2.findViewById(R.id.old_pwd);
                final EditText new_pwd = (EditText) view2.findViewById(R.id.new_pwd);
                final Button updcacle = (Button) view2.findViewById(R.id.updcacle);
                final Button updok = (Button)  view2.findViewById(R.id.updok);
                // 设置弹框标题和图片
                builder.setTitle("Login").setView(view2);
                // 创建对话框
                final AlertDialog alertDialog = builder.create();
                updcacle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                    }
                });
                updok.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String oldpwd = old_pwd.getText().toString().trim();
                        String newpwd = new_pwd.getText().toString().trim();
                        presenter.getword(oldpwd,newpwd,map);
                        myPassword.setText(newpwd);
                        alertDialog.cancel();
                    }
                });
                alertDialog.show();

                break;
        }
    }
    @Override
    public void getPrename(NameBean nameBean) {
        String message = nameBean.getMessage();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getnewword(WordBean wordBean) {
        String message = wordBean.getMessage();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
