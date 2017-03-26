package com.example.hetal13.afinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Hetal13 on 26-03-2017.
 */

public class FormAdapter extends RecyclerView.Adapter<FormAdapter.FormView> {
    ArrayList<FormPojo> form =new ArrayList<FormPojo>();
    Context context;
    public  FormAdapter( Context context,ArrayList<FormPojo> form){
        this.form=form;
        this.context=context;
    }
    @Override
    public FormAdapter.FormView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardform,parent,false);
        FormAdapter.FormView formView = new FormAdapter.FormView(view,context,form);

        return formView;
    }
    @Override
    public void onBindViewHolder(FormAdapter.FormView holder, int position) {
        FormPojo formPojo =form.get(position);
        holder.title.setText(formPojo.getTitle());
        holder.subtitle.setText(formPojo.getSubtitle());
    }

    @Override
    public int getItemCount() {
        return form.size();
    }
    public static class FormView extends RecyclerView.ViewHolder{
        TextView title,subtitle;

        ArrayList<FormPojo> form = new ArrayList<FormPojo>();
        Context context;
        public FormView(View view,Context context,ArrayList<FormPojo> form)
        {
            super(view);
            this.context=context;
            this.form=form;
            title=(TextView)view.findViewById(R.id.titleForm);
            subtitle= (TextView) view.findViewById(R.id.subTitleForm);

        }
    }
}
