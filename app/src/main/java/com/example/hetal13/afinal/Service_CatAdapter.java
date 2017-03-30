package com.example.hetal13.afinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hetal13 on 25-02-2017.
 */
public class Service_CatAdapter extends RecyclerView.Adapter<Service_CatAdapter.MyViewHolder> {

    private Context mContext;
    private List<Service_ListPojo> catlist;



    public Service_CatAdapter(Context mContext, List<Service_ListPojo> catlist) {
        this.mContext = mContext;
        this.catlist = catlist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.service_card, parent, false);

        return new MyViewHolder(itemView,catlist,mContext);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
       Service_ListPojo album = catlist.get(position);
        holder.catname.setText(album.getCatname());
        Log.v("java123",album.getCatname());
        holder.count.setText(String.valueOf(album.getCount()));
       // Log.v("java123", String.valueOf(album.getCount()));

        // TextDrawable drawable = TextDrawable.builder()
         //       .buildRound("H", Color.parseColor("#701b46"));
       // holder.thumbnail.setImageDrawable(drawable);
        holder.thumbnail.setImageResource(album.getThumbnail());
        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

    }


    @Override
    public int getItemCount() {
        return catlist.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView catname,count;
        public ImageView thumbnail;
        Context context;

        List<Service_ListPojo> catlist=new ArrayList<>();

        public MyViewHolder(View view,List<Service_ListPojo> catlist,Context context) {
            super(view);
            this.catlist=catlist;
            this.context=context;
            catname = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            count= (TextView) view.findViewById(R.id.txtviewCount);
            thumbnail.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();

            Service_ListPojo service_listPojo = this.catlist.get(position);
            //  Intent intent = new Intent(this.context,Service_Details.class);
            Intent intent=new Intent(this.context,Recycle_Cat_Details.class);
            intent.putExtra("ServiceName",service_listPojo.getCatname());
            intent.putExtra("skill_id",service_listPojo.getId());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);


        }
    }

}
