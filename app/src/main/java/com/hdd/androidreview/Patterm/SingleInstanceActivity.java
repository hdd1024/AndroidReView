package com.hdd.androidreview.Patterm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hdd.androidreview.PattermBaseActivity;
import com.hdd.androidreview.R;

public class SingleInstanceActivity extends PattermBaseActivity implements View.OnClickListener {
    private Button mBnt_Patterm, mBnt_SingTop, mBnt_SingTask, mBnt_SingInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_instance);
        mTV_content = findViewById(R.id.mTV_content);
        mBnt_Patterm = findViewById(R.id.mBnt_Patterm);
        mBnt_SingTop = findViewById(R.id.mBnt_SingTop);
        mBnt_SingTask = findViewById(R.id.mBnt_SingTask);
        mBnt_SingInstance = findViewById(R.id.mBnt_SingInstance);
        mBnt_Patterm.setOnClickListener(this);
        mBnt_SingTop.setOnClickListener(this);
        mBnt_SingTask.setOnClickListener(this);
        mBnt_SingInstance.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()) {
            case R.id.mBnt_Patterm:
                intent.setClass(getApplicationContext(), PattermActivity.class);
                break;
            case R.id.mBnt_SingTop:
                intent.setClass(getApplicationContext(), SingleTopActivity.class);

                break;
            case R.id.mBnt_SingTask:
                intent.setClass(getApplicationContext(), SingleTaskActivity.class);

                break;
            case R.id.mBnt_SingInstance:
                intent.setClass(getApplicationContext(), SingleInstanceActivity.class);
                break;
        }

        startActivity(intent);
    }
}
