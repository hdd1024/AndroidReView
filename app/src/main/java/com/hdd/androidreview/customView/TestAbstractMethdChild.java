package com.hdd.androidreview.customView;

import android.util.Log;

public class TestAbstractMethdChild extends TestAbstractMethd {
    private String TAG = "TestAbstractMethdChild";

    @Override
    protected void testMeathd(int a) {
        Log.i(TAG, "==testMeathd==");

        System.out.println(TAG+":==testMeathd==");

    }
}
