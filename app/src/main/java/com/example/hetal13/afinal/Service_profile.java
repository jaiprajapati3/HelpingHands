package com.example.hetal13.afinal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Service_profile extends Navigation implements View.OnClickListener {

    LinearLayout pglayout, driverlayout, mechaniclayout, tiffinlayout, securitylayout, maidlayout, contractorlayout;
    Button maidedit, pgedit, securityedit, mechanicedit, driveredit, tiffinedit, contractoredit, personaledit, save;
    EditText edname, edno, edemail, vacancyvalue, accostvalue, nonaccostvalue, tiffintype, tiffincost, tiffinmenu, contractorexp, contractorcost, maidexp, maidtime, maidcost, driverexp, drivercost, securityexp, securitytime, securitycost, mechanicexp, mechaniccost;
    boolean clicked;
    String user_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_service_profile);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_service_profile, null, false);
       drawerLayout.addView(contentView, 0);
        getSupportActionBar().setTitle("Service profile");

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        user_type = (String) bundle.get("SP");
        Log.v("Selected SP:", user_type);
        //All layouts hide show over here
        pglayout = (LinearLayout) findViewById(R.id.sp_profile_pglayout);
        tiffinlayout = (LinearLayout) findViewById(R.id.sp_profile_tiffinlayout);
        driverlayout = (LinearLayout) findViewById(R.id.sp_profile_driverlayout);
        contractorlayout = (LinearLayout) findViewById(R.id.sp_profile_contractorlayout);
        mechaniclayout = (LinearLayout) findViewById(R.id.sp_profile_mechaniclayout);
        securitylayout = (LinearLayout) findViewById(R.id.sp_profile_securitylayout);
        maidlayout = (LinearLayout) findViewById(R.id.sp_profile_maidlayout);

        maidedit = (Button) findViewById(R.id.maidedit);
        pgedit = (Button) findViewById(R.id.pgedit);
        driveredit = (Button) findViewById(R.id.driveredit);
        securityedit = (Button) findViewById(R.id.securityedit);
        mechanicedit = (Button) findViewById(R.id.mechanicedit);
        tiffinedit = (Button) findViewById(R.id.tiffinedit);
        contractoredit = (Button) findViewById(R.id.contractoredit);
        save = (Button) findViewById(R.id.save);
        personaledit = (Button) findViewById(R.id.personaledit);


        edname = (EditText) findViewById(R.id.edname);
        edno = (EditText) findViewById(R.id.edno);
        edemail = (EditText) findViewById(R.id.edemail);

        vacancyvalue = (EditText) findViewById(R.id.vacancyvalue);
        accostvalue = (EditText) findViewById(R.id.accostvalue);
        nonaccostvalue = (EditText) findViewById(R.id.nonaccostvalue);

        tiffintype = (EditText) findViewById(R.id.tiffintype);
        tiffincost = (EditText) findViewById(R.id.tiffincost);
        tiffinmenu = (EditText) findViewById(R.id.tiffinmenu);

        contractorexp = (EditText) findViewById(R.id.contractorexp);
        contractorcost = (EditText) findViewById(R.id.contractorcost);

        maidexp = (EditText) findViewById(R.id.maidexp);
        maidtime = (EditText) findViewById(R.id.maidtime);
        maidcost = (EditText) findViewById(R.id.maidcost);

        driverexp = (EditText) findViewById(R.id.driverexp);
        drivercost = (EditText) findViewById(R.id.drivercost);

        securityexp = (EditText) findViewById(R.id.securityexp);
        securitytime = (EditText) findViewById(R.id.securitytime);
        securitycost = (EditText) findViewById(R.id.securitycost);

        mechanicexp = (EditText) findViewById(R.id.mechanicexp);
        mechaniccost = (EditText) findViewById(R.id.mechaniccost);

        if(user_type.equals("1")){
            pglayout.setVisibility(View.VISIBLE);
            pgedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vacancyvalue.setEnabled(true);
                    vacancyvalue.setFocusable(true);
                    accostvalue.setEnabled(true);
                    accostvalue.setFocusable(true);
                    nonaccostvalue.setEnabled(true);
                    nonaccostvalue.setFocusable(true);
                }
            });

        }
        else if(user_type.equals("2")) {
            pglayout.setVisibility(View.INVISIBLE);
            tiffinlayout.setVisibility(View.VISIBLE);
            tiffinedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tiffintype.setEnabled(true);
                    tiffintype.setFocusable(true);
                    tiffincost.setEnabled(true);
                    tiffincost.setFocusable(true);
                    tiffinmenu.setEnabled(true);
                    tiffinmenu.setFocusable(true);
                }
            });
        }
        else if(user_type.equals("3")){
            pglayout.setVisibility(View.INVISIBLE);
            tiffinlayout.setVisibility(View.INVISIBLE);
            maidlayout.setVisibility(View.VISIBLE);
            maidedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    maidexp.setEnabled(true);
                    maidexp.setFocusable(true);
                    maidtime.setEnabled(true);
                    maidtime.setFocusable(true);
                    maidcost.setEnabled(true);
                    maidcost.setFocusable(true);
                }
            });

        }
        else if(user_type.equals("4")){
            pglayout.setVisibility(View.INVISIBLE);
            tiffinlayout.setVisibility(View.INVISIBLE);
            maidlayout.setVisibility(View.INVISIBLE);
            contractorlayout.setVisibility(View.VISIBLE);
            contractoredit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    contractorexp.setEnabled(true);
                    contractorexp.setFocusable(true);
                    contractorcost.setEnabled(true);
                    contractorcost.setFocusable(true);
                }
            });

        }
        else if(user_type.equals("5")){
            pglayout.setVisibility(View.INVISIBLE);
            tiffinlayout.setVisibility(View.INVISIBLE);
            maidlayout.setVisibility(View.INVISIBLE);
            contractorlayout.setVisibility(View.INVISIBLE);
            mechaniclayout.setVisibility(View.VISIBLE);
            mechanicedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mechanicexp.setEnabled(true);
                    mechanicexp.setFocusable(true);
                    mechaniccost.setEnabled(true);
                    mechaniccost.setFocusable(true);
                }
            });

        }
        else if(user_type.equals("6")){
            pglayout.setVisibility(View.INVISIBLE);
            tiffinlayout.setVisibility(View.INVISIBLE);
            maidlayout.setVisibility(View.INVISIBLE);
            contractorlayout.setVisibility(View.INVISIBLE);
            mechaniclayout.setVisibility(View.INVISIBLE);
            securitylayout.setVisibility(View.VISIBLE);
            securityedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    securityexp.setEnabled(true);
                    securityexp.setFocusable(true);
                    securitytime.setEnabled(true);
                    securitytime.setFocusable(true);
                    securitycost.setEnabled(true);
                    securitycost.setFocusable(true);
                }
            });

        }
        else if(user_type.equals("7")){
            pglayout.setVisibility(View.INVISIBLE);
            tiffinlayout.setVisibility(View.INVISIBLE);
            maidlayout.setVisibility(View.INVISIBLE);
            contractorlayout.setVisibility(View.INVISIBLE);
            mechaniclayout.setVisibility(View.INVISIBLE);
            securitylayout.setVisibility(View.INVISIBLE);
            driverlayout.setVisibility(View.VISIBLE);
            driveredit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    driverexp.setEnabled(true);
                    driverexp.setFocusable(true);
                    drivercost.setEnabled(true);
                    drivercost.setFocusable(true);
                }
            });

        }
        personaledit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edno.setEnabled(true);
                edno.setFocusable(true);
            }
        });

    }

    public void onBackPressed() {
        Intent i_rating = new Intent(Service_profile.this, Service_feedback.class);
        i_rating.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i_rating.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i_rating.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        startActivity(i_rating);
        return;
    }

    @Override
    public void onClick(View view) {
    }
}
