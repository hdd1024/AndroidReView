package com.hdd.androidreview.memory;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

import com.hdd.androidreview.R;
import com.hdd.androidreview.utils.AppUtil;

import java.lang.ref.WeakReference;

public class LayoutMemoryActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LayoutMemoryActivity";
    TextView mTV_incloud_merge;
    Button mBnt_layoutMemory;
    ViewStub mVS_layoutMemory;
    private ObjectAnimator oAnimator;

    static class MyHanlder extends Handler {
        //弱引用
        WeakReference<Activity> mWeakRef;

        public MyHanlder(Activity activity) {
            mWeakRef = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    LayoutMemoryActivity activity = (LayoutMemoryActivity) mWeakRef.get();
                    activity.mTV_incloud_merge.setText((String) msg.obj);
                    break;
            }
        }
    }

    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 200:
                    mTV_incloud_merge.setText((String) msg.obj);
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layoyt_memory);
        mTV_incloud_merge = findViewById(R.id.mTV_incloud_merge);
        mBnt_layoutMemory = findViewById(R.id.mBnt_layoutMemory);
        mBnt_layoutMemory.setOnClickListener(this);
        mTV_incloud_merge.setText("include加merge标签");
        AppUtil.getTesLeak(getApplicationContext());
        TestLeak testLeak = new TestLeak();
        testLeak.sleepThread();
    }

    int viewstubS = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mBnt_layoutMemory:
                //ViewStub中布局显示出来后，他就会被从布局中移除，下面的findViewById()就会获取不到该组件。
                if (viewstubS == 0) {
                    mVS_layoutMemory = findViewById(R.id.mVS_layoutMemory);
                    mVS_layoutMemory.setVisibility(View.VISIBLE);
                    viewstubS++;
                }


                oAnimator = ObjectAnimator
                        .ofFloat(mBnt_layoutMemory, "rotation", 0, 360).setDuration(20000);
                oAnimator.start();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消动画
        oAnimator.cancel();
    }

    static class TestLeak {

        private void sleepThread() {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //睡10秒
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
