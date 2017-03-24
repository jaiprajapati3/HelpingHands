package com.example.hetal13.afinal.utility;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.hetal13.afinal.*;

/**
 * Created by Lenovo on 3/12/2017.
 */

public class NoInternetConnectionDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder alertdialog = new AlertDialog.Builder(getActivity());
        Log.v("TAG","at fragement");
        LayoutInflater inflater = (LayoutInflater) getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.fragment_nointernetconnectiondialog, null);
        alertdialog.setView(v);
        alertdialog.setCancelable(false);
        final AlertDialog dialog = alertdialog.create();
        Button ok= (Button) v.findViewById(R.id.bt_noconnection_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    return dialog;
    }
}
