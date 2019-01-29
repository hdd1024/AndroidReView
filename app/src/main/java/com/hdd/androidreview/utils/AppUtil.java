package com.hdd.androidreview.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

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

}
