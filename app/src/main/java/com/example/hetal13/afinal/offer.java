package com.example.hetal13.afinal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class offer extends Navigation {
Toolbar toolbar_offer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        /*View contentView = inflater.inflate(R.layout.activity_offer, null, false);
        drawerLayout.addView(contentView, 0);*/
        setContentView(R.layout.activity_offer);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Offer");
//        ImageView imageView = (ImageView)findViewById(R.id.hetal);
//
//        Bitmap avatar = BitmapFactory.decodeResource(getResources(), R.drawable.hetal);
//        RoundedBitmapDrawable roundDrawable = RoundedBitmapDrawableFactory.create(getResources(), avatar);
//        roundDrawable.setCircular(true);
//        imageView.setImageDrawable(roundDrawable);
//        RoundImage(R.drawable.hetal,R.id.hetal);

        /*setContentView(R.layout.activity_offer);
        toolbar_offer=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar_offer);
        getSupportActionBar().setTitle("offer");*/


    }
//    public  void RoundImage(int DrawableId,int ImageId){
//        ImageView imageView = (ImageView)findViewById(ImageId);
//
//        Bitmap avatar = BitmapFactory.decodeResource(getResources(), DrawableId);
//        RoundedBitmapDrawable roundDrawable = RoundedBitmapDrawableFactory.create(getResources(), avatar);
//        roundDrawable.setCircular(true);
//        imageView.setImageDrawable(roundDrawable);
//
//    }
  /*  public void onBackPressed()
    {
        Intent i_offer=new Intent(offer.this,Home.class);
        i_offer.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i_offer.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i_offer.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        startActivity(i_offer);
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
