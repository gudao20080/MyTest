package com.wk.customviewdemo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class CanvasTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_test);

        ImageView iv = (ImageView) findViewById(R.id.iv);
        Bitmap bitmap = Bitmap.createBitmap(720, 1280, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Path path = new Path();
        path.moveTo(100, 600);
        path.quadTo(300, 100, 500, 900);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.MITER);
        paint.setColor(Color.BLUE);
//        paint.setStrokeWidth(5);
        paint.setTextSize(20);
//        canvas.drawPath(path, paint);
        canvas.drawTextOnPath("哈哈哈哈，我来了！", path, 90, -20, paint);
        paint.setColor(Color.RED);
        canvas.drawPath(path, paint);
        iv.setImageBitmap(bitmap);
    }
}
