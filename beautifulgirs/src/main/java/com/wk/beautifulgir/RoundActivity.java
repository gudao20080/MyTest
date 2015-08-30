package com.wk.beautifulgir;

import android.graphics.Color;
import android.graphics.PointF;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.HashSet;
import java.util.Set;

public class RoundActivity extends AppCompatActivity {
    private static final Uri URI = Uri.parse(
            "http://apod.nasa.gov/apod/image/1410/20141008tleBaldridge001h990.jpg");
    private static final int WIDTH = 400;
    private static final int HEIGHT = 240;
    private static final float FOCUS_X = 0.454f;
    private static final float FOCUS_Y = 0.266f;
    private static final int RADIUS = 50;

    private LinearLayout mUnroundedColumn;
    private LinearLayout mRoundedColumn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fresco.initialize(this);
        setContentView(R.layout.activity_round);
        FLog.setMinimumLoggingLevel(FLog.VERBOSE);

        mUnroundedColumn = (LinearLayout) findViewById(R.id.unrounded);
        mRoundedColumn = (LinearLayout) findViewById(R.id.rounded);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(WIDTH, HEIGHT);

        RoundingParams bitmapOnly = RoundingParams.fromCornersRadius(RADIUS)
                .setRoundingMethod(RoundingParams.RoundingMethod.BITMAP_ONLY);
        RoundingParams overlayColor = RoundingParams.fromCornersRadius(RADIUS)
                .setRoundingMethod(RoundingParams.RoundingMethod.OVERLAY_COLOR)
                .setOverlayColor(Color.WHITE);
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());

        Set<ScalingUtils.ScaleType> useBitmapOnly = new HashSet<>();
        useBitmapOnly.add(ScalingUtils.ScaleType.CENTER_CROP);
        useBitmapOnly.add(ScalingUtils.ScaleType.CENTER);
        useBitmapOnly.add(ScalingUtils.ScaleType.FOCUS_CROP);

        for (ScalingUtils.ScaleType scaleType : ScalingUtils.ScaleType.values()) {
            builder.setActualImageScaleType(scaleType);
            if (scaleType == ScalingUtils.ScaleType.FOCUS_CROP) {
                builder.setActualImageFocusPoint(new PointF(FOCUS_X, FOCUS_Y));
            }
            builder.setRoundingParams(null);
            SimpleDraweeView unroundedImage = new SimpleDraweeView(this, builder.build());

            if (useBitmapOnly.contains(scaleType)) {
                builder.setRoundingParams(bitmapOnly);
            } else {
                builder.setRoundingParams(overlayColor);
            }
            SimpleDraweeView roundedImage = new SimpleDraweeView(this, builder.build());

            unroundedImage.setLayoutParams(params);
            roundedImage.setLayoutParams(params);
            mUnroundedColumn.addView(unroundedImage);
            mRoundedColumn.addView(roundedImage);
            unroundedImage.setImageURI(URI);
            roundedImage.setImageURI(URI);
        }
    }

}
