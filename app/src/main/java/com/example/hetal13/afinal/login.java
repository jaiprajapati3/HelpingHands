package com.example.hetal13.afinal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    EditText login_email;
    Spinner login_user;
    Button login;
    TextView login_register,tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.login);
        login_email = (EditText) findViewById(R.id.login_email);
        login_user = (Spinner) findViewById(R.id.login_user);
        login_register = (TextView) findViewById(R.id.login_register);
//        loginLable = (EditText) findViewById(R.id.loginlable);
//        loginLable.setClickable(false);
//        loginLable.setEnabled(false);
        tvLogin= (TextView) findViewById(R.id.tvLogin);
       Typeface custom_font = Typeface.createFromAsset(getBaseContext().getResources().getAssets(),"fonts/Allura-Regular.otf");
        tvLogin.setTypeface(custom_font);
        final TextInputLayout input_layout_email = (TextInputLayout) findViewById(R.id.input_layout_email);
        input_layout_email.setHint("Email");
        login_user.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm=(InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(login_email.getWindowToken(), 0);
                return false;
            }
        }) ;
        //    Pass the intent to the registration page if not a user
        login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("temp", "demo");
                Intent intent = new Intent(Login.this, Registration.class);
                startActivity(intent);
                finish();

            }
        });
        //    Pass the intent to the home page if already user
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String spinner_value = (String) login_user.getSelectedItem();
                Log.v("spinner", spinner_value);
                final String email = String.valueOf(login_email.getText());
                String url_login = UrlString.url_string + "/Login.php?email=" + email + "&type=" + spinner_value;
           url_login=url_login.replace(" ", "%20");
                if (spinner_value.equalsIgnoreCase("Service Provider")) {
                } else if (spinner_value.equalsIgnoreCase("User")) {
                } else {
                    Toast.makeText(getApplicationContext(), "Please Choose User Type", Toast.LENGTH_SHORT).show();
                }
                if (login_email.getText().toString().equals("")) {
                    login_email.setError("Please Enter Username");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(login_email.getText().toString()).matches()) {
                    login_email.setError("Please Enter Valid Email-id");
                } else {

                }
                Log.v("testurl", url_login);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_login, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean success = response.getBoolean("success");

                            Log.v("test", String.valueOf(success));
                            if (success == true) {
                                String fname = response.getString("fname");
                                String lname = response.getString("lname");
                                String areaId = response.getString("areaId");

                                MyApplication.getInstance().getPrefManager().storearea(areaId);
                                MyApplication.getInstance().getPrefManager().storefirstname(fname);
                                MyApplication.getInstance().getPrefManager().storelastname(lname);
                                MyApplication.getInstance().getPrefManager().storeemail(email);

//    MyApplication.getInstance().getPrefManager().storeuser_mode(url_type);
                                MyApplication.getInstance().getPrefManager().storeuser_mode(spinner_value);
                                Log.v("test1", spinner_value);
                                if (spinner_value.equals("User")) {
                                    Log.v("test11", spinner_value);
                                    Intent urlHome = new Intent(getBaseContext(), Home.class);
                                    startActivity(urlHome);
                                    finish();
                                }
                                if (spinner_value.equalsIgnoreCase("Service Provider")) {
                                    Log.v("test11", spinner_value);
                                    String skillId = response.getString("skill_id");
                                    MyApplication.getInstance().getPrefManager().storecheckbox(skillId);
                                    Intent urlHome = new Intent(getBaseContext(), Service_feedback.class);
                                    startActivity(urlHome);
                                    finish();
                                }
                            } else {
                                //Login failed
                                Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("Error", error.getMessage());
                    }
                });
                MySingleton.getInstance(getApplicationContext()).addToRequestque(jsonObjectRequest);
            }
        });
    }
}