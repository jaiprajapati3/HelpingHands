package com.example.hetal13.afinal;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextUtils;
import android.text.TextWatcher;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.regex.Pattern;
public class Login extends AppCompatActivity {
    EditText login_email,loginLable;
    Spinner login_user;
    Button login;
    TextView login_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login= (Button) findViewById(R.id.login);
        login_email= (EditText) findViewById(R.id.login_email);
        login_user= (Spinner) findViewById(R.id.login_user);
        login_register= (TextView) findViewById(R.id.login_register);
        loginLable= (EditText) findViewById(R.id.loginlable);
        loginLable.setClickable(false);
        loginLable.setEnabled(false);
//        Typeface custom_font = Typeface.createFromAsset(getAssets(),"assests/fonts/Billabong.ttf");
//        loginLable.setTypeface(custom_font);
       final TextInputLayout input_layout_email= (TextInputLayout) findViewById(R.id.input_layout_email);
        input_layout_email.setHint("Username");
        //    Pass the intent to the registration page if not a user
        login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("temp","demo");
                Intent intent=new Intent(Login.this,Registration.class);
                startActivity(intent);
                finish();

            }
        });
        //    Pass the intent to the home page if already user
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String spinner_value= (String) login_user.getSelectedItem();
                Log.v("spinner",spinner_value);
                final String email= String.valueOf(login_email.getText());
                String url_login=UrlString.url_string+"/Login.php?email="+email+"&type="+spinner_value;
                if(spinner_value.equalsIgnoreCase("Service Provider")){
                }
                else if(spinner_value.equalsIgnoreCase("User")){
                }
                else{
                        Toast.makeText(getApplicationContext(), "Please Choose User Type", Toast.LENGTH_SHORT).show();
                   }
                if (login_email.getText().toString().equals("")){
                    login_email.setError("Please Enter Username");
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(login_email.getText().toString()).matches()){
                    login_email.setError("Please Enter Valid Email-id");
                }
                else {

                }
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
                                MyApplication.getInstance().getPrefManager().storeemail(email);
                                MyApplication.getInstance().getPrefManager().storecheckbox("2");
//    MyApplication.getInstance().getPrefManager().storeuser_mode(url_type);
                                MyApplication.getInstance().getPrefManager().storeuser_mode(spinner_value);
                                if(url_type.equals("2")){
                                    Intent urlHome=new Intent(getBaseContext(),Home.class);
                                    startActivity(urlHome);
                                    finish();
                                }
                                if(url_type.equals("3")){
                                    Intent urlHome=new Intent(getBaseContext(),Service_feedback.class);
                                    startActivity(urlHome);
                                    finish();
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
                MySingleton.getInstance(getApplicationContext()).addToRequestque(jsonObjectRequest);
            }
        });
    }
}
