package com.hdd.androidreview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class PattermActivity extends AppCompatActivity {
    private final String TAG = "PattermActivity";
    private StringBuilder tvContent = new StringBuilder("启动模式页面:\n");
    private TextView mTV_Patterm;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "==onNewIntent()==");

        tvContent.append("\n***onNewIntent()***\n");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patterm);
        mTV_Patterm = findViewById(R.id.mTV_Patterm);
        mTV_Patterm.setText(tvContent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "==onStop()==");
        tvContent.append("\n###onStop()###\n");
        mTV_Patterm.setText(tvContent);
    }
}
