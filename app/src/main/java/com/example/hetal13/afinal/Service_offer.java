package com.example.hetal13.afinal;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Service_offer extends Navigation implements View.OnClickListener{
TextView tvFromDate,tvToDate;
    Button btSubmit;
     String description;
    DatePickerDialog fromDatePicker,toDatePicker;
    SimpleDateFormat smpDateFormate,sendToServerFormate;
    String fromDate,toDate;
    EditText desc;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar progressBar;
    ArrayList<offerSPPojo> arrayList = new ArrayList<offerSPPojo>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_service_offer);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_service_offer, null, false);
        drawerLayout.addView(contentView, 0);
        progressBar= (ProgressBar) findViewById(R.id.progressBarOffer);
        desc= (EditText) findViewById(R.id.et_discription);
        getSupportActionBar().setTitle("Service offer");
        tvFromDate= (TextView) findViewById(R.id.tv_fromdate);
        tvToDate= (TextView) findViewById(R.id.tv_todate);
        btSubmit= (Button) findViewById(R.id.bt_submitoffer);
        fillOffer();
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=MyApplication.getInstance().getPrefManager().getemail();

                description=desc.getText().toString();
                if(description.equals("")){
                    desc.setError("Enter description");
                }
                if (tvFromDate.getText().toString().equals("")){
                    tvFromDate.setHint("Enter From Date");
                    tvFromDate.setHintTextColor(Color.RED);
                }
                if (tvToDate.getText().toString().equals("")){
                    tvToDate.setHint("Enter To Date");
                    tvToDate.setHintTextColor(Color.RED);
                }

                String url_offerAdd=UrlString.url_string+"/offer_Add.php?email="+email+"&from="+fromDate+"&to="+toDate+"&desc="+description;
                Log.v("response",url_offerAdd);
//                url_offerAdd.replaceAll("%", "%25");
               url_offerAdd=url_offerAdd.replace(" ","%20");
                String encodedUrl = null;
                try {
                     encodedUrl = java.net.URLEncoder.encode(url_offerAdd,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    Log.v("response23",e.getMessage());
                }
                StringRequest  stringRequest=new StringRequest(Request.Method.GET, url_offerAdd, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
    Log.v("response",response);
                        Toast.makeText(getBaseContext(),"Offer is submitted succcessfully",Toast.LENGTH_LONG).show();
                        desc.setText("");
                        tvToDate.setText("");
                        desc.setFocusable(false);
                        tvFromDate.setText("");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("response1",error.getMessage());
                    }
                });
                MySingleton.getInstance(getApplicationContext()).addToRequestque(stringRequest);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycleOffer);
//        String desc="Valid Offers";
//        String validDate="13/11/2017";
//        int i=0;
//        for (i=0;i<=2;i++) {
//            offerSPPojo offerSPPojo = new offerSPPojo(desc,validDate);
//            arrayList.add(offerSPPojo);
//        }
//        adapter = new OfferSPAdapter(Service_offer.this, arrayList);
//        recyclerView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(Service_offer.this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);



    }

    @Override
    protected void onStart() {
        super.onStart();
        tvFromDate.requestFocus();
        tvFromDate.setOnClickListener(this);
        tvToDate.requestFocus();
        tvToDate.setOnClickListener(this);

        smpDateFormate = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        sendToServerFormate = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        final Calendar calender=Calendar.getInstance();

        fromDatePicker=new DatePickerDialog(Service_offer.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newCalendar=Calendar.getInstance();
                newCalendar.set(year, month, dayOfMonth);
                fromDate=sendToServerFormate.format(newCalendar.getTime());
                tvFromDate.setText(smpDateFormate.format(newCalendar.getTime()));
                fromDatePicker.getDatePicker().setMinDate(calender.getTimeInMillis()-1000);
            }
        },calender.get(Calendar.YEAR), calender.get(Calendar.MONTH), calender.get(Calendar.DAY_OF_MONTH));

        toDatePicker=new DatePickerDialog(Service_offer.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newCalendar=Calendar.getInstance();
                newCalendar.set(year, month, dayOfMonth);
                toDate=sendToServerFormate.format(newCalendar.getTime());
                tvToDate.setText(smpDateFormate.format(newCalendar.getTime()));
                toDatePicker.getDatePicker().setMinDate(calender.getTimeInMillis()-1000);

            }
        },calender.get(Calendar.YEAR), calender.get(Calendar.MONTH), calender.get(Calendar.DAY_OF_MONTH));

}
    public void onBackPressed()
    {
        Intent i_rating=new Intent(Service_offer.this,Service_feedback.class);
        i_rating.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i_rating.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i_rating.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        startActivity(i_rating);
        return;
    }

    @Override
    public void onClick(View v) {
        if(v== tvFromDate)
        {
            fromDatePicker.show();
        }
        else if(v==tvToDate)
        {
            toDatePicker.show();
        }
    }
    public  void fillOffer(){
        String email=MyApplication.getInstance().getPrefManager().getemail();
        String url_offerList=UrlString.url_string+"/offer_showSp.php?email="+email;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url_offerList, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.GONE);
                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject objOffer=response.getJSONObject(i);
                        String todate=objOffer.getString("to");
                        int Id= Integer.parseInt(objOffer.getString("Id"));
                        String desc=objOffer.getString("desc");
                        offerSPPojo offerSPPojo = new offerSPPojo(desc,todate,Id);

                        arrayList.add(offerSPPojo);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                adapter = new OfferSPAdapter(Service_offer.this, arrayList);
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(Service_offer.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MySingleton.getInstance(this).addToRequestque(jsonArrayRequest);
    }
}
