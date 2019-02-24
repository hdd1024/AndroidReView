package com.hdd.androidreview.customView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.Scroller;

import com.hdd.androidreview.utils.AppUtil;

public class TestFrameLayout extends FrameLayout {
    private final String TAG = "TestFrameLayout";
    private Scroller mScroller;

    public TestFrameLayout(@NonNull Context context) {
        super(context);
    }

    public TestFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        AppUtil.eventLog(ev, TAG, "dispatchTouchEvent");
        //        return true;
        return false;
//        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        AppUtil.eventLog(ev, TAG, "onInterceptTouchEvent");
        return true;
//        return false;
//        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        AppUtil.eventLog(event, TAG, "onTouchEvent");
//        return true;
        return false;
//        return super.onTouchEvent(event);
    }

    private int mInterceptedX;
    private int mInterceptedY;

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return false;
            default:
                return true;
        }
    }

    private void smoothScrollBy(int dx, int dy) {
        mScroller.startScroll(getScrollX(), getScrollY(), dx, dy);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        //返回true代表滑动未结束，false代表滑动结束
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
        }
    }
}
