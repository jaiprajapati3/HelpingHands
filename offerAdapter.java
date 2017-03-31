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
import com.example.hetal13.afinal.R;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Hetal13 on 01-11-2016.
 */

public class offerAdapter extends RecyclerView.Adapter<offerAdapter.ContactView> {
    ArrayList<offerPojo> contacts =new ArrayList<offerPojo>();
    Context context;
    public  offerAdapter( Context context,ArrayList<offerPojo> contacts){
        this.contacts=contacts;
        this.context=context;
    }
    @Override
    public ContactView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardoffer,parent,false);
        ContactView contactView = new ContactView(view,context,contacts);
        return contactView;
    }
    @Override
    public void onBindViewHolder(ContactView holder, int position) {
        offerPojo contact =contacts.get(position);
        holder.img_id.setImageDrawable(contact.getImg_id());
        holder.spName.setText(contact.getspName());
        holder.ServiceType.setText(contact.getspType());
        holder.offerDesc.setText(contact.getofferDesc());
		holder.trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				contacts.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context,position+"Removed",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
    public static class ContactView extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener{
       
        TextView spName,ServiceType,offerDesc;
        ImageButton trash;
        ArrayList<offerPojo> contacts = new ArrayList<offerPojo>();
        Context context;
        public ContactView(View view,Context context,ArrayList<offerPojo> contacts)
        {
            super(view);
            this.context=context;
            this.contacts=contacts;
            view.setOnClickListener(this);
            spName=(TextView)view.findViewById(R.id.SPName);
            ServiceType=(TextView) view.findViewById(R.id.ServiceType);
			offerDesc=(TextView) view.findViewById(R.id.offerDesc);
			trash=(ImageButton) view.findViewById(R.id.trash);
        }
    }
}