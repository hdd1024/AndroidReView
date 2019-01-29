package com.hdd.androidreview.Patterm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hdd.androidreview.R;
import com.hdd.androidreview.utils.AppUtil;
import com.hdd.androidreview.utils.CommandExecution;

/***********************************************************
 * 创建时间:2019/1/29
 * 作   者: [han]
 * 功能描述: <activity启动模式页面>
 * 备注信息: {查看activity在栈中的情况，可在控制台输入：adb shell dumpsys activity activities
 * 通过搜索关键字 most recent first 快速定位 留意包名}
 * @see
 **********************************************************/
public class PattermActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "PattermActivity";
    private StringBuilder tvContent = new StringBuilder("启动模式页面:\n");
    private Button mBnt_Patterm, mBnt_SingTop, mBnt_SingTask, mBnt_SingInstance, mBnt_cmd;
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
        mBnt_Patterm = findViewById(R.id.mBnt_Patterm);
        mBnt_SingTop = findViewById(R.id.mBnt_SingTop);
        mBnt_SingTask = findViewById(R.id.mBnt_SingTask);
        mBnt_SingInstance = findViewById(R.id.mBnt_SingInstance);
        mBnt_cmd = findViewById(R.id.mBnt_cmd);
        mBnt_SingTop.setOnClickListener(this);
        mBnt_SingTask.setOnClickListener(this);
        mBnt_SingInstance.setOnClickListener(this);
        mBnt_cmd.setOnClickListener(this);
        mTV_Patterm.setText(tvContent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        StringBuilder topActivity = AppUtil.getTaskTopActivity(getApplicationContext());
        mTV_Patterm.setText(topActivity);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "==onStop()==");
        tvContent.append("\n###onStop()###\n");
        mTV_Patterm.setText(tvContent);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()) {

            case R.id.mBnt_SingTop:
                intent.setClass(getApplicationContext(), SingleTopActivity.class);
                break;
            case R.id.mBnt_SingTask:
                intent.setClass(getApplicationContext(), SingleTaskActivity.class);

                break;
            case R.id.mBnt_SingInstance:
                intent.setClass(getApplicationContext(), SingleInstanceActivity.class);

                break;
            case R.id.mBnt_Patterm:
                intent.setClass(getApplicationContext(), PattermActivity.class);
                break;
            case R.id.mBnt_cmd:
                CommandExecution.CommandResult commandResult = CommandExecution.execCommand("dumpsys activity activities", false);
                Log.i(TAG, "activity栈内结果为:\n\n" + commandResult.successMsg);
                return;
        }

        startActivity(intent);
    }


}
