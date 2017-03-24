package com.example.hetal13.afinal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000);
                    Intent intent = new Intent(getApplicationContext(),WelcomeActivity.class);
                    startActivity(intent);
                    finish();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     * Created by Hetal13 on 22-12-2016.
     */

   /* public static class ServiceAdapter_comment extends RecyclerView.Adapter<ServiceAdapter_comment.ContactView> {
        ArrayList<ServicePojo_comment> sp_detail =new ArrayList<ServicePojo_comment>();
        Context context;
        public ServiceAdapter_comment(Context context, ArrayList<ServicePojo_comment> sp_detail){
            this.sp_detail=sp_detail;
            this.context=context;
        }

        @Override
        public ContactView onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_feedback,parent,false);
            ContactView contactView = new ContactView(view,context,sp_detail);

            return contactView;
        }

        @Override
        public void onBindViewHolder(ContactView holder, int position) {
            final ServicePojo_comment sp_detail2 = sp_detail.get(position);
            try {
                holder.name_user.setText(sp_detail2.getName_user());
            }
            catch (Exception e){
               e.printStackTrace();
            }
            String description = sp_detail2.getFeed_user().replace("\\n", System.getProperty("line.separator"));
            holder.feed_user.setText(description);
        }

        @Override
        public int getItemCount() {
            return sp_detail.size();
        }
        public static class ContactView extends RecyclerView.ViewHolder{
            TextView name_user,feed_user;

            ArrayList<ServicePojo_comment> sp_detail = new ArrayList<ServicePojo_comment>();
            Context context;
            public ContactView(View view,Context context,ArrayList<ServicePojo_comment> sp_detail)
            {
                super(view);
                this.context=context;
                this.sp_detail=sp_detail;
                name_user= (TextView) view.findViewById(R.id.name_user);
                feed_user= (TextView) view.findViewById(R.id.feed_user);
      *//*          TitleName=(TextView)view.findViewById(R.id.TitleName);
                desc=(TextView) view.findViewById(R.id.desc);*//*
            }
        }
    }*/
}
