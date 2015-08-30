package wangkai.mytest.view;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

/**
 * User: WangKai(123940232@qq.com)
 * 2015-08-03 15:31
 */
public class TestDrawable extends Drawable {
    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        int i = PixelFormat.TRANSLUCENT;
        return 0;
    }
}
