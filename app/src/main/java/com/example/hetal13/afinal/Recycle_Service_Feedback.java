package com.example.hetal13.afinal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Recycle_Service_Feedback extends AppCompatActivity {
RecyclerView recyclerView;
    String skill_id;
    private int area_id;
    String url_getSpList;
    String url_commentshow;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
   public ArrayList<ServicePojo_comment> arrayList = new ArrayList<ServicePojo_comment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycle__service__feedback);
        Log.v("tag","sdf");

        recyclerView=(RecyclerView) findViewById(R.id.recycle);

        int sp_id=4;
//get id from shared Preference
        url_commentshow="https://hetal1395.000webhostapp.com/comment_show.php?sp_id="+sp_id;
        Log.v("TAG",url_commentshow.toString());

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url_commentshow, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.v("TAG", String.valueOf(response));
                for(int i=0;i<response.length();i++){
                    try {

                        JSONObject comments=response.getJSONObject(i);
                    //    final String name=comments.getString("name");
                      //  Log.v("NAme",name);
                      //final  String comment=comments.getString("comment");
                      final ServicePojo_comment contact=new ServicePojo_comment();
                        contact.setFeed_user(comments.getString("comment"));
                        contact.setName_user(comments.getString("name"));
                        arrayList.add(contact);
                        Log.v("arraylist_contact",""+contact.getFeed_user());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                adapter = new ServiceAdapter_comment(Recycle_Service_Feedback.this, arrayList);

                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(Recycle_Service_Feedback.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("Tag","error");
            }
        });

   //       final ServicePojo_comment servicePojo=new ServicePojo_comment("SDf","dsgf");
        Log.v("arraylist",""+arrayList.toString());
     //  arrayList.add(servicePojo);

        MySingleton.getInstance(Recycle_Service_Feedback.this).addToRequestque(jsonArrayRequest);
        Log.v("Len",String.valueOf(arrayList.size()));


    }



}


