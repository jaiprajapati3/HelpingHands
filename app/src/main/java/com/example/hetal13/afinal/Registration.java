package com.example.hetal13.afinal;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import static android.R.id.list;


public class Registration extends AppCompatActivity implements View.OnClickListener {
    public ArrayList<SpinnerModel> CustomListViewValuesArr = new ArrayList<SpinnerModel>();
    CustomAdapter adapter, adapter1;
    Spinner SpinnerExample, SpinnerExample1, SpinnerExample2, SpinnerExample3;
    String cname, sname, city_name, User_OR_ServiceProvider, area_name, area_id;
    public String spDetails;
    String cid, sid, city;
    String f_name, l_name;
    Set<String> set = new HashSet<String>();
    public ArrayList<SpinnerModel> CustomListViewValuesArr1 = new ArrayList<SpinnerModel>();
    private final String url = "https://hetal1395.000webhostapp.com/getcountry.php";
    private String GetState_url, GetCity_url, GetArea_url;
    Registration activity = null;
    EditText phoneno, fname_edittext, lname_edittext;
    String getno;
    RadioButton pg, driver, tiffin, security, maid, contractor, mechanic;
    LinearLayout linearlayout1, linearlayout2, linearlayout3, linearlayout4, linearlayout5;
    LinearLayout linearlayout6, linearlayout7, linearlayout8, linearlayout9;
    LinearLayout pglayout, tiffinlayout, contractorlayout, maidlayout, driverlayout, securitylayout, mechaniclayout;
    String user_type, email_select;
    Button login_next, user_type_next, skill_type_next, name_next, area_next, verify_otp, name_next_user, area_next_user, verify_otp_user;
    Spinner spinner1, spinner2, spinner3, spinner4, spinner5, spinner6;
    int userId;
    JSONObject state, country;
    Spinner dropdown;
    private ArrayList<String> EmailListing;
    ProgressBar progressBar;

    EditText workexp_driver, cost_driver, workexp_meachanic, cost_mechanic, workexp_security, cost_security, workexp_maid, cost_maid;
    EditText workexp_contractor, cost_contractor;
    String workexp, cost, ac_cost, cost_full, cost_half, full_cost, half_cost, nonac_cost, cost_pg, str = "", no_of_person = "", work_exp = "", sp_cost = "", maid_cost = "", selectshift, maid_slot, slot_maid;
    Spinner persons, shift, slot;
    CheckBox ac, non_ac, full, half;
    EditText cost_acroom, cost_room, cost_fulltiffin, cost_halftiffin;
    Button pg_next, contractor_next, maid_next, driver_next, security_next, mechanic_next, tiffin_next;

    String user_array[],url_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (MyApplication.getInstance().getPrefManager().isfirsttime()) {

        } else {
            launch();
        }


        setContentView(R.layout.activity_registration);


        linearlayout1 = (LinearLayout) findViewById(R.id.linearlayout1);
        linearlayout2 = (LinearLayout) findViewById(R.id.linearlayout2);
        linearlayout3 = (LinearLayout) findViewById(R.id.linearlayout3);
        linearlayout4 = (LinearLayout) findViewById(R.id.linearlayout4);
        linearlayout5 = (LinearLayout) findViewById(R.id.linearlayout5);
        linearlayout6 = (LinearLayout) findViewById(R.id.linearlayout6);
        pglayout = (LinearLayout) findViewById(R.id.pglayout);
        tiffinlayout = (LinearLayout) findViewById(R.id.tiffinlayout);
        contractorlayout = (LinearLayout) findViewById(R.id.contractorlayout);
        maidlayout = (LinearLayout) findViewById(R.id.maidlayout);
        driverlayout = (LinearLayout) findViewById(R.id.driverlayout);
        securitylayout = (LinearLayout) findViewById(R.id.securitylayout);
        mechaniclayout = (LinearLayout) findViewById(R.id.mechaniclayout);

        pg = (RadioButton) findViewById(R.id.pg);
        driver = (RadioButton) findViewById(R.id.driver);
        tiffin = (RadioButton) findViewById(R.id.tiffin);
        security = (RadioButton) findViewById(R.id.security);
        maid = (RadioButton) findViewById(R.id.maid);
        mechanic = (RadioButton) findViewById(R.id.mechanic);
        contractor = (RadioButton) findViewById(R.id.contractor);

        login_next = (Button) findViewById(R.id.login_next);
        user_type_next = (Button) findViewById(R.id.user_type_next);
        skill_type_next = (Button) findViewById(R.id.skill_type_next);
        name_next = (Button) findViewById(R.id.name_next);
        area_next = (Button) findViewById(R.id.area_next);
        verify_otp = (Button) findViewById(R.id.verify_otp);
        f_name = ((EditText) findViewById(R.id.fname_id)).getText().toString();
        Log.v("fname1", f_name);


        workexp_driver = (EditText) findViewById(R.id.workexp_driver);
        cost_driver = (EditText) findViewById(R.id.cost_driver);
        driver_next = (Button) findViewById(R.id.driver_next);

        workexp_meachanic = (EditText) findViewById(R.id.workexp_mechanic);
        cost_mechanic = (EditText) findViewById(R.id.cost_mechanic);
        mechanic_next = (Button) findViewById(R.id.mechanic_next);

        workexp_security = (EditText) findViewById(R.id.workexp_security);
        shift = (Spinner) findViewById(R.id.shift);
        cost_security = (EditText) findViewById(R.id.cost_security);
        security_next = (Button) findViewById(R.id.security_next);

        workexp_maid = (EditText) findViewById(R.id.workexp_maid);
        cost_maid = (EditText) findViewById(R.id.cost_maid);
        slot = (Spinner) findViewById(R.id.slot);
        maid_next = (Button) findViewById(R.id.maid_next);
        mechanic_next = (Button) findViewById(R.id.mechanic_next);

        workexp_contractor = (EditText) findViewById(R.id.workexp_contractor);
        cost_contractor = (EditText) findViewById(R.id.cost_contractor);
        contractor_next = (Button) findViewById(R.id.contractor_next);

        persons = (Spinner) findViewById(R.id.no_of_persons);
        ac = (CheckBox) findViewById(R.id.ac);
        non_ac = (CheckBox) findViewById(R.id.nonac);
        cost_acroom = (EditText) findViewById(R.id.cost_acroom);
        cost_room = (EditText) findViewById(R.id.cost_room);
        pg_next = (Button) findViewById(R.id.pg_next);

        full = (CheckBox) findViewById(R.id.full);
        half = (CheckBox) findViewById(R.id.half);
        cost_fulltiffin = (EditText) findViewById(R.id.cost_fulltiffin);
        cost_halftiffin = (EditText) findViewById(R.id.cost_halftiffin);
        tiffin_next = (Button) findViewById(R.id.tiffin_next);

        pg_next.setOnClickListener(this);
        contractor_next.setOnClickListener(this);
        driver_next.setOnClickListener(this);
        security_next.setOnClickListener(this);
        maid_next.setOnClickListener(this);
        mechanic_next.setOnClickListener(this);
        tiffin_next.setOnClickListener(this);

        l_name = ((EditText) findViewById(R.id.lname_id)).getText().toString();
        phoneno = (EditText) findViewById(R.id.phone_no);
        getno = phoneno.getText().toString();
        //    verify= (Button) findViewById(R.id.verify);

        login_next.setOnClickListener(this);
        user_type_next.setOnClickListener(this);
        skill_type_next.setOnClickListener(this);
        name_next.setOnClickListener(this);
        area_next.setOnClickListener(this);
        verify_otp.setOnClickListener(this);
        dropdown = (Spinner) findViewById(R.id.email_select);
        getMailAddress();
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                email_select = dropdown.getSelectedItem().toString();
                Log.v("email", email_select);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2 = (Spinner) findViewById(R.id.user_select);
        spinner3 = (Spinner) findViewById(R.id.country);
        spinner4 = (Spinner) findViewById(R.id.state);
        spinner5 = (Spinner) findViewById(R.id.city);
        spinner6 = (Spinner) findViewById(R.id.area);

        user_array = getResources().getStringArray(R.array.categories);


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                user_type = spinner2.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //MyApplication.getInstance().getPrefManager().storeuser_mode(user_type);

        //bhoomi
        activity = this;
        SpinnerExample = (Spinner) findViewById(R.id.country);
        SpinnerExample1 = (Spinner) findViewById(R.id.state);
        SpinnerExample2 = (Spinner) findViewById(R.id.city);
        SpinnerExample3 = (Spinner) findViewById(R.id.area);

        setListData();
        SpinnerExample.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                Log.v("cID","country");
                cname = ((TextView) v.findViewById(R.id.tx_name)).getText().toString();
                cid = ((TextView) v.findViewById(R.id.tx_id)).getText().toString();
                final ProgressBar progressBar = (ProgressBar) findViewById(R.id.loadlist);
                Log.v("cID",cid);
                Log.v("selected country", cid);
                GetState_url = "https://hetal1395.000webhostapp.com/getstate.php?country_id=" + cid;
                progressBar.setVisibility(View.VISIBLE);
                SetStateList();

                //    progressBar.setVisibility(View.VISIBLE);

                //setListData1();
                Log.v("out of state", "done");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
        SpinnerExample1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                sname = ((TextView) v.findViewById(R.id.tx_name)).getText().toString();
                sid = ((TextView) v.findViewById(R.id.tx_id)).getText().toString();
                final ProgressBar progressBar = (ProgressBar) findViewById(R.id.loadlist);
                Log.v("cID   "+"state", sid);
                GetCity_url = "https://hetal1395.000webhostapp.com/getcity.php?state_id=" + sid;
                progressBar.setVisibility(View.VISIBLE);
                SetCityList();
                //setListData1();
                Log.v("out of state", "done");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
        SpinnerExample2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                city_name = ((TextView) v.findViewById(R.id.tx_name)).getText().toString();
                city = ((TextView) v.findViewById(R.id.tx_id)).getText().toString();
                final ProgressBar progressBar = (ProgressBar) findViewById(R.id.loadlist);
                Log.v("selected city", city_name);
                GetArea_url = "https://hetal1395.000webhostapp.com/getarea.php?city_id=" + city;
                progressBar.setVisibility(View.VISIBLE);
                SetAreaList();

                Log.v("out of state", "done");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        SpinnerExample3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                area_name = ((TextView) view.findViewById(R.id.tx_name)).getText().toString();
                area_id = ((TextView) view.findViewById(R.id.tx_id)).getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /*private void SetAreaList() {
        JsonArrayRequest req = null;
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.loadlist);
        req = new JsonArrayRequest(Request.Method.GET, GetArea_url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        ArrayList<SpinnerModel> CustomListViewValuesArr2 = new ArrayList<SpinnerModel>();
                        Log.v("#res_city", String.valueOf(response));
                        progressBar.setVisibility(View.INVISIBLE);
                        for (int i = 0; i < response.length(); i++) {
                            Log.e("E2#  ", "1");
                            try {
                                JSONObject state = (JSONObject) response.get(i);
                                Log.v("ans1", state.getString("area_name"));
                                final SpinnerModel model = new SpinnerModel();
                                model.setName(state.getString("area_name"));
                                model.setId(state.getInt("area_id"));
                                CustomListViewValuesArr2.add(model);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        adapter1 = new CustomAdapter(activity, R.layout.spinner_rows, CustomListViewValuesArr2);

                        SpinnerExample3.setAdapter(adapter1);
                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("# ERROR at area", "#here" + error.toString());
                //Do what you want to do on error
            }
        });
        MySingleton.getInstance(Registration.this).addToRequestque(req);
    }

    private void SetCityList() {
        JsonArrayRequest req = null;
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.loadlist);
        req = new JsonArrayRequest(Request.Method.GET, GetCity_url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<SpinnerModel> CustomListViewValuesArr1 = new ArrayList<SpinnerModel>();
                        Log.v("#res_city", String.valueOf(response));
                        progressBar.setVisibility(View.INVISIBLE);
                        for (int i = 0; i < response.length(); i++) {
                            Log.e("E2#  ", "1");
                            try {
                                JSONObject state = (JSONObject) response.get(i);
                                Log.v("ans1", state.getString("city_name"));
                                final SpinnerModel model = new SpinnerModel();
                                model.setName(state.getString("city_name"));
                                model.setId(state.getInt("city_id"));
                                CustomListViewValuesArr1.add(model);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter1 = new CustomAdapter(activity, R.layout.spinner_rows, CustomListViewValuesArr1);
                        SpinnerExample2.setAdapter(adapter1);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("# ERROR at city", "#here" + error.toString());
                //Do what you want to do on error
            }
        });
        MySingleton.getInstance(Registration.this).addToRequestque(req);
    }

    private void SetStateList() {
        JsonArrayRequest req = null;
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.loadlist);

        //progressBar.setVisibility(View.VISIBLE);
        req = new JsonArrayRequest(Request.Method.GET, GetState_url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        ArrayList<SpinnerModel> CustomListViewValuesArr1 = new ArrayList<SpinnerModel>();
                        Log.v("#res1", String.valueOf(response));
                        progressBar.setVisibility(View.INVISIBLE);

                        //              progressBar.setVisibility(View.GONE);

                        for (int i = 0; i < response.length(); i++) {

                            Log.e("E2#  ", "1");
                            try {
                                JSONObject state = (JSONObject) response.get(i);
                                Log.v("ans1", state.getString("state_name"));
                                final SpinnerModel model = new SpinnerModel();
                                model.setName(state.getString("state_name"));
                                model.setId(state.getInt("state_id"));
                                CustomListViewValuesArr1.add(model);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter1 = new CustomAdapter(activity, R.layout.spinner_rows, CustomListViewValuesArr1);
                        SpinnerExample1.setAdapter(adapter1);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("# ERROR at state", "#here" + error.toString());
                //Do what you want to do on error
            }
        });
        MySingleton.getInstance(Registration.this).addToRequestque(req);
    }

    private void setListData() {
        //for country
        // final ProgressBar progressBar= (ProgressBar) findViewById(R.id.loadlist);
        //progressBar.setVisibility(View.VISIBLE);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.loadlist);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.v("#response", String.valueOf(response));
                progressBar.setVisibility(View.GONE);
                progressBar.setVisibility(View.INVISIBLE);
                for (int i = 0; i < response.length(); i++) {
                    Log.e("E2#  ", "1");
                    try {
                        JSONObject country = (JSONObject) response.get(i);
                        Log.v("ans", country.getString("country_name"));
                        final SpinnerModel model = new SpinnerModel();
                        model.setName(country.getString("country_name"));
                        model.setId(country.getInt("country_id"));
                        CustomListViewValuesArr.add(model);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter = new CustomAdapter(activity, R.layout.spinner_rows, CustomListViewValuesArr);
                SpinnerExample.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("# ERROR at country", "here");
            }


        });
        MySingleton.getInstance(Registration.this).addToRequestque(jsonArrayRequest);
    }

    //end bhoomi*/

    private void SetAreaList() {
        //     Log.v("ResponseCountry","hello");
        Log.v("java","ARea");
        JsonArrayRequest req = null;
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.loadlist);
        req = new JsonArrayRequest(Request.Method.GET, GetArea_url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        ArrayList<SpinnerModel> CustomListViewValuesArr2 = new ArrayList<SpinnerModel>();
                        Log.v("#res_city", String.valueOf(response));
                        progressBar.setVisibility(View.INVISIBLE);
                        final SpinnerModel model1 = new SpinnerModel();
                        model1.setId(0);
                        model1.setName("Select your Area");
                        CustomListViewValuesArr2.add(model1);
                        for (int i = 0; i < response.length(); i++) {
                            Log.e("E2#  ", "1");
                            try {
                                JSONObject state = (JSONObject) response.get(i);
                                Log.v("ans1", state.getString("area_name"));
                                final SpinnerModel model = new SpinnerModel();
                                model.setName(state.getString("area_name"));
                                model.setId(state.getInt("area_id"));
                                CustomListViewValuesArr2.add(model);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            adapter1 = new CustomAdapter(activity, R.layout.spinner_rows, CustomListViewValuesArr2);

                            SpinnerExample3.setAdapter(adapter1);
                        }
                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("# ERROR at area", "#here" + error.toString());
                //Do what you want to do on error
            }
        });
        MySingleton.getInstance(Registration.this).addToRequestque(req);
    }

    private void SetCityList() {
        //   Log.v("ResponseCountry","hello");
        JsonArrayRequest req = null;
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.loadlist);
        req = new JsonArrayRequest(Request.Method.GET, GetCity_url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<SpinnerModel> CustomListViewValuesArr1 = new ArrayList<SpinnerModel>();
                        Log.v("#res_city", String.valueOf(response));
                        progressBar.setVisibility(View.INVISIBLE);
                        final SpinnerModel model1 = new SpinnerModel();
                        model1.setId(0);
                        model1.setName("Select your City");
                        CustomListViewValuesArr1.add(model1);
                        for (int i = 0; i < response.length(); i++) {
                            Log.e("E2#  ", "1");
                            try {
                                JSONObject state = (JSONObject) response.get(i);
                                Log.v("ans1", state.getString("city_name"));
                                final SpinnerModel model = new SpinnerModel();
                                model.setName(state.getString("city_name"));
                                model.setId(state.getInt("city_id"));
                                CustomListViewValuesArr1.add(model);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        adapter1 = new CustomAdapter(activity, R.layout.spinner_rows, CustomListViewValuesArr1);
                        SpinnerExample2.setAdapter(adapter1);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("# ERROR at city", "#here" + error.toString());
                //Do what you want to do on error
            }
        });
        MySingleton.getInstance(Registration.this).addToRequestque(req);
    }

    private void SetStateList() {
        // Log.v("ResponseCountry","hello");
        JsonArrayRequest req = null;
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.loadlist);

        //progressBar.setVisibility(View.VISIBLE);
        req = new JsonArrayRequest(Request.Method.GET, GetState_url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        ArrayList<SpinnerModel> CustomListViewValuesArr1 = new ArrayList<SpinnerModel>();
                        Log.v("#res1", String.valueOf(response));
                        progressBar.setVisibility(View.INVISIBLE);

                        //              progressBar.setVisibility(View.GONE);
                        final SpinnerModel model1 = new SpinnerModel();
                        model1.setId(0);
                        model1.setName("Select your State");
                        CustomListViewValuesArr1.add(model1);
                        for (int i = 0; i < response.length(); i++) {

                            Log.e("E2#  ", "1");
                            try {
                                JSONObject state = (JSONObject) response.get(i);
                                Log.v("ans1", state.getString("state_name"));
                                final SpinnerModel model = new SpinnerModel();
                                model.setName(state.getString("state_name"));
                                model.setId(state.getInt("state_id"));
                                CustomListViewValuesArr1.add(model);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        adapter1 = new CustomAdapter(activity, R.layout.spinner_rows, CustomListViewValuesArr1);
                        SpinnerExample1.setAdapter(adapter1);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("# ERROR at state", "#here" + error.toString());
                //Do what you want to do on error
            }
        });
        MySingleton.getInstance(Registration.this).addToRequestque(req);
    }

    private void setListData() {
        //for country
        // final ProgressBar progressBar= (ProgressBar) findViewById(R.id.loadlist);
        //progressBar.setVisibility(View.VISIBLE);
        //Log.v("ResponseCountry","hello");
Log.v("final1","Country");
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.loadlist);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.v("final12", String.valueOf(response));
                //   Log.v("#responsecountry", String.valueOf(response));
                progressBar.setVisibility(View.GONE);
                progressBar.setVisibility(View.INVISIBLE);
                final SpinnerModel model1 = new SpinnerModel();
                model1.setId(0);
                model1.setName("Select your Country");
                CustomListViewValuesArr.add(model1);
                for (int i = 0; i < response.length(); i++) {
                    Log.e("E2#  ", "1");
                    try {
                        JSONObject country = (JSONObject) response.get(i);
                        Log.v("final13", country.getString("country_name"));
                        final SpinnerModel model = new SpinnerModel();
                        model.setName(country.getString("country_name"));
                        model.setId(country.getInt("country_id"));
                        CustomListViewValuesArr.add(model);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                // Log.v("Select",String.valueOf(model));
                adapter = new CustomAdapter(activity, R.layout.spinner_rows, CustomListViewValuesArr);
                SpinnerExample.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("# ERROR at country", "here");
            }


        });
        MySingleton.getInstance(Registration.this).addToRequestque(jsonArrayRequest);
    }

//end bhoomi

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_next:

                linearlayout1.setVisibility(View.INVISIBLE);

                linearlayout2.setVisibility(View.VISIBLE);

                MyApplication.getInstance().getPrefManager().storeemail(email_select);
                break;
            case R.id.user_type_next:
                if (user_type.equals("Select Your Category")) {
                    Toast.makeText(this, "Please Choose User Type", Toast.LENGTH_SHORT).show();


                } else {
                    String email=MyApplication.getInstance().getPrefManager().getemail();
                   //url_login=UrlString.url_string+"/Login.php?email="+email;
                    url_login=UrlString.url_string+"/Login.php?email="+email+"&type="+user_type;
                    int type=1;//1 for registration
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url_login, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                boolean success=response.getBoolean("success");
                                if(success==true){
                                    String fname=response.getString("fname");
                                    String lname=response.getString("lname");
                                    String areaId=response.getString("areaId");
                                    String url_type=response.getString("user_type");
                                    MyApplication.getInstance().getPrefManager().storearea(areaId);
                                    MyApplication.getInstance().getPrefManager().storefirstname(fname);
                                    MyApplication.getInstance().getPrefManager().storelastname(lname);
                                    MyApplication.getInstance().getPrefManager().storeuser_mode(url_type);
                                    if(url_type.equals("2")){
                                        Intent urlHome=new Intent(getBaseContext(),Home.class);
                                        startActivity(urlHome);
                                    }
                                    if(url_type.equals("3")){
                                        Intent urlHome=new Intent(getBaseContext(),Service_feedback.class);
                                        startActivity(urlHome);
                                    }

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.v("Error",error.getMessage());
                        }
                    });
                    MySingleton.getInstance(this).addToRequestque(jsonObjectRequest);
                   if (user_type.equalsIgnoreCase("Service Provider")) {
                        linearlayout1.setVisibility(View.INVISIBLE);
                        linearlayout2.setVisibility(View.INVISIBLE);
                        linearlayout3.setVisibility(View.VISIBLE);

                    } else if (user_type.equalsIgnoreCase("User")) {
                        linearlayout1.setVisibility(View.INVISIBLE);
                        linearlayout2.setVisibility(View.INVISIBLE);
                        linearlayout3.setVisibility(View.INVISIBLE);
                        linearlayout4.setVisibility(View.VISIBLE);
                    }
                }
                MyApplication.getInstance().getPrefManager().storeuser_mode(user_type);
                break;
            case R.id.skill_type_next:

                int skill1 = 0, skill2 = 0, skill3 = 0, skill4 = 0, skill5 = 0, skill6 = 0, skill7 = 0;
                String list = "";
                if (pg.isChecked()) {
                    list = (new String("1"));
                    Log.v("LOg", "1");
                    skill1 = 1;

                } else if (tiffin.isChecked()) {
                    list = (new String("2"));
                    Log.v("LOg", "1");
                    skill2 = 1;
                } else if (contractor.isChecked()) {
                    list = (new String("4"));
                    Log.v("LOg", "1");
                    skill4 = 1;

                } else if (maid.isChecked()) {
                    list = ("3");
                    Log.v("LOg", "1");
                    skill3 = 1;
                } else if (driver.isChecked()) {
                    list = ("7");
                    Log.v("LOg", "1");
                    skill7 = 1;
                } else if (security.isChecked()) {
                    list = ("6");
                    Log.v("LOg", "1");
                    skill6 = 1;
                } else if (mechanic.isChecked()) {
                    list = ("5");
                    Log.v("LOg", "1");
                    skill5 = 1;
                }

                MyApplication.getInstance().getPrefManager().storecheckbox(list);
                final String skill_list = MyApplication.getInstance().getPrefManager().getcheckbox();
                Log.v("SP type:", " " + skill_list);
                //  onCheckedChanged(sp_group, Integer.parseInt(skill_list));


                //  final ArrayList<String> skill_list_form = MyApplication.getInstance().getPrefManager().getcheckbox();
                //    int skill1 = 0, skill2 = 0, skill3 = 0, skill4 = 0, skill5 = 0, skill6 = 0, skill7 = 0;
                //   for (String str : skill_list_form) {
                if (skill1 == 1) {
                    linearlayout1.setVisibility(View.INVISIBLE);
                    linearlayout2.setVisibility(View.INVISIBLE);
                    linearlayout3.setVisibility(View.INVISIBLE);
                    pglayout.setVisibility(View.VISIBLE);
                    ac.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (ac.isChecked()) {

                                cost_acroom.setVisibility(View.VISIBLE);
                                ac_cost = ac.getText().toString();

                            } else {
                                cost_acroom.setVisibility(View.INVISIBLE);
                            }

                        }
                    });
                    non_ac.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (non_ac.isChecked()) {
                                cost_room.setVisibility(View.VISIBLE);
                                nonac_cost = non_ac.getText().toString();
                            } else {
                                cost_room.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                } else if (skill2 == 1) {

                    linearlayout1.setVisibility(View.INVISIBLE);
                    linearlayout2.setVisibility(View.INVISIBLE);
                    linearlayout3.setVisibility(View.INVISIBLE);
                    pglayout.setVisibility(View.INVISIBLE);
                    Log.v("Tiffin Layout", "");
                    tiffinlayout.setVisibility(View.VISIBLE);
                    full.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (full.isChecked()) {

                                cost_fulltiffin.setVisibility(View.VISIBLE);
                                full_cost = cost_fulltiffin.getText().toString();

                            } else {
                                cost_fulltiffin.setVisibility(View.INVISIBLE);
                            }

                        }
                    });
                    half.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (half.isChecked()) {
                                cost_halftiffin.setVisibility(View.VISIBLE);
                                half_cost = cost_halftiffin.getText().toString();
                            } else {
                                cost_halftiffin.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                } else if (skill4 == 1) {

                    linearlayout1.setVisibility(View.INVISIBLE);
                    linearlayout2.setVisibility(View.INVISIBLE);
                    linearlayout3.setVisibility(View.INVISIBLE);
                    pglayout.setVisibility(View.INVISIBLE);
                    //    tiffinlayout.setVisibility(View.INVISIBLE);
                    contractorlayout.setVisibility(View.VISIBLE);
                } else if (skill3 == 1) {

                    linearlayout1.setVisibility(View.INVISIBLE);
                    linearlayout2.setVisibility(View.INVISIBLE);
                    linearlayout3.setVisibility(View.INVISIBLE);
                    pglayout.setVisibility(View.INVISIBLE);
                    //      tiffinlayout.setVisibility(View.INVISIBLE);
                    contractorlayout.setVisibility(View.INVISIBLE);
                    maidlayout.setVisibility(View.VISIBLE);
                } else if (skill5 == 1) {

                    linearlayout1.setVisibility(View.INVISIBLE);
                    linearlayout2.setVisibility(View.INVISIBLE);
                    linearlayout3.setVisibility(View.INVISIBLE);
                    pglayout.setVisibility(View.INVISIBLE);
                    //    tiffinlayout.setVisibility(View.INVISIBLE);
                    contractorlayout.setVisibility(View.INVISIBLE);
                    maidlayout.setVisibility(View.INVISIBLE);
                    driverlayout.setVisibility(View.INVISIBLE);
                    mechaniclayout.setVisibility(View.VISIBLE);
                } else if (skill6 == 1) {
                    linearlayout1.setVisibility(View.INVISIBLE);
                    linearlayout2.setVisibility(View.INVISIBLE);
                    linearlayout3.setVisibility(View.INVISIBLE);
                    pglayout.setVisibility(View.INVISIBLE);
                    //    tiffinlayout.setVisibility(View.INVISIBLE);
                    contractorlayout.setVisibility(View.INVISIBLE);
                    maidlayout.setVisibility(View.INVISIBLE);
                    driverlayout.setVisibility(View.INVISIBLE);
                    securitylayout.setVisibility(View.VISIBLE);
                } else {

                    linearlayout1.setVisibility(View.INVISIBLE);
                    linearlayout2.setVisibility(View.INVISIBLE);
                    linearlayout3.setVisibility(View.INVISIBLE);
                    pglayout.setVisibility(View.INVISIBLE);
                    //   tiffinlayout.setVisibility(View.INVISIBLE);
                    contractorlayout.setVisibility(View.INVISIBLE);
                    maidlayout.setVisibility(View.INVISIBLE);
                    driverlayout.setVisibility(View.VISIBLE);
                    securitylayout.setVisibility(View.INVISIBLE);
                    mechaniclayout.setVisibility(View.INVISIBLE);
                }
                // }

                //Log.v("Data:::",list.get(1));


                //Log.v("list::","list"+list);


                break;
            case R.id.pg_next:
                // Log.v("final_pg", "sd");
                //                pg_next.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
                no_of_person = "persons";
                workexp = persons.getSelectedItem().toString();
                cost = cost_acroom.getText().toString();
                cost_pg = cost_room.getText().toString();
                Log.v("final_pg12", "dsf" + cost_pg + "fg");
                Log.v("workexp", workexp);
                Log.v("workexp", cost);
                if (cost.equals("")) {
                    cost = String.valueOf(0);
                    Log.v("final_pg", "dsf");
                }
                if (cost_pg.equals("")) {
                    cost_pg = String.valueOf(0);
                    Log.v("final_pg", "dsf23");
                }
                str = no_of_person + "@" + workexp + "@" + "ACRoom" + "@" + cost + "@" + "NonACRoom" + "@" + cost_pg;
                Log.v("Tag", str);
                MyApplication.getInstance().getPrefManager().storePGdetails(str);

//                        String[] s = str.split("@");
//                        for(int i=0;i<s.length;i++){
//                            Log.v("value is :",s[i]);
//
//                        }
//                    }
//                });

                linearlayout1.setVisibility(View.INVISIBLE);
                linearlayout2.setVisibility(View.INVISIBLE);
                linearlayout3.setVisibility(View.INVISIBLE);
                pglayout.setVisibility(View.INVISIBLE);
                contractorlayout.setVisibility(View.INVISIBLE);
                linearlayout4.setVisibility(View.VISIBLE);
                break;
            case R.id.tiffin_next:

                cost_full = cost_fulltiffin.getText().toString();
                cost_half = cost_halftiffin.getText().toString();
                Log.v("fulltiffincost ", cost_full);
                Log.v("halftiffincost", cost_half);
                str = "Full" + "@" + cost_full + "@" + "Half" + "@" + cost_half;
                Log.v("final", str);
                MyApplication.getInstance().getPrefManager().storeTiffindetails(str);
                linearlayout1.setVisibility(View.INVISIBLE);
                linearlayout2.setVisibility(View.INVISIBLE);
                linearlayout3.setVisibility(View.INVISIBLE);
                pglayout.setVisibility(View.INVISIBLE);
                tiffinlayout.setVisibility(View.INVISIBLE);
                contractorlayout.setVisibility(View.INVISIBLE);
                linearlayout4.setVisibility(View.VISIBLE);
                break;

            case R.id.contractor_next:

                Log.v("final_", "dsf");
//                contractor_next.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
                work_exp = "workexp";
                sp_cost = "cost";
                workexp = workexp_contractor.getText().toString();
                cost = cost_contractor.getText().toString();
                Log.v("workexp", workexp);
                Log.v("workexp", cost);
                str = work_exp + "@" + workexp + "@" + sp_cost + "@" + cost;
                Log.v("final_cont", str);

//                        String[] s = str.split("@");
//                        for(int i=0;i<s.length;i++){
//                            Log.v("value is :",s[i]);
//                        }


                MyApplication.getInstance().getPrefManager().storeContractordetails(str);
//                    }
//                });
                linearlayout1.setVisibility(View.INVISIBLE);
                linearlayout2.setVisibility(View.INVISIBLE);
                linearlayout3.setVisibility(View.INVISIBLE);
                pglayout.setVisibility(View.INVISIBLE);
                contractorlayout.setVisibility(View.INVISIBLE);
                maidlayout.setVisibility(View.INVISIBLE);
                linearlayout4.setVisibility(View.VISIBLE);
                break;
            case R.id.maid_next:
//                maid_next.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
                work_exp = "workexp";
                sp_cost = "cost";
                maid_slot = "slot";
                workexp = workexp_maid.getText().toString();
                cost = cost_maid.getText().toString();
                slot_maid = slot.getSelectedItem().toString();
                Log.v("workexp", workexp);
                Log.v("workexp", cost);
                str = work_exp + "@" + workexp + "@" + sp_cost + "@" + cost + "@" + maid_slot + "@" + slot_maid;
                Log.v("final1Maid", str);

//                        String[] s = str.split("@");
//                        for(int i=0;i<s.length;i++){
//                            Log.v("value is :",s[i]);
//                        }
                MyApplication.getInstance().getPrefManager().storeMaiddetails(str);
//                    }
//                });

                linearlayout1.setVisibility(View.INVISIBLE);
                linearlayout2.setVisibility(View.INVISIBLE);
                linearlayout3.setVisibility(View.INVISIBLE);
                pglayout.setVisibility(View.INVISIBLE);
                contractorlayout.setVisibility(View.INVISIBLE);
                maidlayout.setVisibility(View.INVISIBLE);
                driverlayout.setVisibility(View.INVISIBLE);
                linearlayout4.setVisibility(View.VISIBLE);
                break;
            case R.id.driver_next:
                Log.v("final1", "driver");
//                driver_next.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
                work_exp = "workexp";
                sp_cost = "cost";
                workexp = workexp_driver.getText().toString();
                cost = cost_driver.getText().toString();
                Log.v("workexp", workexp);
                Log.v("workexp", cost);
                str = work_exp + "@" + workexp + "@" + sp_cost + "@" + cost;
                Log.v("finalDriver", str);

//                        String[] s = str.split("@");
//                        for(int i=0;i<s.length;i++){
//                            Log.v("value is :",s[i]);
//                        }

                MyApplication.getInstance().getPrefManager().storeDriverdetails(str);
//                    }
//                });
                linearlayout1.setVisibility(View.INVISIBLE);
                linearlayout2.setVisibility(View.INVISIBLE);
                linearlayout3.setVisibility(View.INVISIBLE);
                pglayout.setVisibility(View.INVISIBLE);
                contractorlayout.setVisibility(View.INVISIBLE);
                maidlayout.setVisibility(View.INVISIBLE);
                driverlayout.setVisibility(View.INVISIBLE);
                securitylayout.setVisibility(View.INVISIBLE);
                linearlayout4.setVisibility(View.VISIBLE);
                break;
            case R.id.security_next:
//                security_next.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
                work_exp = "workexp";
                sp_cost = "cost";
                workexp = workexp_security.getText().toString();
                selectshift = shift.getSelectedItem().toString();
                cost = cost_security.getText().toString();
                //  selectshift = shift.getSelectedItem().toString();
                Log.v("workexp", workexp);
                Log.v("workexp", selectshift);
                str = work_exp + "@" + workexp + "@" + sp_cost + "@" + cost + "@selectshift@" + selectshift;
                Log.v("final1Secu", str);

//                        String[] s = str.split("@");
//                        for(int i=0;i<s.length;i++){
//                            Log.v("value is :",s[i]);
//                        }

                MyApplication.getInstance().getPrefManager().storeSecurityGuarddetails(str);
//                    }
//                });

                linearlayout1.setVisibility(View.INVISIBLE);
                linearlayout2.setVisibility(View.INVISIBLE);
                linearlayout3.setVisibility(View.INVISIBLE);
                pglayout.setVisibility(View.INVISIBLE);
                contractorlayout.setVisibility(View.INVISIBLE);
                maidlayout.setVisibility(View.INVISIBLE);
                driverlayout.setVisibility(View.INVISIBLE);
                securitylayout.setVisibility(View.INVISIBLE);
                mechaniclayout.setVisibility(View.INVISIBLE);
                linearlayout4.setVisibility(View.VISIBLE);
                break;
            case R.id.mechanic_next:
                Log.v("final1", "Mech");
//                mechanic_next.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
                work_exp = "workexp";
                sp_cost = "cost";
                workexp = workexp_meachanic.getText().toString();
                cost = cost_mechanic.getText().toString();
                Log.v("workexp", workexp);
                Log.v("workexp", cost);
                str = work_exp + "@" + workexp + "@" + sp_cost + "@" + cost;
                Log.v("final1MEch", "Mech" + str);

//                        String[] s = str.split("@");
//                        for(int i=0;i<s.length;i++){
//                            Log.v("value is :",s[i]);
//
//                        }
                MyApplication.getInstance().getPrefManager().storeMechanicdetails(str);
//                    }
//                });

                linearlayout1.setVisibility(View.INVISIBLE);
                linearlayout2.setVisibility(View.INVISIBLE);
                linearlayout3.setVisibility(View.INVISIBLE);
                pglayout.setVisibility(View.INVISIBLE);
                contractorlayout.setVisibility(View.INVISIBLE);
                maidlayout.setVisibility(View.INVISIBLE);
                driverlayout.setVisibility(View.INVISIBLE);
                securitylayout.setVisibility(View.INVISIBLE);
                mechaniclayout.setVisibility(View.INVISIBLE);
                linearlayout4.setVisibility(View.VISIBLE);

                break;

            case R.id.name_next:


                linearlayout1.setVisibility(View.INVISIBLE);
                linearlayout2.setVisibility(View.INVISIBLE);
                linearlayout3.setVisibility(View.INVISIBLE);
                pglayout.setVisibility(View.INVISIBLE);
                tiffinlayout.setVisibility(View.INVISIBLE);
                contractorlayout.setVisibility(View.INVISIBLE);
                maidlayout.setVisibility(View.INVISIBLE);
                driverlayout.setVisibility(View.INVISIBLE);
                securitylayout.setVisibility(View.INVISIBLE);
                mechaniclayout.setVisibility(View.INVISIBLE);

                fname_edittext = (EditText) findViewById(R.id.fname_id);
                lname_edittext = (EditText) findViewById(R.id.lname_id);
                l_name = lname_edittext.getText().toString();
                f_name = fname_edittext.getText().toString();
                if (f_name.equals("")) {
                    Log.v("empty::", "empty::" + f_name);
                    fname_edittext.requestFocus();
                    fname_edittext.setError("Please Enter First Name");
                    Log.v("set error::", "set error::" + fname_edittext);

                } else if (l_name.equals("")) {
                    lname_edittext.requestFocus();
                    lname_edittext.setError("Please Enter Last Name");

                } else {
                    linearlayout4.setVisibility(View.INVISIBLE);
                    linearlayout5.setVisibility(View.VISIBLE);

                }
                progressBar = (ProgressBar) findViewById(R.id.loadlist);
                progressBar.setVisibility(View.VISIBLE);


                if (cid != null && sid != null && city != null) {

                    progressBar.setVisibility(View.INVISIBLE);

                } else {
                    progressBar.setVisibility(View.VISIBLE);
                }
                MyApplication.getInstance().getPrefManager().storefirstname(f_name);
                Log.v("fname", f_name);

                MyApplication.getInstance().getPrefManager().storelastname(l_name);
                Log.v("lname", l_name);

                break;
            case R.id.area_next:
                linearlayout1.setVisibility(View.INVISIBLE);
                linearlayout2.setVisibility(View.INVISIBLE);
                linearlayout3.setVisibility(View.INVISIBLE);
                linearlayout4.setVisibility(View.INVISIBLE);
                linearlayout5.setVisibility(View.INVISIBLE);
                pglayout.setVisibility(View.INVISIBLE);
                //    tiffinlayout.setVisibility(View.INVISIBLE);
                contractorlayout.setVisibility(View.INVISIBLE);
                maidlayout.setVisibility(View.INVISIBLE);
                driverlayout.setVisibility(View.INVISIBLE);
                securitylayout.setVisibility(View.INVISIBLE);
                mechaniclayout.setVisibility(View.INVISIBLE);
                linearlayout6.setVisibility(View.VISIBLE);
                MyApplication.getInstance().getPrefManager().storecountry(cid);
                //  Log.v("getcid",cid);
                MyApplication.getInstance().getPrefManager().storestate(sid);
                MyApplication.getInstance().getPrefManager().storearea(area_id);
                // Log.v("getesid",sid);
                MyApplication.getInstance().getPrefManager().storecity(city);
                //  Log.v("getcity",city);
                break;
            case R.id.verify_otp:
                phoneno = (EditText) findViewById(R.id.phone_no);
                getno = phoneno.getText().toString();

                if (getno.equals("")) {
                    linearlayout6.setVisibility(View.VISIBLE);
                    Log.v("phoneno::", "empty::" + getno);
                    phoneno.requestFocus();
                    phoneno.setError("Please Enter PhoneNo");
                    Log.v("set error::", "set error::" + fname_edittext);

                } else if (getno.length() < 10) {
                    phoneno.requestFocus();
                    phoneno.setError("Please Enter Valid PhoneNo");
                } else {

                    linearlayout1.setVisibility(View.INVISIBLE);
                    linearlayout2.setVisibility(View.INVISIBLE);
                    linearlayout3.setVisibility(View.INVISIBLE);
                    linearlayout4.setVisibility(View.INVISIBLE);
                    linearlayout5.setVisibility(View.INVISIBLE);
                    linearlayout6.setVisibility(View.INVISIBLE);
                    pglayout.setVisibility(View.INVISIBLE);
                    //      tiffinlayout.setVisibility(View.INVISIBLE);
                    contractorlayout.setVisibility(View.INVISIBLE);
                    maidlayout.setVisibility(View.INVISIBLE);
                    driverlayout.setVisibility(View.INVISIBLE);
                    securitylayout.setVisibility(View.INVISIBLE);
                    mechaniclayout.setVisibility(View.INVISIBLE);


                    MyApplication.getInstance().getPrefManager().storephoneno(getno);
                    // User_OR_ServiceProvider = MyApplication.getInstance().getPrefManager().getuser_mode();

                    String user_type, fname, lname, phoneno, country_id, state_id, city_id, area_id, email1;
                    String url_reguser, url_regsp, url_spData;
                    user_type = MyApplication.getInstance().getPrefManager().getuser_mode();
                    email1 = MyApplication.getInstance().getPrefManager().getemail();
                    fname = MyApplication.getInstance().getPrefManager().getfirstname();
                    lname = MyApplication.getInstance().getPrefManager().getlastname();
                    phoneno = MyApplication.getInstance().getPrefManager().getphoneno();
                    country_id = MyApplication.getInstance().getPrefManager().getcountry();
                    state_id = MyApplication.getInstance().getPrefManager().getstate();
                    city_id = MyApplication.getInstance().getPrefManager().getcity();
                    area_id = MyApplication.getInstance().getPrefManager().getarea();
                    if (user_type.equals("User")) {
                        url_reguser = "https://hetal1395.000webhostapp.com/registration_user.php?fname=" + fname + "&lname=" + lname +
                                "&phoneno=" + phoneno + "&email_id=" + email1 + "&country_id=" + country_id + "&state_id=" + state_id + "&city_id=" + city_id
                                + "&area_id=" + area_id;
                        Log.v("Hetal", url_reguser);
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_reguser, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.v("Tag", response);

                             /*   Intent in = new Intent(Registration.this, Home.class);
                                startActivity(in);*/
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Log.v("ERROR", error.toString());

                            }
                        });
                        MySingleton.getInstance(Registration.this).addToRequestque(stringRequest);
                    } else {
                        final String str1 = MyApplication.getInstance().getPrefManager().getcheckbox();
                        skill1 = 0;
                        skill2 = 0;
                        skill3 = 0;
                        skill4 = 0;
                        skill5 = 0;
                        skill6 = 0;
                        skill7 = 0;
                        //    for (String str1 : skill_list) {
                        if (str1.equals("1")) {
                            skill1 = 1;
                        } else if (str1.equals("2"))
                            skill2 = 1;
                        else if (str1.equals("3")) {
                            skill3 = 1;
                            Log.v("sp_data", "3");
                        } else if (str1.equals("4")) {
                            skill4 = 1;
                            Log.v("sp_data", "4");
                        } else if (str1.equals("5"))
                            skill5 = 1;
                        else if (str1.equals("6"))
                            skill6 = 1;
                        else
                            skill7 = 1;
                        //   }
                        url_regsp = "https://hetal1395.000webhostapp.com/registration_sp.php?fname=" + fname + "&lname=" + lname +
                                "&phoneno=" + phoneno + "&email_id=" + email1 + "&country_id=" + country_id + "&state_id=" + state_id + "&city_id=" + city_id
                                + "&area_id=" + area_id + "&skill1=" + skill1 + "&skill2=" + skill2 + "&skill3=" + skill3 + "&skill4=" + skill4 + "&skill5=" + skill5 + "&skill6=" + skill6
                                + "&skill7=" + skill7;
                        Log.v("Tag@##", url_regsp);
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_regsp, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.v("Tag#", response);

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Log.v("ERROR", error.toString());

                            }
                        });
                        MySingleton.getInstance(Registration.this).addToRequestque(stringRequest);
                        Log.v("Tag#12", "Out of request");
                        if (skill1 == 1) {


                            url_spData = "https://hetal1395.000webhostapp.com/sp_data.php?sp_id=1" + "&email=" + email1;
                            spDetails = MyApplication.getInstance().getPrefManager().getPGdetails();
                            Log.v("!@#", spDetails + "DS");
                            final String[] spDetail = spDetails.split("@");
                            for (int i = 0; i < (spDetail.length) - 1; i++) {
                                url_spData += "&" + spDetail[i] + "=" + spDetail[++i];

                            }
                            url_spData = url_spData.replace(" ", "%20");
                            Log.v("TagSP", url_spData);
                            StringRequest request = new StringRequest(Request.Method.GET, url_spData, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.v("TagSP", response);
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });
                            MySingleton.getInstance(Registration.this).addToRequestque(request);
                        } else if (skill2 == 1) {
                            url_spData = "https://hetal1395.000webhostapp.com/sp_data.php?sp_id=2" + "&email=" + email1;
                            spDetails = MyApplication.getInstance().getPrefManager().getTiffindetails();
                            Log.v("Java123", spDetails + "Tiffin");
                            final String[] spDetail = spDetails.split("@");
                            for (int i = 0; i < (spDetail.length)-1; i++) {
                                url_spData += "&" + spDetail[i] + "=" + spDetail[++i];
                            }
                            url_spData = url_spData.replace(" ", "%20");
                            StringRequest request = new StringRequest(Request.Method.GET, url_spData, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.v("TAG", response);
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });
                            MySingleton.getInstance(Registration.this).addToRequestque(request);

                        } else if (skill3 == 1) {
                            url_spData = "https://hetal1395.000webhostapp.com/sp_data.php?sp_id=3" + "&email=" + email1;
                            spDetails = MyApplication.getInstance().getPrefManager().getMaiddetails();
                            final String[] spDetail = spDetails.split("@");
                            for (int i = 0; i < (spDetail.length) - 1; i++) {
                                url_spData += "&" + spDetail[i] + "=" + spDetail[++i];
                            }
                            Log.v("TagSP", url_spData);
                            StringRequest request = new StringRequest(Request.Method.GET, url_spData, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.v("TAG", response);
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });

                            MySingleton.getInstance(Registration.this).addToRequestque(request);
                        } else if (skill4 == 1) {
                            url_spData = "https://hetal1395.000webhostapp.com/sp_data.php?sp_id=4" + "&email=" + email1;
                            spDetails = MyApplication.getInstance().getPrefManager().getContractordetails();
                            final String[] spDetail = spDetails.split("@");
                            for (int i = 0; i < (spDetail.length) - 1; i++) {
                                url_spData += "&" + spDetail[i] + "=" + spDetail[++i];
                            }
                            Log.v("TagSP", url_spData);
                            StringRequest request = new StringRequest(Request.Method.GET, url_spData, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.v("TAG", response);
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });

                            MySingleton.getInstance(Registration.this).addToRequestque(request);

                        } else if (skill5 == 1) {

                            url_spData = "https://hetal1395.000webhostapp.com/sp_data.php?sp_id=5" + "&email=" + email1;
                            spDetails = MyApplication.getInstance().getPrefManager().getMechanicdetails();
                            Log.v("finalMEch", "Mech " + spDetails);
                            final String[] spDetail = spDetails.split("@");
                            for (int i = 0; i < (spDetail.length) - 1; i++) {
                                url_spData += "&" + spDetail[i] + "=" + spDetail[++i];
                            }
                            Log.v("TagSP", url_spData);
                            StringRequest request = new StringRequest(Request.Method.GET, url_spData, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.v("TAG", response);
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });

                            MySingleton.getInstance(Registration.this).addToRequestque(request);

                        } else if (skill6 == 1) {
                            url_spData = "https://hetal1395.000webhostapp.com/sp_data.php?sp_id=6" + "&email=" + email1;
                            spDetails = MyApplication.getInstance().getPrefManager().getSecurityGuarddetails();
                            Log.v("finalMEch", "Mech " + spDetails);
                            final String[] spDetail = spDetails.split("@");
                            for (int i = 0; i < (spDetail.length) - 1; i++) {
                                url_spData += "&" + spDetail[i] + "=" + spDetail[++i];
                            }
                            url_spData = url_spData.replace(" ", "%20");
                            Log.v("TagSP", url_spData);
                            StringRequest request = new StringRequest(Request.Method.GET, url_spData, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.v("TAG", response);
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });

                            MySingleton.getInstance(Registration.this).addToRequestque(request);

                        } else {
                            url_spData = "https://hetal1395.000webhostapp.com/sp_data.php?sp_id=7" + "&email=" + email1;
                            spDetails = MyApplication.getInstance().getPrefManager().getDriverdetails();
                            Log.v("finalDRiver", "Mech " + spDetails);
                            final String[] spDetail = spDetails.split("@");
                            for (int i = 0; i < (spDetail.length) - 1; i++) {
                                Log.v("TagSP", url_spData);
                                url_spData += "&" + spDetail[i] + "=" + spDetail[++i];
                            }
                            Log.v("TagSP", url_spData);
                            StringRequest request = new StringRequest(Request.Method.GET, url_spData, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.v("TAG", response);
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });

                            MySingleton.getInstance(Registration.this).addToRequestque(request);


                        }


                    }

                    User_OR_ServiceProvider = MyApplication.getInstance().getPrefManager().getuser_mode();
                    if (User_OR_ServiceProvider.equalsIgnoreCase("Service Provider")) {
                        Intent intent = new Intent(Registration.this, Service_feedback.class);
                        startActivity(intent);
                    } else {

                        Intent in = new Intent(Registration.this, Home.class);
                        startActivity(in);
                    }


                    // Intent in = new Intent(Registration.this, Home.class);
                    // startActivity(in);
                    //User completed Registration,So no need to show Registration PAge
                    MyApplication.getInstance().getPrefManager().setisFirstTime(false);


                    break;
                }
        }
    }

    public void getMailAddress() {

        String possibleEmail = null;
        EmailListing = new ArrayList<String>();
        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+

        checkLocationPermission();

        Account[] accounts = AccountManager.get(getBaseContext()).getAccountsByType("com.google");
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                possibleEmail += account.name;
                EmailListing.add(account.name);
            }
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, EmailListing);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        dropdown.setAdapter(dataAdapter);

    }

    private void launch() {
        User_OR_ServiceProvider = MyApplication.getInstance().getPrefManager().getuser_mode();
        if (User_OR_ServiceProvider.equalsIgnoreCase("Service Provider")) {
            Intent intent = new Intent(Registration.this, Service_feedback.class);
            startActivity(intent);
        } else {
            startActivity(new Intent(Registration.this, Home.class));
        }
        finish();
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.GET_ACCOUNTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.GET_ACCOUNTS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(Registration.this,
                        new String[]{Manifest.permission.GET_ACCOUNTS},
                        1);


            } else {
                ActivityCompat.requestPermissions(Registration.this,
                        new String[]{Manifest.permission.GET_ACCOUNTS},
                        1);

            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.GET_ACCOUNTS)
                            == PackageManager.PERMISSION_GRANTED) {
                        getMailAddress();

                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }


}





