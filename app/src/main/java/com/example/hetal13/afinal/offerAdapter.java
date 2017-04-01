package com.example.hetal13.afinal;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;
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
        holder.spName.setText(contact.getspName());
        holder.ServiceType.setText(contact.getspType());
        holder.offerDesc.setText(contact.getofferDesc());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
    public static class ContactView extends RecyclerView.ViewHolder{

        TextView spName,ServiceType,offerDesc;
        ImageButton trash;
        ArrayList<offerPojo> contacts = new ArrayList<offerPojo>();
        Context context;
        public ContactView(View view,Context context,ArrayList<offerPojo> contacts)
        {
            super(view);
            this.context=context;
            this.contacts=contacts;
            spName=(TextView)view.findViewById(R.id.SPname);
            ServiceType=(TextView) view.findViewById(R.id.serviceType);
            offerDesc=(TextView) view.findViewById(R.id.offerDesc);
            trash=(ImageButton) view.findViewById(R.id.trash);
        }
    }
}