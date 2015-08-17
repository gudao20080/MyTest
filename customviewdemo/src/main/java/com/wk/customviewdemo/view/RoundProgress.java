package com.wk.customviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ProgressBar;

/**
 * User: WangKai(123940232@qq.com)
 * 2015-08-17 11:49
 */
public class RoundProgress extends ProgressBar {
    private final String TAG = getClass().getSimpleName();
    private Paint mPaint;
    private int mProgress = 0;
    public RoundProgress(Context context) {
        super(context);
    }

    public RoundProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(10f);
        mPaint.setStyle(Paint.Style.STROKE);


    }

    public RoundProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        int i = getProgress();
//        canvas.drawCircle(200, 200, 100, mPaint);
        Log.d(TAG, "onDraw " + i);
            canvas.drawArc(0, 0, getWidth(), getHeight(), 0f , mProgress, false, mPaint);
//        canvas.drawOval(0, 0, getWidth(), getHeight(), mPaint);



    }

    @Override
    public synchronized void setProgress(int progress) {
//        super.setProgress(progress);
        mProgress = progress;
        Log.d(TAG, "setProgress " + progress);
        invalidate();
    }
}
