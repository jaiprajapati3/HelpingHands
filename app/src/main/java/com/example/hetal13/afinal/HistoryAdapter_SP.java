package com.example.hetal13.afinal;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.hetal13.afinal.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.hetal13.afinal.R.drawable.incoming;
import static com.example.hetal13.afinal.R.drawable.outgoing;

/**
 * Created by Hetal13 on 01-11-2016.
 */

public class HistoryAdapter_SP extends RecyclerView.Adapter<HistoryAdapter_SP.ContactView> {
    ArrayList<HistoryPojo> contacts =new ArrayList<HistoryPojo>();
    Context context;
    ColorGenerator generator = ColorGenerator.MATERIAL;


    public  HistoryAdapter_SP( Context context,ArrayList<HistoryPojo> contacts){
        this.contacts=contacts;
        this.context=context;

    }
    @Override
    public ContactView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spcardhistory,parent,false);
        ContactView contactView = new ContactView(view,context,contacts);

        return contactView;
    }
    @Override
    public void onBindViewHolder(ContactView holder, int position) {
        HistoryPojo contact =contacts.get(position);
        int flag=contact.getFlag();

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        DisplayMetrics displayMetrics=new DisplayMetrics();
        ViewGroup.LayoutParams layoutParams=holder.img_id.getLayoutParams();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height=displayMetrics.heightPixels;
        int width=displayMetrics.widthPixels;
        int screenSize = context.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
        String toastMsg;
        switch(screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                layoutParams.width = (width/13)*2;
                layoutParams.height = (height/23)*2;
                holder.img_id.setLayoutParams(layoutParams);
                toastMsg = "Large screen";
                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                layoutParams.width = (width/14)*2;
                layoutParams.height = (height/25)*2;
                holder.img_id.setLayoutParams(layoutParams);
                toastMsg = "Normal screen";
                break;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                toastMsg = "Small screen";
                break;
            default:
                toastMsg = "Screen size is neither large, normal or small";
        }
        holder.img_id.setImageDrawable(contact.getImg_id());
        holder.name.setText(contact.getName());
        holder.mobile.setText(contact.getMobile());
        holder.tvDate.setText(contact.getDate());
        holder.tvTime.setText(contact.getTime());
        if (flag ==1) holder.outgoing.setVisibility(View.VISIBLE);
        if (flag==2)holder.incoming.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
    public static class ContactView extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener{
        ImageView img_id;
        TextView name,mobile,tvDate,tvTime;
        ImageButton email;
        ImageView incoming,outgoing;
        CardView cardViewHistorySP;
        ArrayList<HistoryPojo> contacts = new ArrayList<HistoryPojo>();
        Context context;
        public ContactView(View view,Context context,ArrayList<HistoryPojo> contacts)
        {
            super(view);
            this.context=context;
            this.contacts=contacts;
            view.setOnClickListener(this);
            img_id=(ImageView) view.findViewById(R.id.img_person);
            name=(TextView)view.findViewById(R.id.person_name);
            mobile=(TextView) view.findViewById(R.id.person_contact);
            incoming= (ImageView) view.findViewById(R.id.incoming);
            outgoing=(ImageView)view.findViewById(R.id.outgoing);
            tvDate= (TextView) view.findViewById(R.id.callDate);
            tvTime= (TextView) view.findViewById(R.id.callTime);
            cardViewHistorySP= (CardView) view.findViewById(R.id.cardViewHistorySP);

        }
        @Override
        public  void onClick(View v){
            Log.v("Hello","Hetal");
            int position = getAdapterPosition();

            HistoryPojo contact = this.contacts.get(position);
            Log.v("here","ContactAdapter");
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+Uri.encode(contact.getMobile())));
            callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

                return;
            }
            //startActivityForResult(callIntent, 2);

            context.startActivity(callIntent);
        }
    }
}
