package com.hdd.androidreview.customView.evnent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class HorizonalScrollViewEx extends ViewGroup {
    private final String TAG = "HorizonalScrollViewEx";

    private int mChildrenSize;
    private int mChildrenWidth;
    private int mChildrenHeight;
    private int mChildrenIndex;
    private int mChildrenCount;

    //记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;
    //只记录按下时拦截方法的坐标
    private int mLastInterceptX = 0;
    private int mLastInterceptY = 0;

    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;

    public HorizonalScrollViewEx(Context context) {
        this(context, null);
    }


    public HorizonalScrollViewEx(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizonalScrollViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        if (mScroller == null) {
            mScroller = new Scroller(getContext());
            mVelocityTracker = VelocityTracker.obtain();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = 0;
        int measureHeight = 0;
        mChildrenCount = getChildCount();
        measureChildren(widthMeasureSpec, widthMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getMode(heightMeasureSpec);
        if (mChildrenCount == 0) {
            setMeasuredDimension(0, 0);

        } else if (widthSpecMode == MeasureSpec.AT_MOST &&
                heightSpecMode == MeasureSpec.AT_MOST) {
            View childView = getChildAt(0);

            measureWidth = childView.getWidth() * mChildrenCount;
            measureHeight = childView.getHeight();
            setMeasuredDimension(measureWidth, measureHeight);
        } else if (widthMeasureSpec == MeasureSpec.AT_MOST) {
            View childView = getChildAt(0);
            measureWidth = childView.getMeasuredWidth();
            setMeasuredDimension(measureWidth, heightSpecSize);
        } else if (heightMeasureSpec == MeasureSpec.AT_MOST) {
            View childView = getChildAt(0);
            measureHeight = childView.getMeasuredHeight();
            setMeasuredDimension(widthSpecSize, measureHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childrenLeft = 0;
        int childrenTop = 0;
        for (int i = 0; i < mChildrenCount; i++) {
            View childView = getChildAt(i);
            mChildrenWidth = childView.getMeasuredWidth();
            mChildrenHeight = childView.getMeasuredHeight();
            childView.layout(childrenLeft, childrenTop,
                    childrenLeft + mChildrenWidth, mChildrenHeight + childrenTop);
            childrenLeft += mChildrenWidth;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean interCepted = false;
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                interCepted = false;
                if (mScroller.isFinished()) {
                    mScroller.abortAnimation();
                    interCepted = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int distanceX = x - mLastInterceptX;
                int distanceY = y - mLastInterceptY;
                if (Math.abs(distanceX) > Math.abs(distanceY)) {
                    interCepted = true;
                } else {
                    interCepted = false;
                }

                break;
            case MotionEvent.ACTION_UP:
                interCepted = false;
                break;
        }
        mLastX = x;
        mLastY = y;
        mLastInterceptX = x;
        mLastInterceptY = y;
        return interCepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int distanceX = x - mLastX;
                int distanceY = x - mLastY;
                scrollBy(-distanceX, 0);
                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                int scrollToChildrenIndex = scrollX / mChildrenWidth;
                mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = mVelocityTracker.getXVelocity();
                if (Math.abs(xVelocity) >= 50) {
                    mChildrenIndex = xVelocity > 0 ? mChildrenIndex - 1 : mChildrenIndex + 1;
                } else {
                    mChildrenIndex = (scrollX + mChildrenWidth / 2) / mChildrenWidth;
                }
                mChildrenIndex = Math.max(0, Math.min(mChildrenIndex, mChildrenSize - 1));
                int dx = mChildrenIndex * mChildrenWidth - scrollX;
                smoothScrollBy(dx, 0);
                mVelocityTracker.clear();
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    private void smoothScrollBy(int dx, int dy) {
        mScroller.startScroll(getScrollX(), getScrollY(), dx, dy);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        //computeScrollOffest返回true代表还没有结束，返回false代表滑动已结束。
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        mVelocityTracker.recycle();
        super.onDetachedFromWindow();
    }
}
