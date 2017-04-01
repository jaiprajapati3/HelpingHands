package com.example.hetal13.afinal;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Service_offer extends Navigation{
TextView tvFromDate,tvToDate,btSubmit;
    DatePickerDialog fromDatePicker,toDatePicker;
    SimpleDateFormat smpDateFormate,sendToServerFormate;
    String fromDate,toDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_service_offer);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_service_offer, null, false);
        drawerLayout.addView(contentView, 0);
        getSupportActionBar().setTitle("Service offer");
        tvFromDate= (TextView) findViewById(R.id.tv_fromdate);
        tvToDate= (TextView) findViewById(R.id.tv_todate);




    }

    @Override
    protected void onStart() {
        super.onStart();
        tvFromDate.requestFocus();
        //tvFromDate.setOnClickListener(this);
        tvToDate.requestFocus();
        //tvToDate.setOnClickListener(this);

        smpDateFormate = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        sendToServerFormate = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        final Calendar calender=Calendar.getInstance();
        tvFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

fromDatePicker.show();
            }
        });
        fromDatePicker=new DatePickerDialog(Service_offer.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newCalendar=Calendar.getInstance();
                newCalendar.set(year, month, dayOfMonth);
                fromDate=sendToServerFormate.format(newCalendar.getTime());
                tvFromDate.setText(smpDateFormate.format(newCalendar.getTime()));
            }
        },calender.get(Calendar.YEAR), calender.get(Calendar.MONTH), calender.get(Calendar.DAY_OF_MONTH));
       tvToDate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              toDatePicker.show();

           }

       });
        toDatePicker=new DatePickerDialog(Service_offer.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newCalendar=Calendar.getInstance();
                newCalendar.set(year, month, dayOfMonth);
                toDate=sendToServerFormate.format(newCalendar.getTime());
                tvToDate.setText(smpDateFormate.format(newCalendar.getTime()));
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

   }
