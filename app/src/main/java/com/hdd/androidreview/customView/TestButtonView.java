//package com.hdd.androidreview.customView;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//import android.view.View;
//
//import com.hdd.androidreview.utils.AppUtil;
//
//public class TestButtonView extends android.support.v7.widget.AppCompatButton {
//    private String TAG = "TestButtonView";
//
//    public TestButtonView(Context context) {
//        super(context);
//    }
//
//    public TestButtonView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public TestButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//
//    }
//
//    private void init() {
//
//    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        AppUtil.eventLog(event, TAG, "dispatchTouchEvent");
////        return true;
////        return false;
//        return super.dispatchTouchEvent(event);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        AppUtil.eventLog(event, TAG, "onTouchEvent");
////        return true;
////        return false;
//        return super.onTouchEvent(event);
//    }
//}
