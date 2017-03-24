package com.example.hetal13.afinal;

import android.content.Context;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Home extends Navigation {
    PieChart pieChart ;
    ArrayList<Entry> entries ;
    ArrayList<String> PieEntryLabels ;
    PieDataSet pieDataSet ;
    PieData pieData ;
    private RecyclerView recyclerView;
    private Service_CatAdapter adapter;
    private List<Service_ListPojo> catlist;
 int[] arr={0,0,0,0,0,0,0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_home, null, false);
        drawerLayout.addView(contentView, 0);
        getSupportActionBar().setTitle("Helping Hands");
        //hetal

        pieChart = (PieChart) findViewById(R.id.chart1);
        pieChart.setDescription("");

        entries = new ArrayList<>();

        PieEntryLabels = new ArrayList<String>();

        AddValuesToPIEENTRY();

        AddValuesToPieEntryLabels();

        pieDataSet = new PieDataSet(entries, "");

        pieData = new PieData(PieEntryLabels, pieDataSet);


        pieDataSet.setColors(ColorTemplate_Demo.COLORFUL_COLORS);
       // pieDataSet.setDrawValues(false);
        Legend l = pieChart.getLegend();
        l.setEnabled(false);

        l.setXEntrySpace(0);
        l.setYEntrySpace(5);
        pieChart.setData(pieData);

        pieChart.animateXY(3000,3000);

        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        catlist = new ArrayList<>();
        adapter = new Service_CatAdapter(this, catlist);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new Home.GridSpacingItemDecoration(2, dpToPx(2), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareAlbums();
    }

    private void prepareAlbums() {

        final int[] covers = new int[]{
                R.drawable.pg11,
                R.drawable.tiffin11,
                R.drawable.maid11,
                R.drawable.contractor11,
                R.drawable.mechanic,
                R.drawable.s1,
                R.drawable.driver};
        String url_piechart=UrlString.url_string+"/PieChart.php?area_id=1";

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url_piechart, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.v("java123", response.toString());
                for (int i = 0; i < response.length(); i++) {
                    try {
                        int j = response.getInt(i);
                        Log.v("javaJ", String.valueOf(j));
                        int ans = response.getInt(++i);
                        Log.v("javaAns", String.valueOf(ans));
                        arr[--j] = ans;
                        //  entries.add(new BarEntry(5,0));
                        //   entries.add(new BarEntry(arr[j],j));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.v("java", e.getMessage());
                    }


                }
                Service_ListPojo a = new Service_ListPojo(covers[0],"P.G.",1,arr[0]);
                catlist.add(a);

                a = new Service_ListPojo(covers[1],"Tiffin",2,arr[1]);
                catlist.add(a);

                a = new Service_ListPojo(covers[2],"Maid",3,arr[2]);
                catlist.add(a);

                a = new Service_ListPojo(covers[3],"Contractor",4,arr[3]);
                catlist.add(a);

                a = new Service_ListPojo(covers[4],"Mechanic",5,arr[4]);

                catlist.add(a);

                a = new Service_ListPojo(covers[5],"Security",6,arr[5]);
                catlist.add(a);

                a = new Service_ListPojo(covers[6],"Driver",7,arr[6]);
                catlist.add(a);

                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("java123",error.getMessage());
            }
        });
        MySingleton.getInstance(getApplicationContext()).addToRequestque(jsonArrayRequest);
Log.v("javaAns1", String.valueOf(arr[0]));


    }
    public void AddValuesToPIEENTRY(){

        entries.add(new BarEntry(5, 0));
        entries.add(new BarEntry(5, 1));
        entries.add(new BarEntry(5, 2));
        entries.add(new BarEntry(5, 3));
        entries.add(new BarEntry(5, 4));
        entries.add(new BarEntry(5, 5));
        entries.add(new BarEntry(5, 6));

    }
    public void AddValuesToPieEntryLabels(){

        PieEntryLabels.add("P.G.");
        PieEntryLabels.add("Tiffin");
        PieEntryLabels.add("Maid");
        PieEntryLabels.add("Contractor");
        PieEntryLabels.add("Mechanic");
        PieEntryLabels.add("Security");
        PieEntryLabels.add("Driver");
}
    //end hetal
    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Snackbar.make(coordinator,"Press again to Exit...", Snackbar.LENGTH_LONG).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }


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
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
