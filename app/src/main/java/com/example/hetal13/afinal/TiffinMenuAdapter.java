package com.example.hetal13.afinal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
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
import com.example.hetal13.afinal.R;

import java.util.ArrayList;

/**
 * Created by Hetal13 on 01-11-2016.
 */

public class TiffinMenuAdapter extends RecyclerView.Adapter<TiffinMenuAdapter.ContactView> {
    ArrayList<TiffinMenuPojo> contacts =new ArrayList<TiffinMenuPojo>();
    Context context;

    public  TiffinMenuAdapter( Context context,ArrayList<TiffinMenuPojo> contacts){
        this.contacts=contacts;
        this.context=context;
    }
    @Override
    public ContactView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tiffinmenucard,parent,false);
        ContactView contactView = new ContactView(view,context,contacts);

        return contactView;
    }

    @Override
    public void onBindViewHolder(final ContactView holder, final int position) {
          TiffinMenuPojo contact =contacts.get(position);
        contact=contacts.get(position);

        holder.name.setText(contact.getCategory());
        holder.email.setText(contact.getMenu());
        holder.deletemenu.setOnClickListener(new View.OnClickListener() {
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
    public static class ContactView extends RecyclerView.ViewHolder{
        TextView name,email;
        ImageButton deletemenu;

        ArrayList<TiffinMenuPojo> contacts = new ArrayList<TiffinMenuPojo>();
        Context context;
        public ContactView(View view,Context context,ArrayList<TiffinMenuPojo> contacts)
        {
            super(view);
            this.context=context;
            this.contacts=contacts;

            name=(TextView)view.findViewById(R.id.category);
            email=(TextView)view.findViewById(R.id.menu);
            deletemenu= (ImageButton) view.findViewById(R.id.deletemenu);




        }
    }
}
