package com.example.hetal13.afinal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
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
import java.util.Date;

public class Service_history extends Navigation {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ImageView image;
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView.LayoutManager layoutManager;
    //int img_id=R.drawable.hetal;
    String name,email,mobile;
    ProgressBar progressBar;
    Handler handler = new Handler();
    ArrayList<HistoryPojo> arrayList = new ArrayList<HistoryPojo>();
    ColorGenerator generator = ColorGenerator.MATERIAL;
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
        mobile="8487046558";
        progressBar= (ProgressBar) findViewById(R.id.progressbar_loadlist);
        //Random rnd = new Random();
       // int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
//        final TextDrawable drawable = TextDrawable.builder()
//                .buildRound("A", Color.parseColor("#701b46"));
        int i;
//        for (i=0;i<=50;i++) {
//            HistoryPojo historyPojo = new HistoryPojo(drawable, name, email, mobile);
//            arrayList.add(historyPojo);
//        }
        //String Email=MyApplication.getInstance().getPrefManager().getemail();
        String Email="reenajani@gmail.com";

        String url_history=UrlString.url_string+"/call_history.php?email="+Email;
        Log.v("TAG1",url_history);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url_history, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.GONE);
                Log.v("TAG1234",response.toString());
                for(int i=0;i<response.length();i++){
                    try {

                        JSONObject jsonObject=response.getJSONObject(i);
                        String name=jsonObject.getString("name");
                        //String name="Bhoomi";
                        String letter = String.valueOf(name.charAt(0)).toUpperCase();
                        String email=jsonObject.getString("email");
                        String mobile=jsonObject.getString("phone");
                        int flag=Integer.parseInt(jsonObject.getString("way"));
                        String timing = jsonObject.getString("timing");
                        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        Date date = dt.parse(timing);
                        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
                        Log.v("timingDate", dt1.format(date));
                        String timeDate = dt1.format(date);
                        dt1 = new SimpleDateFormat("HH:mm");
                        Log.v("timingTime", dt1.format(date));
                        String time = dt1.format(date);
                        Log.v("timing", String.valueOf(date));
                        final TextDrawable drawable1 = TextDrawable.builder()
                                .buildRound(letter, generator.getRandomColor());
                        HistoryPojo historyPojo = new HistoryPojo(drawable1, name, email, mobile, flag, timeDate, time);
                        arrayList.add(historyPojo);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(Service_history.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                Log.v("TAG123", String.valueOf(adapter.getItemCount()));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("TAG12",error.getMessage());
            }
        });
        Log.v("TAG12",adapter.toString());
        MySingleton.getInstance(this).addToRequestque(jsonArrayRequest);

        Log.v("TAG12", String.valueOf(adapter.getItemCount()));

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
