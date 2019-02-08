package com.hdd.androidreview.customView;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.hdd.androidreview.R;
import com.hdd.androidreview.utils.AppUtil;

public class EventActivity extends AppCompatActivity {
    private String TAG = "EventActivity";
    private TestView mTEST_EventACT;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        mTEST_EventACT = findViewById(R.id.mTEST_EventACT);
//        mTEST_EventACT.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i(TAG, "TestView组件setOnClickListener()事件的:onClick()");
//            }
//        });
//        mTEST_EventACT.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                AppUtil.eventLog(event, TAG, "TestView组件setOnTouchListener事件的:onTouch()");
//                return false;
//            }
//        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        AppUtil.eventLog(ev, TAG, "dispatchTouchEvent");

//        return false;
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        AppUtil.eventLog(event, TAG, "onTouchEvent");
//        return false;

        return super.onTouchEvent(event);
    }

}
