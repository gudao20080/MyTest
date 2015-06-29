package com.wk.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2015/1/10.
 */
public class TestView extends View {
    private Paint paint;
    private Rect rect;
    private Path path;
    public TestView(Context context) {
        super(context);

    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        path = new Path();
        path.moveTo(33, 33);
//        path.lineTo(100, 300);
//        path.lineTo(200, 400);
        path.quadTo(50, 100, 400, 100);
//        path.close();

//        rect = new Rect(0, 0, 300, 300);
//        rect.intersect(100, 100, 800, 800);

//         rect.union(100, 100, 800, 800);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
//        canvas.clipRect(0, 0, 300, 300);
//        canvas.drawColor(Color.RED);
//        canvas.clipRect(rect);
//        canvas.drawColor(Color.RED);

        canvas.drawPath(path, paint);




    }
}
