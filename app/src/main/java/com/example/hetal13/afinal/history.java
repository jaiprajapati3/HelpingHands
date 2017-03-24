package com.example.hetal13.afinal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

public class history extends Navigation {
Toolbar toolbar_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
       /* View contentView = inflater.inflate(R.layout.activity_history, null, false);
        drawerLayout.addView(contentView, 0);*/
        setContentView(R.layout.activity_history);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("History");

        /*setContentView(R.layout.activity_history);
        toolbar_history=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar_history);
        getSupportActionBar().setTitle("history");*/


    }
   /* public void onBackPressed()
    {
        Intent i_history=new Intent(history.this,Home.class);
        i_history.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i_history.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i_history.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        startActivity(i_history);
        return;
    }*/
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
}
