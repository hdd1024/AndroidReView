package com.hdd.androidreview.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hdd.androidreview.R;

public class CirleView2 extends View {

    private int mColor;

    private Paint mPaint;

    public CirleView2(Context context) {
        super(context);
        init();
    }

    public CirleView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CirleView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自动属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CirleView2);
        //入股未设置颜色，默认为红色
        mColor = typedArray.getColor(R.styleable.CirleView2_circleview2_color, Color.BLUE);
        typedArray.recycle();
        init();
    }

    private void init() {
        //创建画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mColor);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST &&
                heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, 200);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, 200);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingButton = getPaddingBottom();

        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingButton;
        int radius = Math.min(width, height) / 2;
        //画圆
        canvas.drawCircle(paddingLeft + width / 2,
                paddingTop + height / 2, radius, mPaint);
    }
}
