package com.example.hetal13.afinal;

import android.app.Service;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.hetal13.afinal.utility.ConnectionDetector;
import com.example.hetal13.afinal.utility.NoInternetConnectionDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Recycle_Cat_Details extends AppCompatActivity {
    String skill_id;
    String url_getSpList;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ServicePojo> arrayList = new ArrayList<ServicePojo>();
    ProgressBar progressbar_loadlist;
    Toolbar toolbar_detail;
    ImageView imgback;
    Drawable drawable;
    private int area_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle__cat__details);
        toolbar_detail= (Toolbar) findViewById(R.id.toolbar_rec);
        setSupportActionBar(toolbar_detail);
     //   getSupportActionBar().setTitle("ServiceProvider");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       /* getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.leftarrow);*/
        recyclerView=(RecyclerView) findViewById(R.id.recycle);
        progressbar_loadlist= (ProgressBar) findViewById(R.id.progressbar_loadlist);
        //drawable=this.getResources().getDrawable(R.drawable.leftarrow);
        /*toolbar_detail.setNavigationIcon(getResources().getDrawable(R.drawable.leftarrow));
        toolbar_detail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Home.class);
                startActivity(intent);
            }
        });*/
        // Intent intent;
        skill_id = String.valueOf(getIntent().getExtras().get("skill_id"));
        Log.v("Hello", skill_id);
        String Tooltitle;
        switch (skill_id){
            case "1":
            {
                Tooltitle="PG";
                break;
            }
            case "2":
            {
                Tooltitle="Tiffin Detail";
                break;
            }
            case "3":
            {
                Tooltitle="Maid";
                break;
            }
            case "4":
            {
                Tooltitle="Contractor";
                break;
            }
            case "5":
            {
                Tooltitle="Mechanic";
                break;
            }
            case "6":
            {
                Tooltitle="Security Guard";
                break;
            }
            default:{
                Tooltitle="Driver";
            }
        }
        getSupportActionBar().setTitle(Tooltitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Take area_id from the sharedPreference
        area_id = 1;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String timing = df.format(c.getTime());
        url_getSpList = "http://hetal1395.000webhostapp.com/List_Sp.php?skill_id=" + skill_id + "&area_id=" + area_id +"&timing="+timing;
        Log.v("at", "#$" + url_getSpList);

        //  progressBar.setVisibility(View.VISIBLE);

//    private void prepareService() {
//        int[] covers = new int[]{
//                R.drawable.call};
//    }

        final ConnectionDetector connectionDetector = new ConnectionDetector(this);

        if (connectionDetector.isConnectingToInternet()) {
            //  progressBar.setVisibility(View.VISIBLE);
            setDataInAdapter();
        }
        else {
            NoInternetConnectionDialog dialog=new NoInternetConnectionDialog();
            android.app.FragmentManager fm1 = getFragmentManager();
            dialog.show(fm1, "nointernet");
            final Button retry= (Button) findViewById(R.id.bt_retry);
            retry.setVisibility(View.VISIBLE);
            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (!connectionDetector.isConnectingToInternet()) {
                        //  progressBar.setVisibility(View.VISIBLE);
                        retry.setVisibility(View.VISIBLE);

                    }
                    else {
                        retry.setVisibility(View.GONE);
                    }
                    setDataInAdapter();




                }
            });
        }
    }

    public void setDataInAdapter(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url_getSpList, new Response.Listener<JSONArray>() {
            @Override

            public void onResponse(JSONArray response) {
                Log.v("atFinal", String.valueOf(response));
                progressbar_loadlist.setVisibility(View.GONE);
                for (int i = 0; i < response.length(); i++) {
                    Log.v("at", "here");

                    try {
                        JSONObject person = (JSONObject) response.get(i);
                        final String TitleName=person.getString("name");
                        String desc=person.getString("desc");
                        String user_id=person.getString("user_id");
                        //  int thumbnail=R.drawable.call_1;
                        // char c=TitleName.charAt(0);
                        int drawable=R.drawable.user;

                        ServicePojo contact=new ServicePojo(user_id,TitleName,desc,drawable);

                        arrayList.add(contact);
                     /*   btncall= (ImageButton)findViewById(R.id.btncall);
                        btncall.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(),TitleName,Toast.LENGTH_LONG).show();
    }
});*/

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
/*
                adapter = new ServiceAdapter(Recycle_Cat_Details.this,arrayList);

                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(Recycle_Cat_Details.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);*/
                adapter = new ServiceAdapter(Recycle_Cat_Details.this,arrayList);

                recyclerView.setHasFixedSize(true);
                RecyclerView.LayoutManager mLayoutManager;
                if(skill_id.equals("2")){
                     mLayoutManager = new GridLayoutManager(Recycle_Cat_Details.this, 1);
                }
                else {
                     mLayoutManager = new GridLayoutManager(Recycle_Cat_Details.this, 2);
                }
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("error",error.toString());

            }
        });
        MySingleton.getInstance(Recycle_Cat_Details.this).addToRequestque(jsonArrayRequest);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 2) {
            //dialog.show();
            Log.e("java1", "Code" + String.valueOf(resultCode));

            if (resultCode == 2) {
                Log.e("java1", "AgainResult");
                String sp_id = data.getStringExtra("sp_id");
                String name = data.getStringExtra("name");
                Log.e("java1", sp_id);
                String url_phone = UrlString.url_string + "/ratingCallDetail.php?sp_id" + sp_id;
//                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_phone, null, new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
////                            name = (String) response.get("name");
//                            phone[0] = response.getString("phone");
//                name[0] =response.getString("name");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.v("javaerror", error.toString());
//                    }
//                });
//                MySingleton.getInstance(getApplicationContext()).addToRequestque(jsonObjectRequest);
                AlertFragment fragment = new AlertFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putString("sp_id", sp_id);
                fragment.setArguments(bundle);
                android.app.FragmentManager fm = getFragmentManager();
                fragment.show(fm, "rating");
            }
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}

