package com.hdd.androidreview.customView;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;

import com.hdd.androidreview.MyApp;
import com.hdd.androidreview.customView.evnent.HorizonalscrollViewExActivity;

import junit.extensions.ActiveTestSuite;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestParentTest  extends ActiveTestSuite{
//    public TestParentTest() {
//        super(HorizonalscrollViewExActivity.class);
//    }

//    @Test
//    public void testP() {
//        TestAbstractMethd abstractMethd = new TestAbstractMethdChild();
//        abstractMethd.testP();
//    }

    @Test
    public void toActivity() {
      Context context= InstrumentationRegistry.getContext();
        Intent intent = new Intent();
        intent.setClass(context, HorizonalscrollViewExActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}