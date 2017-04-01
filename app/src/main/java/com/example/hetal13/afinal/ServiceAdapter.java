package com.example.hetal13.afinal;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Hetal13 on 22-12-2016.
 */

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ContactView> {
    ArrayList<ServicePojo> sp_detail =new ArrayList<ServicePojo>();
    Context context;
    public ServiceAdapter(Context context, ArrayList<ServicePojo> sp_detail){
        this.sp_detail=sp_detail;
        this.context=context;
    }

    @Override
    public ContactView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_details,parent,false);
        ContactView contactView = new ContactView(view,context,sp_detail);

        return contactView;
    }


    @Override
    public void onBindViewHolder(ContactView holder, int position) {
        final ServicePojo sp_detail2 =sp_detail.get(position);
        holder.TitleName.setText(sp_detail2.getName());
        holder.id.setText(sp_detail2.getId());
//        holder.btncall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent call1 = new Intent();
//                call1.setAction(Intent.ACTION_DIAL);
//                call1.setData(Uri.parse("tel:" + sp_detail2.getId()));
//                context.startActivity(call1);
//                //call ws to call
//               // String title= ((TextView) v.findViewById(R.id.TitleName)).getText().toString();
//                //Toast.makeText(context,sp_detail2.getName(),Toast.LENGTH_LONG).show();
//            }
//        });



        String description = sp_detail2.getDesc().replace("\\n", System.getProperty("line.separator"));
        holder.desc.setText(description);
//        TextDrawable drawable = TextDrawable.builder()
//                .buildRound("H", Color.parseColor("#701b46"));
        //holder.thumbnail.setImageDrawable(sp_detail2.getThumbnail());
/*
int drawable1=Integer.parseInt(drawable.toString());
        holder.thumbnail.setImageResource(drawable1);
*/
        Glide.with(context).load(sp_detail2.getThumbnail()).into(holder.thumbnail);
   /*    holder.comment.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final Dialog dialog=new Dialog(context);
               dialog.setContentView(R.layout.comment_layout);
               ImageButton send= (ImageButton) dialog.findViewById(R.id.send);

               send.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       dialog.dismiss();
                   }
               });
               dialog.show();

           }
       });*/

    }

    @Override
    public int getItemCount() {
        return sp_detail.size();
    }
    public static class ContactView extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView TitleName,id,desc;
        /*  ImageButton btncall ,comment;
          RatingBar rating;*/
        ImageView thumbnail;
        ArrayList<ServicePojo> sp_detail = new ArrayList<ServicePojo>();
        Context context;
        public ContactView(View view,Context context,ArrayList<ServicePojo> sp_detail)
        {
            super(view);
            this.context=context;
            this.sp_detail=sp_detail;
            view.setOnClickListener(this);
            TitleName=(TextView)view.findViewById(R.id.title);
            id= (TextView) view.findViewById(R.id.textid);
            desc=(TextView) view.findViewById(R.id.desc);
            thumbnail= (ImageView) view.findViewById(R.id.thumbnail);
      /*      btncall= (ImageButton) view.findViewById(R.id.btncall);
            rating= (RatingBar) view.findViewById(R.id.rating);
            comment=(ImageButton)view.findViewById(R.id.btn2);*/
        }
        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();

            final ServicePojo contact = this.sp_detail.get(position);
            Log.e("TAG","DFdf");
            Log.e("TAG","Demo");
            //  Intent intent = new Intent(this.context,Service_Details.class);
            String sp_id=contact.getId();
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String timing = df.format(c.getTime());
            final String[] name = new String[1];
            final String[] email = new String[1];
            final String[] phone = new String[1];
            final String[] type = new String[1];
            final String[] desc = new String[1];
            final  double[] rating=new  double[1];
            final JSONObject[] obj = new JSONObject[1];
            String url_profile=UrlString.url_string+"/SpProfile.php?sp_id="+sp_id+"&timing="+timing+"&type=1";
            Log.e("url",url_profile);
            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url_profile, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.e("Error", String.valueOf(response));
                    try {
                        rating[0] = response.getDouble("rating");
                        obj[0] =response;
                        Log.e("Json#", String.valueOf(obj[0]));
                        Intent intent=new Intent(context,Form.class);
                        // intent.putExtra("desc",contact.getDesc());
                        intent.putExtra("sp_id",contact.getId());
                        intent.putExtra("JsonObj", String.valueOf(obj[0]));
                        Log.e("Json", String.valueOf(obj[0]));
                        ((Activity) context).startActivityForResult(intent, 2);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.v("Error",e.getMessage());
                    }



                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.v("Error",error.getMessage());
                }
            });
            MySingleton.getInstance(context).addToRequestque(jsonObjectRequest);

            //   MySingleton.getInstance(context).addToRequestque(jsonObjectRequest);
            Log.e("Error#","After");
//            Intent intent=new Intent(this.context,Form.class);
//            intent.putExtra("desc",contact.getDesc());
//            intent.putExtra("sp_id",contact.getId());
//            Log.e("Json", String.valueOf(obj[0]));
            //   Log.v("Js",name[0]);
//            intent.putExtra("JsonObj", String.valueOf(obj[0]));

            //     intent.putExtra("thumbnail", String.valueOf(contact.getThumbnail()));


//           ((Activity) context).startActivityForResult(intent, 2);
        }

    }
}

