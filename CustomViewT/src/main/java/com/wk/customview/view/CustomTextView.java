package com.wk.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.wk.customview.R;

/**
 * User: WangKai(123940232@qq.com)
 * 2015-06-29 09:08
 */
public class CustomTextView extends View {
    private Paint mPaint;
    private TextPaint mTextPaint;
    private int mTextColor;
    private String mText;
    private float mTextSize;
    private Rect mBounds;


    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Custom_text_View);

        mTextColor= typedArray.getColor(R.styleable.Custom_text_View_textColor, Color.RED);
        mText = typedArray.getString(R.styleable.Custom_text_View_text);
        if (TextUtils.isEmpty(mText)) {
            mText = "text is empty";
        }
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.Custom_text_View_textSize,
            (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 40, getResources().getDisplayMetrics())
        );
        typedArray.recycle();

        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
        mBounds = new Rect();
        mTextPaint.getTextBounds(mText, 0, mText.length(), mBounds);
        mPaint.setColor(Color.YELLOW);

    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);

//        canvas.drawRect(mBounds, mPaint);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        canvas.drawText(mText, getMeasuredWidth() / 2 - mBounds.width() / 2, getMeasuredHeight() / 2 + mBounds.height() / 2, mTextPaint);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widMode = MeasureSpec.getMode(widthMeasureSpec);
        int widSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int w, h;

        if (MeasureSpec.EXACTLY == widMode) {
            w = widSize;
        }else {
            int desired = getPaddingLeft() + mBounds.width() + getPaddingRight();
            w = desired;
        }

        if (MeasureSpec.EXACTLY == heightMode) {
            h = heightSize;
        } else {
            int desired = getPaddingBottom() + mBounds.height() + getPaddingTop();
            h = desired;
        }

//        super.onMeasure(MeasureSpec.makeMeasureSpec(w, widMode), MeasureSpec.makeMeasureSpec(h, heightMode));
        setMeasuredDimension(w, h);
    }
}
