package com.example.hetal13.afinal;

/**
 * Created by Hetal13 on 13-12-2016.
 */


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lincoln on 05/05/16.
 */
public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    ArrayList<String> list1,check_list;
   ArrayList<String> check_set;
    // Shared preferences file name
    private static final String USER_TYPE = "user_type";
    private static final String Email = "email";
    private static final String First_Name = "FirstName";
    private static final String L_name = "LastName";
    private static final String Phone_No = "Phone_No";
    private static final String Country = "Country";
    private static final String State = "State";
    private static final String City = "City";
    private static final String Area = "Area";
    private static final String Skill_Type = "Skill_Type";
    private static final String checkboxlist="checkboxlist";
    private static final String PREF_NAME = "crm-registration";
    private static final String Firsttime = "firsttime";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";


    private static final String PG_details = "PG_details";
    private static final String Contractor_details = "Contractor_details";
    private static final String Mechanic_details = "Mechanic_details";
    private static final String Tiffin_details = "Tiffin_details";
    private static final String SecurityGuard_details = "SecurityGuard_details";
    private static final String Driver_details = "PG_details";
    private static final String Maid_details = "Maid_details";


    public void storePGdetails(String pg_details) {
        editor.putString(PG_details, pg_details);
        editor.commit();

    }


    public String getPGdetails() {
        if (pref.getString(PG_details, null) != null) {
            String pg_details;
            pg_details = pref.getString(PG_details, null);
            return pg_details;
        }
        return null;
    }

    public void storeContractordetails(String contractor_details) {
        editor.putString(Contractor_details, contractor_details);
        editor.commit();

    }


    public String getContractordetails() {
        if (pref.getString(Contractor_details, null) != null) {
            String contractor_details;
            contractor_details = pref.getString(Contractor_details, null);
            return contractor_details;
        }
        return null;
    }

    public void storeMechanicdetails(String mechamic_details) {
        editor.putString(Mechanic_details, mechamic_details);
        editor.commit();

    }


    public String getMechanicdetails() {
        if (pref.getString(Mechanic_details, null) != null) {
            String mechamic_details;
            mechamic_details = pref.getString(Mechanic_details, null);
            return mechamic_details;
        }
        return null;
    }
    public void storeSecurityGuarddetails(String securityGuard_details) {
        editor.putString(SecurityGuard_details, securityGuard_details);
        editor.commit();

    }


    public String getSecurityGuarddetails() {
        if (pref.getString(SecurityGuard_details, null) != null) {
            String securityGuard_details;
            securityGuard_details = pref.getString(SecurityGuard_details, null);
            return securityGuard_details;
        }
        return null;
    }
    public void storeTiffindetails(String tiffin_details) {
        editor.putString(Tiffin_details, tiffin_details);
        editor.commit();

    }


    public String getTiffindetails() {
        if (pref.getString(Tiffin_details, null) != null) {
            String tiffin_details;
            tiffin_details = pref.getString(Tiffin_details, null);
            return tiffin_details;
        }
        return null;
    }

    public void storeDriverdetails(String driver_details) {
        editor.putString(Driver_details, driver_details);
        editor.commit();

    }


    public String getDriverdetails() {
        if (pref.getString(Driver_details, null) != null) {
            String driver_details;
            driver_details = pref.getString(Driver_details, null);
            return driver_details;
        }
        return null;
    }
    public void storeMaiddetails(String maid_details) {
        editor.putString(Maid_details, maid_details);
        editor.commit();

    }


    public String getMaiddetails() {
        if (pref.getString(Maid_details, null) != null) {
            String maid_details;
            maid_details = pref.getString(Maid_details, null);
            return maid_details;
        }
        return null;
    }
    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void setisFirstTime( boolean isfirst) {
        editor.putBoolean(Firsttime, isfirst);
        editor.commit();

    }
    public boolean isfirsttime() {

        return pref.getBoolean(Firsttime, true);
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
    public void storeuser_mode(String user_type) {
        editor.putString(USER_TYPE, user_type);
        editor.commit();

    }

    public String getuser_mode() {
        if (pref.getString(USER_TYPE, null) != null) {
            String user_type;
            user_type = pref.getString(USER_TYPE, null);
            return user_type;
        }



        return null;
    }

    public void storeemail(String email_id) {
        editor.putString(Email, email_id);
        editor.commit();

    }

    public String getemail() {
        if (pref.getString(Email, null) != null) {
            String email;
            email = pref.getString(Email, null);
            return email;
        }



        return null;
    }

    public void storecheckbox(String check_list)
    {
        editor.putString(checkboxlist, check_list);
        editor.commit();
    }
    public String getcheckbox()
    {
        if (pref.getString(checkboxlist,null) != null) {
            String check_list;
            check_list = pref.getString(checkboxlist, null);
            return check_list;
        }
        return null;
    }
    public void storefirstname(String first_name) {
        editor.putString(First_Name, first_name);
        editor.commit();

    }

    public String getfirstname() {
        if (pref.getString(First_Name, null) != null) {
            String firstname;
            firstname = pref.getString(First_Name, null);
            return firstname;
        }



        return null;
    }

    public void storelastname(String last_name) {
        editor.putString(L_name, last_name);
        editor.commit();

    }

    public String getlastname() {
        if (pref.getString(L_name, null) != null) {
            String lastname;
            lastname = pref.getString(L_name, null);
            return lastname;
        }



        return null;
    }

    public void storephoneno(String phoneno) {
        editor.putString(Phone_No, phoneno);
        editor.commit();

    }

    public String getphoneno() {
        if (pref.getString(Phone_No, null) != null) {
            String phone_no;
            phone_no = pref.getString(Phone_No, null);
            return phone_no;
        }



        return null;
    }

    public void storecountry(String country) {
        editor.putString(Country, country);
        editor.commit();

    }

    public String getcountry() {
        if (pref.getString(Country, null) != null) {
            String country_name;
            country_name = pref.getString(Country, null);
            return country_name;
        }



        return null;
    }

    public void storestate(String state) {
        editor.putString(State, state);
        editor.commit();

    }

    public String getstate() {
        if (pref.getString(State, null) != null) {
            String state_name;
            state_name = pref.getString(State, null);
            return state_name;
        }



        return null;
    }
    public void storecity(String city) {
        editor.putString(City, city);
        editor.commit();

    }

    public String getcity() {
        if (pref.getString(City, null) != null) {
            String city_name;
            city_name = pref.getString(City, null);
            return city_name;
        }



        return null;
    }
    public void storearea(String area) {
        editor.putString(Area, area);
        editor.commit();

    }

    public String getarea() {
        if (pref.getString(Area, null) != null) {
            String area_name;
            area_name = pref.getString(Area, null);
            return area_name;
        }



        return null;
    }
    public void storeskillset(String skillset) {
        editor.putString(Skill_Type, skillset);
        editor.commit();



    }

    public String getskillset() {
        if (pref.getString(Skill_Type, null) != null) {
            String skill_type;
            skill_type = pref.getString(Skill_Type, null);
            return skill_type;
        }



        return null;
    }



}