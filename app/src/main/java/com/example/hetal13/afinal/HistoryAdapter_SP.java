package com.example.hetal13.afinal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
        TextDrawable drawable = TextDrawable.builder()
                .buildRound("H",generator.getRandomColor());
        holder.img_id.setImageDrawable(drawable);
        holder.name.setText(contact.getName());
        holder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto: hetalshah027@gmail.com"));
                v.getContext().startActivity(Intent.createChooser(emailIntent,"Send Mail to"));
            }
        });
        holder.mobile.setText(contact.getMobile());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
    public static class ContactView extends RecyclerView.ViewHolder{
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
            img_id=(ImageView) view.findViewById(R.id.img_person);
            name=(TextView)view.findViewById(R.id.person_name);
            email= (ImageButton) view.findViewById(R.id.person_email);
            mobile=(TextView) view.findViewById(R.id.person_contact);

        }
    }
}
