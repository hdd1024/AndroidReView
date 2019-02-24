package com.hdd.androidreview;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.hdd.androidreview.utils.AppUtil;

public abstract class PattermBaseActivity extends Activity {
    protected  String TAG;
    private int onNewIntentIndex = 0;
    private int onCreateIndex = 0;
    private int onRestartIndex = 0;
    private int onPauseIndex = 0;
    protected TextView mTV_content;
    protected StringBuilder tvContentBuilder;
    protected final int CHANGE_TEXT = 200;

    protected Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case CHANGE_TEXT:
                    if (mTV_content != null)
                        mTV_content.setText(tvContentBuilder);
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "==onNewIntent()==");
        tvContentBuilder.append("\n嘿嘿你有啥意图？？:" + onNewIntentIndex);
        tvContentBuilder.append("\n***onNewIntent()***\n");
        mHandler.sendEmptyMessage(CHANGE_TEXT);
        onNewIntentIndex++;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        Log.i(TAG, "==onCreate()==");
        tvContentBuilder = new StringBuilder("启动模式页面:\n");
        tvContentBuilder.append("\n页面创建完毕:" + onCreateIndex);
        tvContentBuilder.append("\n***onCreate()***\n");
        mHandler.sendEmptyMessage(CHANGE_TEXT);
        onCreateIndex++;
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "==onRestart()==");
        tvContentBuilder.append("\n页面马上返回前台:" + onRestartIndex);
        tvContentBuilder.append("\n---onRestart()---\n");
        mHandler.sendEmptyMessage(CHANGE_TEXT);
        onRestartIndex++;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "==onStart()==");
        tvContentBuilder.append("\n###onStart()###\n");
        mHandler.sendEmptyMessage(CHANGE_TEXT);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.i(TAG, "==onRestoreInstanceState()==");
        tvContentBuilder.append("\n~~~onRestoreInstanceState()~~~\n");
        mHandler.sendEmptyMessage(CHANGE_TEXT);
        String test1 = savedInstanceState.getString("test1");
        //onRestoreInstanceState()恢复的数据是有价值的，可以不用做非空判断。但是onCreate()要做非空判断
        mTV_content.setText(test1);
        Log.i(TAG, "onRestoreInstanceState():" + test1);

    }

    @Override
    protected void onResume() {
        super.onResume();
        StringBuilder topActivity = AppUtil.getTaskTopActivity(getApplicationContext());
        tvContentBuilder.append("\n栈顶Activy为：" + topActivity + "\n");
        Log.i(TAG, "==onResume()==");
        tvContentBuilder.append("\n&&&onResume()&&&\n");
        mHandler.sendEmptyMessage(CHANGE_TEXT);

    }


    @Override
    protected void onPause() {
        super.onPause();

        Log.i(TAG, "==onPause()==");
        tvContentBuilder.append("\n页面失去焦点了:" + onPauseIndex);
        tvContentBuilder.append("\n&&&onPause()&&&\n");
        mHandler.sendEmptyMessage(CHANGE_TEXT);
        onPauseIndex++;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "==onSaveInstanceState()==");
        tvContentBuilder.append("\n~~~onSaveInstanceState()~~~\n");
        mHandler.sendEmptyMessage(CHANGE_TEXT);
        outState.putString("test1", "这是activity销毁前保存的数据");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "==onStop()==");
        tvContentBuilder.append("\n###onStop()###\n");
        mHandler.sendEmptyMessage(CHANGE_TEXT);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "==onDestroy()==");
        tvContentBuilder.append("\n***onDestroy()***\n");
        mHandler.sendEmptyMessage(CHANGE_TEXT);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "onConfigurationChanged配置信息:" + newConfig.orientation);
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        Log.i(TAG, "onUserInteraction");
    }
    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Log.i(TAG, "onUserLeaveHint");
    }

}
