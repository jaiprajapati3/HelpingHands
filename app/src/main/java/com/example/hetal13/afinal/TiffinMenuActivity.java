package com.example.hetal13.afinal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import com.amulyakhare.textdrawable.TextDrawable;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TiffinMenuActivity extends Navigation {
ImageButton addTiffin,deletemenu;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String cat,menu;
    String url_menuList;
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
        String email="vaidyameghna1996@gmail.com";
        Date now = new Date();
        Date alsoNow = Calendar.getInstance().getTime();
        String nowAsString = new SimpleDateFormat("yyyy-MM-dd").format(now);
        url_menuList=UrlString.url_string+"/Tiffin_show.php?email="+email+"&timing="+nowAsString;
        Log.v("menu1",url_menuList);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url_menuList, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.v("menu112",response.toString());
                for(int i=0;i<response.length();i++){

                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        String cat=jsonObject.getString("menu_type");
                        String menu=jsonObject.getString("menu");
                        String date=jsonObject.getString("Date");
                        Log.v("url",date);
//                        java.util.Date tiffinDate=new SimpleDateFormat("yyyy-MM-dd").parse(date);
//                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//
                       // Log.v("url1", String.valueOf(tiffinDate));
                        TiffinMenuPojo tiffinMenuPojo=new TiffinMenuPojo(cat,menu,date);
                        arrayList.add(tiffinMenuPojo);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(TiffinMenuActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Log.v("Menu1",error.getMessage());
            }
        });
        MySingleton.getInstance(this).addToRequestque(jsonArrayRequest);

        int i;

//        for (i=0;i<5;i++) {
//           TiffinMenuPojo tiffinMenuPojo = new TiffinMenuPojo("Lunch",menu);
//            arrayList.add(tiffinMenuPojo);
//
//            }
//        recyclerView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(TiffinMenuActivity.this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);


    }
    @Override
    public void onResume(){
        super.onResume();
      //  adapter.notifyAll();

    }
    public void onBackPressed() {
        Intent i_rating = new Intent(TiffinMenuActivity.this, Service_feedback.class);
        i_rating.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i_rating.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i_rating.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i_rating);
        return;
    }

}
