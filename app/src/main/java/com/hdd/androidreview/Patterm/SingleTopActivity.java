package com.hdd.androidreview.Patterm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hdd.androidreview.PattermBaseActivity;
import com.hdd.androidreview.R;

public class SingleTopActivity extends PattermBaseActivity implements View.OnClickListener {
    private Button mBnt_Patterm, mBnt_SingInstance, mBnt_SingTask, mBnt_SingTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_top);
        mTV_content = findViewById(R.id.mTV_content);
        mBnt_Patterm = findViewById(R.id.mBnt_Patterm);
        mBnt_SingInstance = findViewById(R.id.mBnt_SingInstance);
        mBnt_SingTask = findViewById(R.id.mBnt_SingTask);
        mBnt_SingTop = findViewById(R.id.mBnt_SingTop);
        mBnt_Patterm.setOnClickListener(this);
        mBnt_SingInstance.setOnClickListener(this);
        mBnt_SingTask.setOnClickListener(this);
        mBnt_SingTop.setOnClickListener(this);
        Log.i(TAG, "onCreate");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()) {
            case R.id.mBnt_Patterm:
                intent.setClass(getApplicationContext(), PattermActivity.class);
                break;
            case R.id.mBnt_SingInstance:
                intent.setClass(getApplicationContext(), SingleTopActivity.class);

                break;
            case R.id.mBnt_SingTask:
                intent.setClass(getApplicationContext(), SingleTaskActivity.class);

                break;
            case R.id.mBnt_SingTop:
                intent.setClass(getApplicationContext(), SingleTopActivity.class);
                break;
        }

        startActivity(intent);
    }
}
