package com.u9time.butterknife;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_1)
    Button btn1;
    @BindView(R.id.btn_2)
    Button btn2;
    @BindView(R.id.btn_3)
    Button btn3;
    @BindView(R.id.btn_4)
    Button btn4;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.vp)
    ViewPager vp;

    /**
     * 有很多的额绑定,可以是字符串,数组,颜色,图片等等.
     */
    @BindString(R.string.hello_blank_fragment)
    String hello_blank_fragment;
    @BindArray(R.array.city)
    String[] city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            fragmentArrayList.add(new BlankFragment());
        }
        vp.setAdapter(new VpAdapter(getSupportFragmentManager(), fragmentArrayList));
    }

    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                break;
            case R.id.btn_2:
                break;
            case R.id.btn_3:
                break;
            case R.id.btn_4:
                break;
            case R.id.ll:
                break;
        }
    }
}
