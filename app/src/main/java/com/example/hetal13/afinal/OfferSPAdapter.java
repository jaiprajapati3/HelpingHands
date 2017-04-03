package com.example.hetal13.afinal;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
/**
 * Created by Hetal13 on 01-11-2016.
 */

public class OfferSPAdapter extends RecyclerView.Adapter<OfferSPAdapter.ContactView> {
    ArrayList<offerSPPojo> contacts =new ArrayList<offerSPPojo>();
    Context context;
    public  OfferSPAdapter( Context context,ArrayList<offerSPPojo> contacts){
        this.contacts=contacts;
        this.context=context;
    }
    @Override
    public ContactView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardspoffer,parent,false);
        ContactView contactView = new ContactView(view,context,contacts);
        return contactView;
    }
    @Override
    public void onBindViewHolder(ContactView holder, final int position) {
        final offerSPPojo contact =contacts.get(position);
        holder.tvofferSPDesc.setText(contact.getDesc());
        holder.tvvalidTillDate.setText(contact.getDate());
        holder.trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contacts.remove(position);
                notifyDataSetChanged();
               // int Id=contacts.get(position).getId();
                int Id=contact.getId();
                String email=MyApplication.getInstance().getPrefManager().getemail();

                String urlOfferDelete=UrlString.url_string+"/offer_delete.php?email="+email+"&Id="+Id;
                Log.v("response",urlOfferDelete);
                StringRequest stringRequest=new StringRequest(Request.Method.GET, urlOfferDelete, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("respsonse",response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
Log.v("response",error.getMessage());
                    }
                });
               MySingleton.getInstance(context).addToRequestque(stringRequest);
                Toast.makeText(v.getContext(),"Item Removed",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
    public static class ContactView extends RecyclerView.ViewHolder{

        TextView tvvalidTillDate,tvofferSPDesc;
        ImageButton trash;
        ArrayList<offerSPPojo> contacts = new ArrayList<offerSPPojo>();
        Context context;
        public ContactView(View view,Context context,ArrayList<offerSPPojo> contacts)
        {
            super(view);
            this.context=context;
            this.contacts=contacts;
            tvofferSPDesc=(TextView) view.findViewById(R.id.tvofferSPDesc);
            tvvalidTillDate=(TextView) view.findViewById(R.id.tvvalidTillDate);
            trash=(ImageButton) view.findViewById(R.id.trash);
        }
    }
}