package com.example.hetal13.afinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Navigation extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    String User_OR_Provider,fname,lname,get_phoneno;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ImageButton btn1,btn2,btn3,btn4,btn5,btn6,btn7;
    public boolean exit=false;
    SharedPreferences pref;
    TextView username,country,state,city;
    TextView email;
    String country_data,user_name;
    String state_data;
    String city_data,email_data,Area_data,user_type;
    ArrayList<String> checkboxlist;
    View coordinator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        coordinator= findViewById(R.id.coordinator);
        /*country_data= MyApplication.getInstance().getPrefManager().getcountry();
        state_data= MyApplication.getInstance().getPrefManager().getstate();
        city_data= MyApplication.getInstance().getPrefManager().getcity();
        Area_data= MyApplication.getInstance().getPrefManager().getarea();
        c//username= (TextView) findViewById(R.id.username_nav);
        email= (TextView) findViewById(R.id.email);



        //user_name=MyApplication.getInstance().getPrefManager().getfirstname();
        email_id=MyApplication.getInstance().getPrefManager().getemail();
        username.setText(user_name);
        email.setText(email_id);*/
        email_data= MyApplication.getInstance().getPrefManager().getemail();

        fname = MyApplication.getInstance().getPrefManager().getfirstname();
        lname = MyApplication.getInstance().getPrefManager().getlastname();
        user_type  =MyApplication.getInstance().getPrefManager().getcheckbox();

//        Toast.makeText(this, "fname--" + fname,
//                Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "lname--" + lname,
//                Toast.LENGTH_SHORT).show();

       /* checkboxlist=MyApplication.getInstance().getPrefManager().getcheckbox();
for(String str: checkboxlist){
            Toast.makeText(this, "value selected" + str,Toast.LENGTH_SHORT).show();
        }
       */
        country_data = MyApplication.getInstance().getPrefManager().getcountry();
        state_data = MyApplication.getInstance().getPrefManager().getstate();
        city_data = MyApplication.getInstance().getPrefManager().getcity();
        get_phoneno = MyApplication.getInstance().getPrefManager().getphoneno();
        /*Log.v("getcountry", country_data);
        Log.v("getcity", city_data);
        Log.v("getstate", state_data);*/
//        Toast.makeText(this, "getno--" + get_phoneno,
//                Toast.LENGTH_SHORT).show();



        // hetal comment Log.v("getemail", email_data);

       /* Toast.makeText(this, country_data,
                Toast.LENGTH_SHORT).show();
        Toast.makeText(this, state_data,
                Toast.LENGTH_SHORT).show();*/

        //email_id.setText(email_data);
        //username.setText(fname);
        User_OR_Provider = MyApplication.getInstance().getPrefManager().getuser_mode();
        if (User_OR_Provider == null) {
            User_OR_Provider = "User";
        }
//        Toast.makeText(this, User_OR_Provider,
//                Toast.LENGTH_SHORT).show();

//Log.v("URL","#"+URL);
       /* Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            Log.v("Value", "#" + bundle.getString("user").toString());
            User_OR_Provider = bundle.getString("user");
        } else {
            User_OR_Provider = "service_provider";
        }*/
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigationview);
        View header=navigationView.getHeaderView(0);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        username= (TextView)header. findViewById(R.id.username_nav);
        email= (TextView)header. findViewById(R.id.email_nav);
        username.setText(fname+" "+lname);
        email.setText(email_data);

       // drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);


        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //my new code
        /*String str=MyApplication.getInstance().getPrefManager().getcheckbox();
        Log.v("preference value",str);
        if (User_OR_Provider.equalsIgnoreCase("Service Provider")) {
            Menu m = navigationView.getMenu();
            if (MyApplication.getInstance().getPrefManager().getcheckbox().equalsIgnoreCase("2")) {
                m.findItem(R.id.SP_feedback).setVisible(true);
                m.findItem(R.id.SP_history).setVisible(true);
                m.findItem(R.id.SP_offer).setVisible(true);
                m.findItem(R.id.SP_profile).setVisible(true);
                m.findItem(R.id.SP_rating).setVisible(true);
                m.findItem(R.id.SP_tiffinMenu).setVisible(true);

                m.findItem(R.id.service).setVisible(false);
                m.findItem(R.id.Edit).setVisible(false);
                m.findItem(R.id.feedback).setVisible(false);
                m.findItem(R.id.history).setVisible(false);
                m.findItem(R.id.rating).setVisible(false);
                m.findItem(R.id.offer).setVisible(false);
                m.findItem(R.id.SP_tiffinMenu).setVisible(false);
            }
            else {
                m.findItem(R.id.SP_feedback).setVisible(true);
                m.findItem(R.id.SP_history).setVisible(true);
                m.findItem(R.id.SP_offer).setVisible(true);
                m.findItem(R.id.SP_profile).setVisible(true);
                m.findItem(R.id.SP_rating).setVisible(true);

                m.findItem(R.id.service).setVisible(false);
                m.findItem(R.id.Edit).setVisible(false);
                m.findItem(R.id.feedback).setVisible(false);
                m.findItem(R.id.history).setVisible(false);
                m.findItem(R.id.rating).setVisible(false);
                m.findItem(R.id.offer).setVisible(false);
            }


        }
        if (User_OR_Provider.equalsIgnoreCase("User")) {
            Menu m = navigationView.getMenu();
            m.findItem(R.id.service).setVisible(true);
            m.findItem(R.id.Edit).setVisible(true);
            m.findItem(R.id.feedback).setVisible(true);
            m.findItem(R.id.history).setVisible(true);
            m.findItem(R.id.rating).setVisible(true);
            m.findItem(R.id.offer).setVisible(true);

            m.findItem(R.id.SP_feedback).setVisible(false);
            m.findItem(R.id.SP_history).setVisible(false);
            m.findItem(R.id.SP_offer).setVisible(false);
            m.findItem(R.id.SP_profile).setVisible(false);
            m.findItem(R.id.SP_rating).setVisible(false);


        }



*/
        if (User_OR_Provider.equalsIgnoreCase("Service Provider")) {
            Menu m = navigationView.getMenu();
            //m.findItem(R.id.service).setVisible(false);
            m.findItem(R.id.aboutUs).setVisible(true);
            m.findItem(R.id.shareApp).setVisible(true);

            if( MyApplication.getInstance().getPrefManager().getcheckbox().equalsIgnoreCase("2"))
            {
                m.findItem(R.id.SP_feedback).setVisible(true);
                m.findItem(R.id.SP_history).setVisible(true);
                m.findItem(R.id.SP_offer).setVisible(true);
                m.findItem(R.id.SP_profile).setVisible(true);
                m.findItem(R.id.SP_rating).setVisible(true);
                m.findItem(R.id.SP_tiffinMenu).setVisible(true);

            }
            else {
                m.findItem(R.id.SP_feedback).setVisible(true);
                m.findItem(R.id.SP_history).setVisible(true);
                m.findItem(R.id.SP_offer).setVisible(true);
                m.findItem(R.id.SP_profile).setVisible(true);
                m.findItem(R.id.SP_rating).setVisible(true);
                m.findItem(R.id.SP_tiffinMenu).setVisible(false);


            }


            m.findItem(R.id.dashboard).setVisible(false);
            m.findItem(R.id.Edit).setVisible(false);
            m.findItem(R.id.feedback).setVisible(false);
            m.findItem(R.id.history).setVisible(false);
            m.findItem(R.id.offer).setVisible(false);



        }
        if (User_OR_Provider.equalsIgnoreCase("User")) {
            Menu m = navigationView.getMenu();
            m.findItem(R.id.dashboard).setVisible(true);
            m.findItem(R.id.Edit).setVisible(true);
            m.findItem(R.id.feedback).setVisible(true);
            m.findItem(R.id.history).setVisible(true);
            m.findItem(R.id.offer).setVisible(true);
            m.findItem(R.id.aboutUs).setVisible(true);
            m.findItem(R.id.shareApp).setVisible(true);


            m.findItem(R.id.SP_feedback).setVisible(false);
            m.findItem(R.id.SP_history).setVisible(false);
            m.findItem(R.id.SP_offer).setVisible(false);
            m.findItem(R.id.SP_profile).setVisible(false);
            m.findItem(R.id.SP_rating).setVisible(false);

            m.findItem(R.id.SP_tiffinMenu).setVisible(false);

        }
        //END NEW
       /* if (User_OR_Provider.equalsIgnoreCase("Service_Provider")) {
            Menu m = navigationView.getMenu();
            m.findItem(R.id.service).setVisible(false);
            m.findItem(R.id.Edit).setVisible(true);
            m.findItem(R.id.feedback).setVisible(true);
            m.findItem(R.id.history).setVisible(true);
            m.findItem(R.id.rating).setVisible(true);
            m.findItem(R.id.offer).setVisible(true);


        }
        if (User_OR_Provider.equalsIgnoreCase("User")) {
            Menu m = navigationView.getMenu();
            m.findItem(R.id.service).setVisible(true);
            m.findItem(R.id.Edit).setVisible(true);
            m.findItem(R.id.feedback).setVisible(true);
            m.findItem(R.id.history).setVisible(true);
            m.findItem(R.id.rating).setVisible(true);
            m.findItem(R.id.offer).setVisible(true);

        }*/


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                navigationView.setSelected(true);

                switch (item.getItemId()) {
                    case R.id.dashboard:
                        Intent edit = new Intent(getApplicationContext(), Home.class);
                        startActivity(edit);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.feedback:

                        Intent feedback1 = new Intent(getApplicationContext(), feedback.class);
                        startActivity(feedback1);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.history:
                        Intent history1 = new Intent(getApplicationContext(), history.class);
                        startActivity(history1);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.Edit:
                        Intent service1 = new Intent(getApplicationContext(), Edit.class);
                        startActivity(service1);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.offer:
                        Intent offer1 = new Intent(getApplicationContext(), offer.class);
                        startActivity(offer1);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.SP_profile:
//                        Intent edit1 = new Intent(getApplicationContext(), Service_profile.class);
//                        startActivity(edit1);
//                        drawerLayout.closeDrawers();
//                        break;
                        Intent edit1 = new Intent(getApplicationContext(), Service_profile.class);
                        Log.v("SP value:",user_type);
                        edit1.putExtra("SP",user_type);
                        startActivity(edit1);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.SP_feedback:

                        Intent feedback2 = new Intent(getApplicationContext(), Service_feedback.class);
                        startActivity(feedback2);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.SP_history:
                        Intent history2 = new Intent(getApplicationContext(), Service_history.class);
                        startActivity(history2);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.SP_rating:
                        Intent rating2 = new Intent(getApplicationContext(), Service_rating.class);
                        startActivity(rating2);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.SP_offer:
                        Intent offer2 = new Intent(getApplicationContext(), Service_offer.class);
                        startActivity(offer2);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.SP_tiffinMenu:
                       /* if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                            drawerLayout.closeDrawers();
                            TiffinMenuFragment Tiffinfragment=new TiffinMenuFragment();
                            android.app.FragmentManager fm = getFragmentManager();
                            Tiffinfragment.show(fm, "menu");

                        }*/
                        Intent tiffin = new Intent(getApplicationContext(), TiffinMenuActivity.class);
                        startActivity(tiffin);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.shareApp:
                        try {
                            Intent share = new Intent(Intent.ACTION_SEND);
                            share.setType("text/plain");
                            share.putExtra(Intent.EXTRA_SUBJECT, "Helping Hands");
                            share.putExtra(Intent.EXTRA_TEXT,"Hello");
                            startActivity(Intent.createChooser(share, "Helping Hands"));
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                }
                return false;
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Snackbar.make(coordinator,"Press Back again to Exit.", Snackbar.LENGTH_LONG).show();
            /*Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();*/
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
