package com.hdd.androidreview.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.List;

/***********************************************************
 * 创建时间:2019/1/29
 * 作   者: [han]
 * 功能描述: <>
 * 备注信息: {}
 * @see
 **********************************************************/
public class AppUtil {
    private static final String TAG = AppUtil.class.getSimpleName();

    /**
     * 获取任务栈顶部activity
     *
     * @param context 上下文
     */
    public static StringBuilder getTaskTopActivity(Context context) {
        StringBuilder topActivity = new StringBuilder("栈顶activity为:\n");
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfoList = activityManager.getRunningTasks(1);
        for (ActivityManager.RunningTaskInfo runningTaskInfo : taskInfoList) {
            Log.i(TAG, "任务栈顶部activity信息:" + runningTaskInfo.topActivity);
            topActivity.append("\n" + runningTaskInfo.topActivity.getShortClassName());
        }
        return topActivity;
    }

    public static void eventLog(MotionEvent event, String classNanem, String mothdName) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(classNanem, "\n" + mothdName + "():ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(classNanem, "\n" + mothdName + "():ACTION_MOVE");

                break;
            case MotionEvent.ACTION_UP:
                Log.i(classNanem, "\n" + mothdName + "():ACTION_UP");
                break;
        }
    }

    public static void getTesLeak(Context context) {
        Toast.makeText(context, "您的内存泄漏啦", Toast.LENGTH_SHORT).show();
    }

}
