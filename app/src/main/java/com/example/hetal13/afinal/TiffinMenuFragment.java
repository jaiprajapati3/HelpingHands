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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Lenovo on 3/11/2017.
 */

public class TiffinMenuFragment extends DialogFragment {
    int flag=1;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder alertdialog = new AlertDialog.Builder(getActivity());
        Log.v("TAG","at fragement");
        LayoutInflater inflater = (LayoutInflater) getActivity().getBaseContext()
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
        Bundle bundle=this.getArguments();
        if (bundle!=null) {
            if(bundle.containsKey("type")&& bundle.containsKey("menu")) {
                Log.v("Menu12","Bundle");
               String type=bundle.getString("type");
                String menu=bundle.getString("menu");
                Log.v("menu",menu);
                flag++;
                Log.v("Menu",menu);
                int position = 0;
                addmenu.setText(menu);
                if(type.equalsIgnoreCase("Lunch")){
                    position=1;
                }
                 if(type.equalsIgnoreCase("Dinners")){
                    position=2;
                 }
               Log.v("selected item",""+ selectmenu.getSelectedItemId());
                selectmenu.setSelection(position);
                //selectmenu.setSelection(position);
            }
        } else {
            Log.i("Activity2 Log", "asdf is null");

        }
        final AlertDialog dialog = alertdialog.create();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String type=selectmenu.getSelectedItem().toString();
                Log.v("java1",type);
                //String email=MyApplication.getInstance().getPrefManager().getemail();
                String email="vaidyameghna1996@gmail.com";
                String menu= String.valueOf(addmenu.getText());
                Date now = new Date();

                Date alsoNow = Calendar.getInstance().getTime();
                String nowAsString = new SimpleDateFormat("yyyy-MM-dd").format(now);

                String url_menu=UrlString.url_string+"/tiffinf_Add.php?type="+type+"&menu="+menu+"&email="+email+"&timing="+nowAsString+"&flag="+flag;
                url_menu = url_menu.replace(" ", "%20");
                TiffinMenuPojo tiffinMenuPojo=new TiffinMenuPojo(type,menu,nowAsString);


                Log.v("url",url_menu);
                final StringRequest stringRequest=new StringRequest(Request.Method.GET, url_menu, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("Response",response);
                        JSONObject obj= null;
                        try {
                            obj = new JSONObject(response);
                            String objResponse=obj.getString("success");
                            Log.v("Response1",objResponse);
                            Toast.makeText(view.getContext(),"Your Menu has been added",Toast.LENGTH_LONG);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    Log.v("type",error.getMessage());
                    }
                });
                MySingleton.getInstance(getActivity().getApplicationContext()).addToRequestque(stringRequest);
                dialog.dismiss();
//                TiffinMenuAdapter.class.notifyAll();

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
