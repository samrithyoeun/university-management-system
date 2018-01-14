package com.fluffy.samrith.university_managment_system;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.fluffy.samrith.university_managment_system.college.CollegeActivity;
import com.fluffy.samrith.university_managment_system.sampledata.Database;
import com.fluffy.samrith.university_managment_system.sampledata.MySingleton;
import com.fluffy.samrith.university_managment_system.session.SessionEditActivity;
import com.fluffy.samrith.university_managment_system.student.StudentHomeActivity;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);
        checkIfUsedToLogIn();
        login.setOnClickListener(e->{
            String url = Database.CONNECT + "?opt=login&usernames="+username.getText().toString()+"&passwords="+password.getText().toString();
            Log.d("activities",this.getClass().getSimpleName());


            JsonArrayRequest js = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.d("volley",url);

                    try{
                        String user = response.getJSONObject(0).getString("usernames");
                        String pass = response.getJSONObject(0).getString("passwords");
                        String role = response.getJSONObject(0).getString("role");
                        String sid = response.getJSONObject(0).getString("sid");

                        SharedPreferences pref = getApplicationContext().getSharedPreferences("YOUR_PREF_NAME", MODE_PRIVATE);
                        SharedPreferences.Editor edt = pref.edit();

                        edt.putString("user", user);
                        edt.putString("pass", pass);
                        edt.putString("role", role);
                        edt.putString("sid", sid);
                        edt.commit();

                        if(role.equals("admin")){
                            Intent i = new Intent(getApplicationContext(),Main2Activity.class);
                            startActivity(i);
                            finish();
                        }else{
                            Intent i = new Intent(getApplicationContext(),StudentHomeActivity.class);
                            startActivity(i);
                            finish();
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

    public void signup(View v){
        Toast.makeText(this, "You need to process some paper work to get an account", Toast.LENGTH_SHORT).show();
    }

    public void checkIfUsedToLogIn(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("YOUR_PREF_NAME", MODE_PRIVATE);
        if (pref.getString("role",null)!=null){
            if (pref.getString("role",null).equals("admin")) {
                Intent i = new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(i);
                finish();
            }
            else if (pref.getString("role",null).equals("student")){
                Intent i = new Intent(getApplicationContext(),StudentHomeActivity.class);
                startActivity(i);
                finish();
            }
        }

    }

}
