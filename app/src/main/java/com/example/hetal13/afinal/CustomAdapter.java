package com.example.hetal13.afinal;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by BHOOMI on 16-12-2016.
 */

public class CustomAdapter extends ArrayAdapter<String> {
    private Activity activity;
    private ArrayList data;
   /* public Resources res;*/
    SpinnerModel tempValues=null;
    LayoutInflater inflater;
    public CustomAdapter(Registration activitySpinner,int textViewResourceId,
            ArrayList objects )
    {
        super(activitySpinner, textViewResourceId, objects);

        /********** Take passed values **********/
        activity = activitySpinner;
        data     = objects;
/*        res      = resLocal;*/

        /***********  Layout inflator to call external xml layout () **********************/
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    } @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
    public View getCustomView(int position, View convertView, ViewGroup parent) {

        /********** Inflate spinner_rows.xml file for each row ( Defined below ) ************/
        View row = inflater.inflate(R.layout.spinner_rows, parent, false);

        /***** Get each Model object from Arraylist ********/
        tempValues = null;
        tempValues = (SpinnerModel) data.get(position);

        /**TextView label        = (TextView)row.findViewById(R.id.company);
        TextView sub          = (TextView)row.findViewById(R.id.sub);
        ImageView companyLogo = (ImageView)row.findViewById(R.id.image);**/
        TextView label = (TextView) row.findViewById(R.id.tx_name);
        TextView label2= (TextView) row.findViewById(R.id.tx_id);
Log.v("Position","#"+position);
            // Set values for spinner each row
            /**label.setText(tempValues.getCompanyName());
            sub.setText(tempValues.getUrl());
            companyLogo.setImageResource(res.getIdentifier
                    ("com.androidexample.customspinner:drawable/"
                            + tempValues.getImage(),null,null));**/
            label.setText(tempValues.getName());
            label2.setText(String.valueOf(tempValues.getId()));
            Log.v("count",tempValues.getName());

        return row;
    }


}
