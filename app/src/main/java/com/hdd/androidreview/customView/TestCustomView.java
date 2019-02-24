package com.hdd.androidreview.customView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hdd.androidreview.R;


public class TestCustomView extends Activity {
    private final String TAG = "TestCustomView";
    FlowLayout mFlowL_testCustom;
    Handler mHandler = new Handler();
        //##3
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_customview);
        mFlowL_testCustom = (FlowLayout) findViewById(R.id.mFlowL_testCustom);


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
        //通过view的post()方法获取View的宽高
//        mFlowL_testCustom.post(new Runnable() {
//            @Override
//            public void run() {
//                int width = mFlowL_testCustom.getMeasuredWidth();
//                int height = mFlowL_testCustom.getMeasuredHeight();
//                Log.i(TAG, "mFlowL_testCustom.post()测量的宽为:" + width + "高为:" + height);
//            }
//        });
        //通过ViewTreeObserver获取到View的宽高

        ViewTreeObserver treeObserver=mFlowL_testCustom.getViewTreeObserver();
        treeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = mFlowL_testCustom.getMeasuredWidth();
                int height = mFlowL_testCustom.getMeasuredHeight();
                Log.i(TAG, "addOnGlobalLayoutListener()测量的宽为:" + width + "高为:" + height);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        int width = mFlowL_testCustom.getMeasuredWidth();
        int height = mFlowL_testCustom.getMeasuredHeight();
        Log.i(TAG, "onResume()测量的宽为:" + width + "高为:" + height);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int width = mFlowL_testCustom.getMeasuredWidth();
        int height = mFlowL_testCustom.getMeasuredHeight();
        Log.i(TAG, "onWindowFocusChanged()测量的宽为:" + width + "高为:" + height);
    }
}
