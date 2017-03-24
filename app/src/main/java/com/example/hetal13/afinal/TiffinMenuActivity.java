package com.example.hetal13.afinal;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.ArrayList;

public class TiffinMenuActivity extends Navigation {
ImageButton addTiffin,deletemenu;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String cat,menu;
    ArrayList<TiffinMenuPojo> arrayList = new ArrayList<TiffinMenuPojo>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_tiffin_menu);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_tiffin_menu, null, false);
        drawerLayout.addView(contentView, 0);
        getSupportActionBar().setTitle("Menu Details");



        addTiffin= (ImageButton) findViewById(R.id.iv_addmenu);
        addTiffin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TiffinMenuFragment Tiffinfragment=new TiffinMenuFragment();
                android.app.FragmentManager fm = getFragmentManager();
                Tiffinfragment.show(fm, "menu");

            }
        });
        adapter = new TiffinMenuAdapter(TiffinMenuActivity.this,arrayList);
        recyclerView=(RecyclerView) findViewById(R.id.recycle);
        cat="Category:Lunch";
        menu="Menu:Panner Tika";

        int i;
        for (i=0;i<5;i++) {
           TiffinMenuPojo tiffinMenuPojo = new TiffinMenuPojo("Lunch",menu);
            arrayList.add(tiffinMenuPojo);

            }
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(TiffinMenuActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }
}
