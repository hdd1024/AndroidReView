package com.hdd.androidreview.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.hdd.androidreview.utils.AppUtil;

public class CircleView extends View {
    private String TAG = "CircleView";

    int lastX;
    int lastY;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        lastX = dm.widthPixels / 2;//屏幕宽的二分之一
        lastY = dm.heightPixels / 2;//屏幕高的二分一

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(lastX, lastY, 80, paint);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        AppUtil.eventLog(event, TAG, "dispatchTouchEvent");
//        return true;
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "\nonTouchEvent():ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "\nonTouchEvent():ACTION_UP");
//                performClick();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = rawX - lastX;
                int moveY = rawY - lastY;

                int translationX = (int) getTranslationX() + moveX;
                int translationY = (int) getTranslationY() + moveY;
                Log.i(TAG, "\nmoveX:" + moveX + "\nmovaY:" + moveY
                        + "\ntranslationX:" + translationX + "\ntranslationY:" + translationY);
//                ViewHelper.setTranslationX(this, translationX);
//                ViewHelper.setTranslationY(this, translationY);
                setTranslationX(translationX);
                setTranslationY(translationY);
                //view的宽高计算
                int width = getRight() - getLeft();
                int width2 = getWidth();
                int height = getBottom() - getTop();
                int height2 = getHeight();
                //x、y的计算
                float x = getLeft() + getTranslationX();
                float x2 = getX();
                float y = getTop() + getTranslationY();
                float y2 = getY();
                break;

        }
        lastX = rawX;
        lastY = rawY;
        return true;
//        return super.onTouchEvent(event);
    }
}
