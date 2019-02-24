package com.hdd.androidreview;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

public class MyApp extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        LeakCanary.install(this);

    }

}
