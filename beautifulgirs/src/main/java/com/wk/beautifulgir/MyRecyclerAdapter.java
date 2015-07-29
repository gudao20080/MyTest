package com.wk.beautifulgir;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

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
//        View picItem = LayoutInflater.from(context).inflate(R.layout.item_pic, parent, false);
        PicsVH vh = new PicsVH(picItem);
        return vh;
    }

    @Override
    public void onBindViewHolder(PicsVH holder, int position) {
        Pic pic = pics.get(position);
        Picasso.with(context).load(pic.getPicUrl()).into(holder.iv);

    }

    @Override
    public int getItemCount() {
        return pics.size();
    }



    public static class PicsVH extends RecyclerView.ViewHolder{

        private ImageView iv;
        public PicsVH(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.pic);
        }
    }

}  
