package com.wk.customviewdemo.view;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * User: WangKai(123940232@qq.com)
 * 2015-08-11 16:43
 */
public class DragableViewGroup extends LinearLayout {
    private final String TAG = getClass().getSimpleName();
    private ViewDragHelper mDragHelper;
    private View commonView, releaseView, edgeView;
    private Point releasePoint = new Point(0, 0);

    public DragableViewGroup(Context context) {
        super(context);

//        mDragHelper.captureChildView(getChildAt(0), getChildAt(0).getId());
    }

    public DragableViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);


        mDragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {

                Log.d(TAG, "tryCaptureView ");
                return true;
            }

            @Override
            public void onViewDragStateChanged(int state) {
                super.onViewDragStateChanged(state);
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
            }

            @Override
            public void onViewCaptured(View capturedChild, int activePointerId) {
                super.onViewCaptured(capturedChild, activePointerId);
                Log.d(TAG, "onViewCaptured ");
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                if (releasedChild == releaseView) {
                    mDragHelper.settleCapturedViewAt(releasePoint.x, releasePoint.y);
                    invalidate();
                }


            }

            @Override
            public void onEdgeTouched(int edgeFlags, int pointerId) {
                super.onEdgeTouched(edgeFlags, pointerId);
            }

            @Override
            public boolean onEdgeLock(int edgeFlags) {
                return super.onEdgeLock(edgeFlags);
            }

            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
//                super.onEdgeDragStarted(edgeFlags, pointerId);

                mDragHelper.captureChildView(edgeView, pointerId);

            }

            @Override
            public int getOrderedChildIndex(int index) {
                return super.getOrderedChildIndex(index);
            }

            @Override
            public int getViewHorizontalDragRange(View child) {

                int range = getMeasuredWidth() - child.getMeasuredWidth()- getPaddingLeft() - getPaddingRight();
                return range;

            }

            @Override
            public int getViewVerticalDragRange(View child) {
                int range = getMeasuredHeight()- child.getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
                return range;
            }


            //水平边界检查
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                Log.d(TAG, "clampViewPositionHorizontal " + "left: " + left + " dx: " + dx);
                int minLeft = getPaddingLeft();
                int maxLeft = getWidth() - getPaddingRight() - child.getWidth();
                if (left <= minLeft) {
                    return minLeft;
                }else if (left >= maxLeft){
                    return maxLeft;

                }else {
                    return left;
                }
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }
        });

        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    public DragableViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        releasePoint.x = releaseView.getLeft();
        releasePoint.y = releaseView.getTop();
        Log.d(TAG, "onLayout ");
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean b = mDragHelper.shouldInterceptTouchEvent(ev);
        Log.d(TAG, "onInterceptTouchEvent " + b);
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        Log.d(TAG, "onTouchEvent ");
        mDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        commonView = getChildAt(0);
        releaseView = getChildAt(1);
        edgeView = getChildAt(2);
        Log.d(TAG, "onFinishInflate ");
    }

    @Override
    public void computeScroll() {
        super.computeScroll();

        if (mDragHelper.continueSettling(true)) {

            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
