package com.hdd.androidreview;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hdd.androidreview.Patterm.PattermActivity;

public class CycleActivity extends Activity {
    private static final String TAG = "CycleActivity";
    private final int CHANGE_TEXT = 200;

    private int onNewIntentIndex = 0;
    private int onCreateIndex = 0;
    private int onRestartIndex = 0;
    private int onPauseIndex = 0;
    private Button mBnt_cycle;
    private TextView mTV_Cycle, mTV_test1;
    private EditText mEdt_test;
    private StringBuilder methdName = new StringBuilder("[开始]\n");//在单线程情况先，StringBuild的效率要高于StringBuffer
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case CHANGE_TEXT:
                    if (mTV_Cycle != null)
                        mTV_Cycle.setText(methdName);
                    break;
            }
            return false;
        }
    });


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "==onNewIntent()==");
        methdName.append("\n嘿嘿你有啥意图？？:" + onNewIntentIndex);
        methdName.append("\n***onNewIntent()***\n");
        mHandler.sendEmptyMessage(CHANGE_TEXT);
        onNewIntentIndex++;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle);
        mBnt_cycle = (Button) findViewById(R.id.mBnt_cycle);
        mTV_Cycle = (TextView) findViewById(R.id.mTV_Cycle);
        mTV_test1 = (TextView) findViewById(R.id.mTV_test1);
        mEdt_test = (EditText) findViewById(R.id.mEdt_test);
        mTV_test1.setText("这是系统默认帮我们恢复的数据.");
        Log.i(TAG, "==onCreate()==");

        mBnt_cycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PattermActivity.class);
                startActivity(intent);
            }
        });
        methdName.append("\n页面创建完毕:" + onCreateIndex);
        methdName.append("\n***onCreate()***\n");
        mHandler.sendEmptyMessage(CHANGE_TEXT);
        onCreateIndex++;
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "==onRestart()==");
        methdName.append("\n页面马上返回前台:" + onRestartIndex);
        methdName.append("\n---onRestart()---\n");
        mHandler.sendEmptyMessage(CHANGE_TEXT);
        onRestartIndex++;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "==onStart()==");
        methdName.append("\n###onStart()###\n");
        mHandler.sendEmptyMessage(CHANGE_TEXT);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.i(TAG, "==onRestoreInstanceState()==");
        methdName.append("\n~~~onRestoreInstanceState()~~~\n");
        mHandler.sendEmptyMessage(CHANGE_TEXT);
        String test1 = savedInstanceState.getString("test1");
        //onRestoreInstanceState()恢复的数据是有价值的，可以不用做非空判断。但是onCreate()要做非空判断
        mTV_test1.setText(test1);
        Log.i(TAG, "onRestoreInstanceState():" + test1);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "==onResume()==");
        methdName.append("\n&&&onResume()&&&\n");
        mHandler.sendEmptyMessage(CHANGE_TEXT);

    }


    @Override
    protected void onPause() {
        super.onPause();

        Log.i(TAG, "==onPause()==");
        methdName.append("\n页面失去焦点了:" + onPauseIndex);
        methdName.append("\n&&&onPause()&&&\n");
        mHandler.sendEmptyMessage(CHANGE_TEXT);
        onPauseIndex++;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "==onSaveInstanceState()==");
        methdName.append("\n~~~onSaveInstanceState()~~~\n");
        mHandler.sendEmptyMessage(CHANGE_TEXT);
        outState.putString("test1", "这是activity销毁前保存的数据");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "==onStop()==");
        methdName.append("\n###onStop()###\n");
        mHandler.sendEmptyMessage(CHANGE_TEXT);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "==onDestroy()==");
        methdName.append("\n***onDestroy()***\n");
        mHandler.sendEmptyMessage(CHANGE_TEXT);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "onConfigurationChanged配置信息:" + newConfig.orientation);
    }
}
