package com.hdd.androidreview.Patterm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hdd.androidreview.PattermBaseActivity;
import com.hdd.androidreview.R;
import com.hdd.androidreview.utils.AppUtil;

public class SingleTaskActivity extends PattermBaseActivity implements View.OnClickListener {

    private Button mBnt_Patterm, mBnt_SingTop, mBnt_SingInstance, mBnt_SingTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);
        mTV_content = (TextView) findViewById(R.id.mTV_content);
        mBnt_Patterm = (Button) findViewById(R.id.mBnt_Patterm);
        mBnt_SingTop = (Button) findViewById(R.id.mBnt_SingTop);
        mBnt_SingInstance = (Button) findViewById(R.id.mBnt_SingInstance);
        mBnt_SingTask = (Button) findViewById(R.id.mBnt_SingTask);
        mBnt_Patterm.setOnClickListener(this);
        mBnt_SingTop.setOnClickListener(this);
        mBnt_SingInstance.setOnClickListener(this);
        mBnt_SingTask.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        StringBuilder taskTopActivity = AppUtil.getTaskTopActivity(getApplicationContext());
        mTV_content.setText(taskTopActivity);
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
            case R.id.mBnt_SingInstance:
                intent.setClass(getApplicationContext(), SingleInstanceActivity.class);

                break;

            case R.id.mBnt_SingTask:
                intent.setClass(getApplicationContext(), SingleTaskActivity.class);

                break;
        }

        startActivity(intent);
    }
}
