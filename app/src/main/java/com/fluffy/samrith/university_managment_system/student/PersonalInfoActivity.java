package com.fluffy.samrith.university_managment_system.student;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fluffy.samrith.university_managment_system.Main2Activity;
import com.fluffy.samrith.university_managment_system.R;
import com.fluffy.samrith.university_managment_system.sampledata.Database;
import com.fluffy.samrith.university_managment_system.sampledata.MySingleton;

import org.json.JSONArray;
import org.json.JSONObject;

public class PersonalInfoActivity extends AppCompatActivity {

    TextView us ;
    TextView pa;
    TextView ne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        this.setTitle("Personal Information");
        Button save = (Button)findViewById(R.id.change);
        us = (TextView)findViewById(R.id.us);
        pa = (TextView)findViewById(R.id.ps);
        ne = (TextView)findViewById(R.id.np) ;

        SharedPreferences pref = getApplicationContext().getSharedPreferences("YOUR_PREF_NAME", MODE_PRIVATE);
        String sid = pref.getString("side","0");
        save.setOnClickListener(e->{
            String url = Database.CONNECT + "?opt=update&usernames="+us.getText().toString()+"&passwords="
                    +pa.getText().toString()+"&newpass="+ne.getText().toString()+"&sid="+sid;


            Log.d("activities",this.getClass().getSimpleName());


            JsonObjectRequest js = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("volley",url);

                    try{
                        if(response.getBoolean("role")){

                            SharedPreferences pref = getApplicationContext().getSharedPreferences("YOUR_PREF_NAME", MODE_PRIVATE);
                            SharedPreferences.Editor edt = pref.edit();


                            edt.putString("pass", ne.getText().toString());

                            edt.putString("sid", sid);
                            edt.commit();
                            Toast.makeText(PersonalInfoActivity.this, "password Changed", Toast.LENGTH_SHORT).show();
                            onBackPressed();

                        }else{
                            Toast.makeText(PersonalInfoActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                        }




                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("volley",url);
                }
            });

            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(js);
        });

    }
}
