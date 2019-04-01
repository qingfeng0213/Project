package com.example.small_project;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.small_project.fragment.CircleFragment;
import com.example.small_project.fragment.HomeFragment;
import com.example.small_project.fragment.MyFragment;
import com.example.small_project.fragment.MyListFragment;
import com.example.small_project.fragment.ShopFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.frag)
    FrameLayout frag;
    @BindView(R.id.btn1)
    RadioButton btn1;
    @BindView(R.id.btn2)
    RadioButton btn2;
    @BindView(R.id.btn3)
    RadioButton btn3;
    @BindView(R.id.btn4)
    RadioButton btn4;
    @BindView(R.id.btn5)
    RadioButton btn5;
    @BindView(R.id.radiogroup)
    RadioGroup radiogroup;

    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private CircleFragment circleFragment;
    private ShopFragment shopFragment;
    private MyListFragment myListFragment;
    private MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        radiogroup.check(radiogroup.getChildAt(0).getId());


        fragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        homeFragment = new HomeFragment();
        circleFragment = new CircleFragment();
        shopFragment = new ShopFragment();
        myListFragment = new MyListFragment();
        myFragment = new MyFragment();
        transaction.add(R.id.frag, homeFragment);
        transaction.add(R.id.frag, circleFragment);
        transaction.add(R.id.frag, shopFragment);
        transaction.add(R.id.frag, myListFragment);
        transaction.add(R.id.frag, myFragment);
        transaction.show(homeFragment).hide(circleFragment).hide(shopFragment).hide(myListFragment).hide(myFragment);
        transaction.commit();

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                switch (checkedId) {
                    case R.id.btn1:
                        beginTransaction.show(homeFragment).hide(circleFragment).hide(shopFragment).hide(myListFragment).hide(myFragment);
                        break;
                    case R.id.btn2:
                        beginTransaction.show(circleFragment).hide(homeFragment).hide(shopFragment).hide(myListFragment).hide(myFragment);
                        break;
                    case R.id.btn3:
                        beginTransaction.show(shopFragment).hide(homeFragment).hide(circleFragment).hide(myListFragment).hide(myFragment);
                        break;
                    case R.id.btn4:
                        beginTransaction.show(myListFragment).hide(homeFragment).hide(circleFragment).hide(shopFragment).hide(myFragment);
                        break;
                    case R.id.btn5:
                        beginTransaction.show(myFragment).hide(homeFragment).hide(circleFragment).hide(shopFragment).hide(myListFragment);
                        break;


                    default:
                        break;
                }
                beginTransaction.commit();
            }
        });
    }


}
