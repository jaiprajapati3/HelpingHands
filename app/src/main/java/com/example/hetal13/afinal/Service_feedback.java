package com.example.hetal13.afinal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Service_feedback extends Navigation {
    public ArrayList<ServicePojo_comment> arrayList = new ArrayList<ServicePojo_comment>();
    RecyclerView recyclerView;
    String skill_id;
    String url_getSpList;
    String url_commentshow;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    private int area_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_service_feedback, null, false);
        drawerLayout.addView(contentView, 0);
        getSupportActionBar().setTitle("Service feedback");
        //hetal
        recyclerView = (RecyclerView) findViewById(R.id.recycle);

       // int sp_id = 4;
        String Email="reenajani@gmail.com";
//get id from shared Preference
        url_commentshow = "https://hetal1395.000webhostapp.com/comment_showSp.php?email=" + Email;
        //  url_commentshow="https://hetal1395.000webhostapp.com/comment_show.php?sp_id="+sp_id;
        Log.e("TAG1", url_commentshow.toString());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url_commentshow, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("TAG","123");
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        JSONArray comments = object.getJSONArray("comments");
                        String commentShow = "";
                        for (int j = 0; j < comments.length(); j++) {
                            commentShow =commentShow+ comments.get(j) + "\n";
                            Log.e("java12",commentShow);
                        }
                        Log.e("java",commentShow);
                        final ServicePojo_comment contact = new ServicePojo_comment();
                        //    contact.setFeed_user(comments.getString("comment"));
                        // contact.setName_user(comments.getString("name"));
                        contact.setFeed_user(commentShow);
                        JSONArray profile = object.getJSONArray("Profile");
                        String name = String.valueOf(profile.get(0));
                        String phone=String.valueOf(profile.get(2));
                        String cId=object.getString("customer_id");
                        Log.e("TAG",name);
                        Log.e("java",phone);
                        Log.v("java",commentShow);
                        contact.setName_user(name);
                        contact.setPhone(phone);
                        contact.setCustomerId(cId);

                        arrayList.add(contact);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("java123",e.toString());
                    }

                }
                adapter = new ServiceAdapter_comment(Service_feedback.this, arrayList);

                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(Service_feedback.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("javaerror",error.toString());
            }
        });
//        //       final ServicePojo_comment servicePojo=new ServicePojo_comment("SDf","dsgf");
        Log.v("java123", "" + arrayList.toString());
        //  arrayList.add(servicePojo);

        MySingleton.getInstance(Service_feedback.this).addToRequestque(jsonArrayRequest);

    }

    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Snackbar.make(coordinator, "Press again to Exit...", Snackbar.LENGTH_LONG).show();

            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }


    }


}
