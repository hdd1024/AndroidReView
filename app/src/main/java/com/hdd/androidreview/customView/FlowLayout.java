package com.hdd.androidreview.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {


    private List<LineView> mLineViewList;

    private int marginLeft = 0;
    private int marginTop = 0;
    private int marginRight = 0;
    private int marginBottom = 0;

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LineView mLineView = null;
        mLineViewList = new ArrayList<>();
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int totalHeight = 0;//总高度
        int remaining = 0;//剩余空间
        int usedWidth = 0;//已用宽度


        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);

            //先测量子View
            measureChild(childView, widthMeasureSpec, widthMeasureSpec);
            //获取Margin值
            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            marginLeft = Math.max(marginLeft, lp.rightMargin);
            marginTop = Math.max(marginTop, lp.topMargin);
            marginRight = Math.max(marginRight, lp.rightMargin);
            marginBottom = lp.bottomMargin;
            int childWidth = childView.getMeasuredWidth() + marginLeft + marginRight;

            //判断LineView是否为空
            if (mLineView == null) {
                mLineView = new LineView();
                mLineViewList.add(mLineView);
            }
            //计算剩余空间
            remaining = widthSize - usedWidth;

            //判断子view的测量宽度是否大于剩余空间，如果大于则另起一行
            if (childWidth > remaining) {
                //另起一行，已用宽度应该为零
                usedWidth = 0;
                //添加View
                mLineView = new LineView();
                mLineViewList.add(mLineView);
            }
            mLineView.addView(childView);
            //已用宽度累加
            usedWidth += childWidth;
            mLineView.setTotalWidth(usedWidth);

        }

        for (int i = 0; i < mLineViewList.size(); i++) {
            //总高度=所有行数相加
            totalHeight += mLineViewList.get(i).mHeight;
        }
        //父容器的总高度=上下padding的高度，在最后额外加一个底部marginBottom
        totalHeight += getPaddingTop() + getPaddingBottom() + marginBottom;
        setMeasuredDimension(widthSize, heightMode == MeasureSpec.EXACTLY ? heightSize : totalHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int left = l + getPaddingLeft();
        int top = t + getPaddingTop();
        for (int i = 0; i < mLineViewList.size(); i++) {
            LineView lineView = mLineViewList.get(i);
            lineView.layout(left, top);
            top += lineView.mHeight;
        }
    }


    public class LineView {
        private int mHeight = 0;//View的高
        private int totalWidth = 0;//共占用多少宽度
        private List<View> childViews = new ArrayList<>();


        public void addView(View child) {
            childViews.add(child);
            mHeight = child.getMeasuredHeight() + marginTop;
        }

        //设置这一行共占用了多少宽度
        public void setTotalWidth(int totalWidth) {
            this.totalWidth = totalWidth;
        }

        public void layout(int l, int t) {
            int left = l;
            int top = t;
            int parentWidth = getMeasuredWidth();
            //求为每个子View平均分配的偏移宽度
            int cMeanWith = (parentWidth - totalWidth) / (childViews.size() + 1);

            //设置每个View的位置
            for (int i = 0; i < childViews.size(); i++) {
                View childView = childViews.get(i);
                int childWidth = childView.getMeasuredWidth();
                int childHeight = childView.getMeasuredHeight();

                int cLeft = cMeanWith + left + marginLeft;
                int cRight = cMeanWith + left + childWidth + marginRight;
                int cTop = top + marginTop;
                int cBottom = top + childHeight + marginBottom;

                //设置View的具体位置
                childView.layout(cLeft, cTop, cRight, cBottom);
                left += childWidth + marginLeft + marginRight + cMeanWith;
            }
        }

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }


}
