package com.hdd.androidreview.thread;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hdd.androidreview.R;

public class TestHandlerActivity extends Activity implements View.OnClickListener {
    private TextView mTV_testHandler;
    private Button mBnt_Post, mBnt_Send, mBnt_Callback;
    Looper mLooper;
    MessageQueue mQ;
    @SuppressLint("HandlerLeak")
    private Handler mHanlder = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 200:
                    mTV_testHandler.setText((String) msg.obj);
                    break;
            }
            super.handleMessage(msg);
        }
    };

    Handler callbackHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 202:
                    mTV_testHandler.setText((String) msg.obj);

                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_handler);
        mTV_testHandler = (TextView) findViewById(R.id.mTV_testHandler);
        mBnt_Post = (Button) findViewById(R.id.mBnt_Post);
        mBnt_Send = (Button) findViewById(R.id.mBnt_Send);
        mBnt_Callback = (Button) findViewById(R.id.mBnt_Callback);
        mBnt_Post.setOnClickListener(this);
        mBnt_Send.setOnClickListener(this);
        mBnt_Callback.setOnClickListener(this);


    }


    public void getThead() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                Looper.prepare();
                Handler handler1 = new Handler();
                //一定要开启循环
                Looper.loop();

                Looper looper = Looper.getMainLooper();
                Handler handler2 = new Handler(looper);

                //获取当前线程的Looper
//                Looper looper = Looper.myLooper();
//                //获取主线的Looper
//                Looper mainLooper = Looper.getMainLooper();
//                //清除全部消息
//                looper.quit();
//                //清除延迟消息
//                looper.quitSafely();
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mBnt_Post:

                mHanlder.post(new Runnable() {
                    @Override
                    public void run() {
                        mTV_testHandler.setText("这是Post发送的消息");
                    }
                });

                break;
            case R.id.mBnt_Send:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Message msg = new Message();
                                msg.what = 200;
                                msg.obj = "这是Send发送的消息";
                                mHanlder.sendMessage(msg);
                            }
                        }).start();
                    }
                }).start();
                break;
            case R.id.mBnt_Callback:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Message msg = new Message();
                                msg.what = 202;
                                msg.obj = "这是Callback发送的消息";
                                callbackHandler.sendMessage(msg);
                            }
                        }).start();
                    }
                }).start();
                break;
        }
    }


}
