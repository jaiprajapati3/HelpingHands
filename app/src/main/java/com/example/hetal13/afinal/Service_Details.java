package com.example.hetal13.afinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Service_Details extends AppCompatActivity {
    TextView desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service__details);
       desc=(TextView)findViewById(R.id.desc);
        desc.setText("Mobile:"+getIntent().getStringExtra("desc"));
    }
}
