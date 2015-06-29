package com.wk.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/1/18.
 */
public class CustomColockView extends TextView {
    private Paint paint;

    public CustomColockView(Context context) {
        super(context);
    }

    public CustomColockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        paint.setTextSize(30);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.rotate(-60);
        canvas.translate(-getHeight(), 0);
        super.onDraw(canvas);


        int width = canvas.getWidth();
        int height = canvas.getHeight();
//        canvas.rotate(-30);

    }
}
