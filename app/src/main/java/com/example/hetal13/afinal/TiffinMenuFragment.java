package com.example.hetal13.afinal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by Lenovo on 3/11/2017.
 */

public class TiffinMenuFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder alertdialog = new AlertDialog.Builder(getActivity());
        Log.v("TAG","at fragement");
        LayoutInflater inflater = (LayoutInflater) getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.fragment_tiffinlayout, null);
        alertdialog.setView(v);
        alertdialog.setCancelable(false);
        final EditText addmenu= (EditText) v.findViewById(R.id.et_addmenu);
        Button add= (Button) v.findViewById(R.id.bt_addmenu);
        ImageButton cancle= (ImageButton) v.findViewById(R.id.bt_canclemenu);
        final Spinner selectmenu= (Spinner) v.findViewById(R.id.sp_chooseMenu);
        ArrayAdapter adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.menu));
        selectmenu.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        final AlertDialog dialog = alertdialog.create();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type=selectmenu.getSelectedItem().toString();
                Log.v("type",type);
                String email=MyApplication.getInstance().getPrefManager().getemail();
                String menu= String.valueOf(addmenu.getText());
                String url_menu=UrlString.url_string+"/tiffin_update.php?type="+type+"&menu="+menu+"&email="+email;
                Log.v("type",url_menu);
                StringRequest stringRequest=new StringRequest(Request.Method.GET, url_menu, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    Log.v("type",error.getMessage());
                    }
                });
                MySingleton.getInstance(getActivity().getApplicationContext()).addToRequestque(stringRequest);
                dialog.dismiss();

            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });




        return dialog;

    }
}
