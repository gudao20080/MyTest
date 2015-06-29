package com.wk.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * TODO: document your custom view class.
 */
public class TouchView extends LinearLayout {
    private String TAG = getClass().getSimpleName();
    private Paint mPaint;
    private TextPaint mTextPaint;
    float h;


    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(40);
        mTextPaint.setColor(Color.RED);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        h = Math.abs(fontMetrics.ascent - fontMetrics.descent);
    }

    public TouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText("TouchView", 0, h, mTextPaint);
//        canvas.drawLine(0, 0, 300, 300, mPaint);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            LinearLayout.LayoutParams params = (LayoutParams) child.getLayoutParams();

        }

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        Log.d(TAG, "dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent");
        return false;
    }

}
