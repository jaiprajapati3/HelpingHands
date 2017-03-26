package com.example.hetal13.afinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
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
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
       /* TextDrawable drawable = TextDrawable.builder()
                .buildRound("H", generator.getRandomColor());
*/
        DisplayMetrics displayMetrics=new DisplayMetrics();
        android.view.ViewGroup.LayoutParams layoutParams=holder.img_id.getLayoutParams();
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

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
    public static class ContactView extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener{
        ImageView img_id;
        TextView name,mobile;
        ImageButton email;

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

        }
        @Override
        public  void onClick(View v){
            int position = getAdapterPosition();
            HistoryPojo contact = this.contacts.get(position);
            Log.v("here","ContactAdapter");
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:"+Uri.encode("7041764049".trim())));
            callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            v.getContext().startActivity(callIntent);
        }
    }
}
