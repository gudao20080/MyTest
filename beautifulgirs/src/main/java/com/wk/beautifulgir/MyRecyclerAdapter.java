package com.wk.beautifulgir;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;

import java.util.List;

//import com.squareup.picasso.Picasso;

/**
 * User: WangKai(123940232@qq.com)
 * 2015-07-21 11:19
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.PicsVH> {
    private List<Pic> pics;
    private Activity activity;
    int widthPixels;
    int heightPixels;

    public MyRecyclerAdapter(Activity context, @NonNull List<Pic> pics) {
        this.activity = context;
        this.pics = pics;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        widthPixels = displayMetrics.widthPixels;
        heightPixels = displayMetrics.heightPixels;
    }

    @Override
    public PicsVH onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View picItem = View.inflate(context, R.layout.item_pic, null);
        PicsVH vh = new PicsVH(picItem);

        return vh;
    }

    @Override
    public void onBindViewHolder(final PicsVH holder, int position) {
        Pic pic = pics.get(position);
//        Picasso.with(activity).load(pic.getPicUrl()).into(holder.iv);
        final GenericDraweeHierarchy hierarchy = holder.iv.getHierarchy();
//        hierarchy.setPlaceholderImage(R.mipmap.b);

//        RoundingParams roundingParams1 = new RoundingParams();
//        roundingParams1.setCornersRadius(20f);
//        hierarchy.setRoundingParams(roundingParams1);
        ViewGroup.LayoutParams layoutParams = holder.iv.getLayoutParams();
        layoutParams.width = widthPixels;

        RoundingParams roundingParams = RoundingParams.fromCornersRadius(30f);
        roundingParams.setOverlayColor(Color.RED);
        roundingParams.setBorder(Color.BLUE, 5f);
        hierarchy.setRoundingParams(roundingParams);

        ControllerListener listener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onSubmit(String id, Object callerContext) {
                super.onSubmit(id, callerContext);
            }

            @Override
            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                super.onFinalImageSet(id, imageInfo, animatable);
            }

            @Override
            public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
                super.onIntermediateImageSet(id, imageInfo);
            }

            @Override
            public void onIntermediateImageFailed(String id, Throwable throwable) {
                super.onIntermediateImageFailed(id, throwable);
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                super.onFailure(id, throwable);
            }

            @Override
            public void onRelease(String id) {
                super.onRelease(id);
            }
        };
        Uri uri = Uri.parse(pic.getPicUrl());
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setTapToRetryEnabled(true)
                .setOldController(holder.iv.getController())
                .setControllerListener(listener)
                .build();
        holder.iv.setImageURI(uri);

    }

    @Override
    public int getItemCount() {
        return pics.size();
    }



    public static class PicsVH extends RecyclerView.ViewHolder{

        private SimpleDraweeView iv;
        public PicsVH(View itemView) {
            super(itemView);
            iv = (SimpleDraweeView) itemView.findViewById(R.id.simpleDraweeView);
        }
    }

}  
