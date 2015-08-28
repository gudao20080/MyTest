package com.wk.beautifulgir;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

//import com.squareup.picasso.Picasso;

/**
 * User: WangKai(123940232@qq.com)
 * 2015-07-21 11:19
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.PicsVH> {
    private List<Pic> pics;
    private Context context;

    public MyRecyclerAdapter(Context context, @NonNull List<Pic> pics) {
        this.context = context;
        this.pics = pics;
    }

    @Override
    public PicsVH onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View picItem = View.inflate(context, R.layout.item_pic, null);
        PicsVH vh = new PicsVH(picItem);
        return vh;
    }

    @Override
    public void onBindViewHolder(PicsVH holder, int position) {
        Pic pic = pics.get(position);
//        Picasso.with(context).load(pic.getPicUrl()).into(holder.iv);
        GenericDraweeHierarchy hierarchy = holder.iv.getHierarchy();
        hierarchy.setPlaceholderImage(R.mipmap.b);
        hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE);
//        RoundingParams roundingParams1 = new RoundingParams();
//        roundingParams1.setCornersRadius(20f);
//        hierarchy.setRoundingParams(roundingParams1);
        holder.iv.setImageURI(Uri.parse(pic.getPicUrl()));

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
