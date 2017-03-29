package com.example.hetal13.afinal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

public class ServiceAdapter_comment extends RecyclerView.Adapter<ServiceAdapter_comment.ContactView> implements ItemTouchHelperAdapter {
    ArrayList<ServicePojo_comment> sp_detail = new ArrayList<ServicePojo_comment>();
public     Context context;

    public ServiceAdapter_comment(Context context, ArrayList<ServicePojo_comment> sp_detail) {
        this.sp_detail = sp_detail;
        this.context = context;
    }

    @Override
    public void onItemDismiss(int position) {
        sp_detail.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public ContactView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_feedback, parent, false);
        ContactView contactView = new ContactView(view, context, sp_detail);

        return contactView;
    }

    @Override
    public void onBindViewHolder(ContactView holder, final int position) {
        final ServicePojo_comment sp_detail2 = sp_detail.get(position);
        try {
            holder.name_user.setText(sp_detail2.getName_user());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String description = sp_detail2.getFeed_user().replace("\\n", System.getProperty("line.separator"));
        holder.feed_user.setText(description);
        holder.trash.setOnClickListener(new View.OnClickListener() {
            String Email = "reenajani@gmail.com";
     //       String Email=sp_detail2.getEmail();
            String customerId = sp_detail2.getCustomerId();
            String url_delete = UrlString.url_string + "/delete_comment.php?email=" + Email + "&customer_id=" + customerId;

            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url_delete, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("JAva", response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("JAVA", error.getMessage());
                    }
                });
                Toast toast = Toast.makeText(v.getContext(), "Item Deleted", Toast.LENGTH_SHORT);
                toast.show();

                MySingleton.getInstance(context).addToRequestque(stringRequest);
                sp_detail.remove(position);
                notifyDataSetChanged();

            }
        });
        holder.feedEmail.setOnClickListener(new View.OnClickListener() {
       //     String Email="reenajani@gmail.com";
             String Email=sp_detail2.getEmail();
            @Override

            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" +Email));
                v.getContext().startActivity(Intent.createChooser(emailIntent,"Send Mail to"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return sp_detail.size();
    }

    public static class ContactView extends RecyclerView.ViewHolder {
        TextView name_user, feed_user;
        ImageButton trash,feedEmail;
        ArrayList<ServicePojo_comment> sp_detail = new ArrayList<ServicePojo_comment>();
        Context context;

        public ContactView(View view, Context context, ArrayList<ServicePojo_comment> sp_detail) {
            super(view);
            this.context = context;
            this.sp_detail = sp_detail;
            name_user = (TextView) view.findViewById(R.id.name_user);
            feed_user = (TextView) view.findViewById(R.id.feed_user);
            trash = (ImageButton) view.findViewById(R.id.trash);
            feedEmail= (ImageButton) view.findViewById(R.id.emailFeedBack);
      /*          TitleName=(TextView)view.findViewById(R.id.TitleName);
                desc=(TextView) view.findViewById(R.id.desc);*/
        }
    }
}
