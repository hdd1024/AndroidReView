package com.hdd.androidreview.customView;

import android.util.Log;

public class TestParent {
    private String TAG = "TestParent";


    protected void testMeathd(int a) {

        int b = a;
    }

    public void testP() {
        testMeathd(1);
        Log.i(TAG, "testP");


    }

    public void testS() {
        int a = 5;
        int b = 9;
        a &= ~b;
        System.out.println(a);
    }
}
