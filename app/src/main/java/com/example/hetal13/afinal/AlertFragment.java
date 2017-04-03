package com.example.hetal13.afinal;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by Lenovo on 2/11/2017.
 * This is a Dialog fragment
 */

public class AlertFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder alertdialog = new AlertDialog.Builder(getActivity());
        Log.v("TAG", "at fragement");
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.setContentView(R.layout.alertfragment);
        LayoutInflater inflater = (LayoutInflater) getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.alertfragment, null);
        alertdialog.setView(v);
        String name = getArguments().getString("name");
        final String sp_id = getArguments().getString("sp_id");
        final RatingBar ratingBar = (RatingBar) v.findViewById(R.id.rating);
        Button notnow = (Button) v.findViewById(R.id.notnow);
        final Button rate = (Button) v.findViewById(R.id.rate);
        alertdialog.setCancelable(false);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        final AlertDialog dialog = alertdialog.create();
        TextView tv = (TextView) v.findViewById(R.id.tvheading);
        tv.setText("Like the Service of " + name + "?");
        String email = MyApplication.getInstance().getPrefManager().getemail();
        notnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float rat = ratingBar.getRating();
                String email = MyApplication.getInstance().getPrefManager().getemail();
                String url_rating = UrlString.url_string + "/ratingInsert.php?sp_id=" + sp_id + "&email=" + email + "&rating=" + rat;
                Log.v("java12", url_rating);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url_rating, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                MySingleton.getInstance(getActivity().getApplicationContext()).addToRequestque(stringRequest);
                dialog.dismiss();
            }
        });
        return dialog;

    }
}
