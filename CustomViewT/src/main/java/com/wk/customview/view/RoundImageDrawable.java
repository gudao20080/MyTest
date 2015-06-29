package com.wk.customview.view;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * User: WangKai(123940232@qq.com)
 * 2015-03-04 11:31
 */
public class RoundImageDrawable extends Drawable {

    private Paint mPaint;
    private Bitmap mBitmap;
    RectF rectF;
    public RoundImageDrawable(Bitmap bitmap) {
        mBitmap = bitmap;
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(shader);
//        Rect bounds = getBounds();

    }

//    @Override
//    public void setBounds(Rect bounds) {
//
//        super.setBounds(bounds);
//        rectF = new RectF(bounds);
//    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        rectF = new RectF(left, top, right, bottom);
    }

    @Override
    public void setBounds(Rect bounds) {
        super.setBounds(bounds);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRoundRect(rectF, 30, 30, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
