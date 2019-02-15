package com.hdd.androidreview.customView;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hdd.androidreview.R;

public class TestCustomView extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_customview);

//        CircleView circleView = new CircleView(this);
//
//        RelativeLayout relativeLayout = new RelativeLayout(this);
//        relativeLayout.addView(circleView);
//        //获取内容栏布局
//        ViewGroup contentView= (ViewGroup) findViewById(android.R.id.content);
//        //获取当前Activity的更布局，及我们自己布局文件的根布局
//        LinearLayout linearLayout= (LinearLayout) contentView.getChildAt(0);
//
//        setContentView(relativeLayout);
    }

}
