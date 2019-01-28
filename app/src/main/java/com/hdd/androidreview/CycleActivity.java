package com.hdd.androidreview;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CycleActivity extends AppCompatActivity {
    private static final String TAG = "CycleActivity";
    private final int CHANGE_TEXT = 200;

    private int onNewIntentIndex = 0;
    private int onCreateIndex = 0;
    private int onRestartIndex = 0;
    private int onPauseIndex = 0;
    private Button mBnt_cycle;
    private TextView mTV_Cycle;
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
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.i(TAG, "==onRestoreInstanceState()==");
        methdName.append("\n~~~onRestoreInstanceState()~~~\n");
        mHandler.sendEmptyMessage(CHANGE_TEXT);
    }

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
        mBnt_cycle = findViewById(R.id.mBnt_cycle);
        mTV_Cycle = findViewById(R.id.mTV_Cycle);
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
}
