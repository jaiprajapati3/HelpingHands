package com.example.hetal13.afinal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;
import java.util.Random;

public class Service_history extends Navigation {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ImageView image;
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView.LayoutManager layoutManager;
    //int img_id=R.drawable.hetal;
    String name,email,mobile;
    Handler handler = new Handler();
    ArrayList<HistoryPojo> arrayList = new ArrayList<HistoryPojo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_service_history);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_service_history, null, false);
        drawerLayout.addView(contentView, 0);
        getSupportActionBar().setTitle("Service history");
        adapter = new HistoryAdapter_SP(Service_history.this,arrayList);
        recyclerView=(RecyclerView) findViewById(R.id.recycle);
        name="Hetal Shah";
        email="hetalshah027@gmail.com";
        mobile="8487046553";
        //Random rnd = new Random();
       // int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        TextDrawable drawable = TextDrawable.builder()
                .buildRound("H", Color.parseColor("#701b46"));
        int i;
        for (i=0;i<=50;i++) {
            HistoryPojo historyPojo = new HistoryPojo(drawable, name, email, mobile);
            arrayList.add(historyPojo);
        }
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(Service_history.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }
    public void onBackPressed()
    {
        Intent i_rating=new Intent(Service_history.this,Service_feedback.class);
        i_rating.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i_rating.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i_rating.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        startActivity(i_rating);
        return;
    }
}
