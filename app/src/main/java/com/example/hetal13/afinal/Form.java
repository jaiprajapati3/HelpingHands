package com.example.hetal13.afinal;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.TextDrawable;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Form extends AppCompatActivity {

    ImageButton btnCall, send, button;
    ListView listView;
    String sp_id;
    RatingBar ratingBar;
    TextView sp_name, sp_type, sp_desc;
    String url_commentshow, url_commentedit;
    String name = "";
    EditText comment_editText;
    JSONObject obj;
    ProgressBar progressBar, progressBarLoadFeedback;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<FormPojo> arrayList = new ArrayList<FormPojo>();
    private PopupWindow popupWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Service Details");

        //  final String[] name = new String[1];
        final String[] email = new String[1];
        final String[] phone = new String[1];
        //  final double[] rating = new double[1];
        final String desc;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*progressBar= (ProgressBar) findViewById(R.id.progressbar_formload);
        progressBar.setVisibility(View.VISIBLE);*/
        Intent intent = getIntent();
        sp_id = intent.getStringExtra("sp_id");
        String JsonString = intent.getStringExtra("JsonObj");
        Log.e("J", JsonString);

        ratingBar = (RatingBar) findViewById(R.id.formRating);
        sp_name = (TextView) findViewById(R.id.Sp_name);
        //sp_desc= (TextView) findViewById(R.id.Sp_Desc);
        sp_type = (TextView) findViewById(R.id.Sp_type);
        ImageView image = (ImageView) findViewById(R.id.image_view);
        recyclerView = (RecyclerView) findViewById(R.id.recycleForm);
        //hetal recyclerView
        try {
            JSONObject obj = new JSONObject(JsonString);
            name = obj.getString("name");
            String type = obj.getString("type");
            phone[0] = obj.getString("phone");
            sp_name.setText(name);
            sp_type.setText(type);
            char letter = name.charAt(0);
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound(String.valueOf(letter).toUpperCase(), Color.parseColor("#701b46"));
            DisplayMetrics displayMetrics = new DisplayMetrics();
            android.view.ViewGroup.LayoutParams layoutParams = image.getLayoutParams();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
            int screenSize = getResources().getConfiguration().screenLayout &
                    Configuration.SCREENLAYOUT_SIZE_MASK;

            image.setImageDrawable(drawable);
            double rating = obj.getDouble("rating");
            ratingBar.setRating((float) rating);
            desc = obj.getString("desc");
            String[] arr = desc.split("@");
            for (int i = 1; i < arr.length - 1; i = i + 2) {
                FormPojo form = new FormPojo(arr[i], arr[i + 1]);
                arrayList.add(form);

                Log.e("Json", arr[i]);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String title = "Cost";
        String subtitle = "5000";
        int i = 0;
//        for (i = 0; i < 14; i++) {
//            FormPojo form = new FormPojo(title, subtitle);
//            arrayList.add(form);
//        }
        adapter = new FormAdapter(Form.this, arrayList);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(Form.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        //TextDrawable drawable = TextDrawable.builder()
        //    .buildRound("H", Color.parseColor("#701b46"));
        /*String url_profile=UrlString.url_string+"/SpProfile.php?sp_id="+sp_id;
        Log.v("java11",url_profile);*/
/*        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url_profile, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //progressBar.setVisibility(View.GONE);
                    name = response.getString("name");
                    Log.v("java11", response.toString());
                    obj = response;
                    email[0] = response.getString("email");
                    phone[0] = response.getString("phone");
                    String type = response.getString("type");
                    String desc = response.getString("desc");
                    String description = desc.replace("\\n", System.getProperty("line.separator"));
                    Log.v("java11", email[0]);
                    double rating = 0;
                    Log.v("java12", String.valueOf(rating));
                    rating = response.getDouble("rating");
                    ratingBar.setRating((float) rating);

                    sp_name.setText(name);
                    sp_type.setText(type);
                    Log.v("java12", sp_name.getText().toString());
                    sp_desc.setText(description);
                    String letter = String.valueOf(name.charAt(0));
                    ImageView image = (ImageView) findViewById(R.id.image_view);

                    Log.e("java123", letter);
                    TextDrawable drawable = TextDrawable.builder()
                           .buildRound(String.valueOf(letter).toUpperCase(), Color.parseColor("#701b46"));
                     DisplayMetrics displayMetrics=new DisplayMetrics();
                   android.view.ViewGroup.LayoutParams layoutParams=image.getLayoutParams();
                    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    int height=displayMetrics.heightPixels;
                    int width=displayMetrics.widthPixels;
                    int screenSize = getResources().getConfiguration().screenLayout &
                            Configuration.SCREENLAYOUT_SIZE_MASK;

                    String toastMsg;
                    switch(screenSize) {
                        case Configuration.SCREENLAYOUT_SIZE_LARGE:
                            layoutParams.width = (width/9)*3;
                            layoutParams.height = (height/10)*2;
                            image.setLayoutParams(layoutParams);
                            toastMsg = "Large screen";
                            break;
                        case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                            layoutParams.width = (width/9)*3;
                            layoutParams.height = (height/11)*2;
                            image.setLayoutParams(layoutParams);
                            toastMsg = "Normal screen";
                            break;
                        case Configuration.SCREENLAYOUT_SIZE_SMALL:
                            toastMsg = "Small screen";
                            break;
                        default:
                            toastMsg = "Screen size is neither large, normal or small";
                    }
                    Toast.makeText(getBaseContext(), toastMsg, Toast.LENGTH_LONG).show();
                    image.setImageDrawable(drawable);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.v("java11",e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("java11",error.getMessage());
            }
        });
        MySingleton.getInstance(getApplicationContext()).addToRequestque(jsonObjectRequest);
        Log.e("java123",name);
        char letter='B';*/
        //char letter=name.charAt(0);

        //  Log.e("java1",email[0]);
//        try {
//            Log.e("java1",obj.getString("name"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Log.e("java1",e.toString());
//        }
        //  sp_name.setText(name);
        Log.e("java1", sp_name.getText().toString());
        //image.setImageDrawable(drawable);
        button = (ImageButton) findViewById(R.id.comment);
        btnCall = (ImageButton) findViewById(R.id.call);
        PhoneCallListener phoneListener = new PhoneCallListener();
        TelephonyManager telephonyManager = (TelephonyManager) this
                .getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);

                //sharedpreference
                // callIntent.setData(Uri.parse("tel:9428739620"));
                // Log.v("java12", String.valueOf(Uri.parse("tel:9428739620")));
                callIntent.setData(Uri.parse("tel:" + phone[0]));
                Log.v("java12", String.valueOf(Uri.parse("tel:" + phone[0])));

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                //startActivityForResult(callIntent, 2);
                startActivity(callIntent);
                Log.v("Phone", "demo");

            }

        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("tag", "Hetal");
                //Intent intent=new Intent(Form.this,Recycle_Service_Feedback.class);
                /// Intent intent=new Intent(Form.this,CommentActivity.class);
                // startActivity(intent);
                onShowPopup(v);
            }
        });
    }

    public void DeleteCallLogByNumber(String number) {
        String queryString = "NUMBER=" + number;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        this.getContentResolver().delete(CallLog.Calls.CONTENT_URI, queryString, null);
    }

    public void onShowPopup(View v) {


        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.comment_layout, null, false);
        progressBarLoadFeedback = (ProgressBar) view.findViewById(R.id.pbFeedback);
        progressBarLoadFeedback.setVisibility(View.VISIBLE);
        AlphaAnimation animation = new AlphaAnimation(0.4f, 0.1f);
        animation.setDuration(200);
        RelativeLayout mainlayout = (RelativeLayout) view.findViewById(R.id.mainlayout);
        mainlayout.startAnimation(animation);
        listView = (ListView) view.findViewById(R.id.commentlist);
        send = (ImageButton) view.findViewById(R.id.send);
        setList(listView, progressBarLoadFeedback);
        comment_editText = (EditText) view.findViewById(R.id.write_comment);
//        Log.v("TAG",comment_editText.getText().toString());
//        String comment_edit=comment_editText.getText().toString();
//        Log.v("TAG",comment_edit);
//        // send button onclick method
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sp_id = String.valueOf(4);
                String cust_email;
                cust_email = MyApplication.getInstance().getPrefManager().getemail();
                String comment_edit = comment_editText.getText().toString();
                Log.v("TAG", comment_edit);
                url_commentedit = "http://hetal1395.000webhostapp.com/comment_enter.php?sp_id=" + sp_id + "&customer_email=" + cust_email + "&comment=" + comment_edit;
                Log.v("java12", url_commentedit);
                url_commentedit = url_commentedit.replace(" ", "%20");
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url_commentedit, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("Tag", response.toString());
                        //progressBar.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("TAG", "ERROR AT STRING REQUEST #" + error.toString());
                    }
                });

                MySingleton.getInstance(getApplicationContext()).addToRequestque(stringRequest);
                comment_editText.setText("");

                //      Intent in=new Intent(Form.this,Form.class);
                //    startActivity(in);
            }
        });
        // get device height

        Display display = getWindowManager().getDefaultDisplay();
        //graphics
        final Point size = new Point();
        display.getSize(size);
        int deviceHeight = size.y;


        //  setList(listView);
        //set height
        popupWindow = new PopupWindow(view, size.x, size.y - 50, true);

        //set shape
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_window));

        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        //popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        view.measure(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        int height = view.getMeasuredHeight();
        int width = view.getMeasuredWidth();
        popupWindow.showAtLocation(v, Gravity.TOP, 0, 0);

        // popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE|WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        //popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        // popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        //popupWindow.setAnimationStyle(R.style.PopupAnimation);
    }

    public void setList(ListView list_View, final ProgressBar progressBarLoadList) {
        // set adapter and data in model class
        Log.v("ProgressBarLOadList", "" + progressBarLoadList.getVisibility());
        Log.v("setlist", "");
        final ArrayList<Comment_Model> commentlist = new ArrayList<Comment_Model>();
        sp_id = String.valueOf(4);

        url_commentshow = "https://hetal1395.000webhostapp.com/comment_show.php?sp_id=" + sp_id;
        Log.v("TAG", url_commentshow.toString());
        //get sp_id from activity

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url_commentshow, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBarLoadList.setVisibility(View.GONE);
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject comments = response.getJSONObject(i);

                        Log.v("java123", comments.toString());
                        final String name = comments.getString("name");
                        final String comment = comments.getString("comment");
//                        model.setName(name);
//                        model.setComment(comment);

                        Log.v("java123", name);
//
                        Comment_Model model = new Comment_Model(name, comment);
                        //  Log.v("TAG",comment);
                        commentlist.add(model);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("TAG", "ERROR AT list");
            }
        });
        MySingleton.getInstance(getApplicationContext()).addToRequestque(jsonArrayRequest);
        CustomeAdapter customeAdapter = new CustomeAdapter(getApplicationContext(), commentlist);
        Log.v("adapter", "");
        list_View.setAdapter(customeAdapter);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    public class CustomeAdapter extends ArrayAdapter<Comment_Model> {
        Context context;
        ArrayList<Comment_Model> arrayList;

        public CustomeAdapter(Context context, ArrayList<Comment_Model> list) {
            super(context, R.layout.comment_list, list);
            this.arrayList = list;
            this.context = context;
        }

        @Override
        public View getView(int pos, View converview, ViewGroup parent) {
            Log.v("getview", "");
            Comment_Model model = getItem(pos);
            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            converview = inflater.inflate(R.layout.comment_list, parent, false);
            TextView name = (TextView) converview.findViewById(R.id.name);
            TextView comment = (TextView) converview.findViewById(R.id.comment);

            name.setText(model.getName());
            comment.setText(model.getComment());
            return converview;
        }
    }

    public class PhoneCallListener extends PhoneStateListener {

        SharedPreferences sh;
        Boolean flag;
        String LOG_TAG = "LOGGING 123";
        private boolean isPhoneCalling = false;
        private int prev_state;

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            Log.v("final!@#", "123");
            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:
                    Log.d("cal ringing", "CALL_STATE_RINGING");
                    prev_state = state;
                    break;

                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Log.d("call offhook", "CALL_STATE_OFFHOOK");
                    prev_state = state;
                    break;

                case TelephonyManager.CALL_STATE_IDLE:

                    Log.d("idle", "CALL_STATE_IDLE==>");

//                    if ((prev_state == TelephonyManager.CALL_STATE_OFFHOOK)) {
//                        prev_state = state;
//                        //Answered Call which is ended
//                        Intent i = new Intent(getApplicationContext(), Recycle_Cat_Details.class);
//                      //  i.putExtra("sp_id1", sp_id);
//                        //startActivity(i);
//                            MyApplication.getInstance().getPrefManager().storeSpId(sp_id);
//                        //finish();
//                        i.putExtra("name","bhoomi");
//                        startActivityForResult(i,2);
//                        setResult(RESULT_OK,i);
//                        Log.e("java!@#",sp_id);
//                 //       setResult(2, i);
//                        finish();
//                    }
//                    if ((prev_state == TelephonyManager.CALL_STATE_RINGING)) {
//                        prev_state = state;
//                        flag = true;
//                        Intent i = new Intent(getApplicationContext(), Recycle_Cat_Details.class);
//                        //i.putExtra("sp_id", sp_id);
//                        // startActivity(i);
//                        // finish();
//                         startActivityForResult(i,2);
//                        Log.v("final!@#",sp_id);
//                        //setResult(2, i);
//                        finish();
//                        //Rejected or Missed call
//                    }
                    if ((prev_state == TelephonyManager.CALL_STATE_OFFHOOK)) {
                        prev_state = state;
                        //Answered Call which is ended
                        Intent i = new Intent(getApplicationContext(), Recycle_Cat_Details.class);
                        //startActivity(i);
                        //finish();
                        //startActivityForResult(i,2);
                        i.putExtra("sp_id", sp_id);
                        i.putExtra("name", name);
                        setResult(2, i);
                        // setResult(RESULT_OK, i);
                        finish();
                    }
                    if ((prev_state == TelephonyManager.CALL_STATE_RINGING)) {
                        prev_state = state;
                        flag = true;
                        Intent i = new Intent(getApplicationContext(), Recycle_Cat_Details.class);
                        // startActivity(i);
                        // finish();
                        // startActivityForResult(i,2);
                        // setResult(RESULT_OK, i);
                        setResult(2, i);

                        finish();
                        //Rejected or Missed call
                    }
                    break;
            }
        }


    }
}
