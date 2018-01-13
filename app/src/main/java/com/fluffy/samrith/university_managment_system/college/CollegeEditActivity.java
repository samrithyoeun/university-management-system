package com.fluffy.samrith.university_managment_system.college;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.fluffy.samrith.university_managment_system.DetailActivity;
import com.fluffy.samrith.university_managment_system.R;
import com.fluffy.samrith.university_managment_system.sampledata.Database;
import com.fluffy.samrith.university_managment_system.sampledata.MySingleton;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class CollegeEditActivity extends AppCompatActivity {

    TextView name ;
    TextView office;
    TextView phone;
    Button saves;
    Bundle b;
    String url ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_edit);

        name = (TextView)findViewById(R.id.name);
        office = (TextView)findViewById(R.id.office);
        phone = (TextView)findViewById(R.id.phone);
        saves = (Button)findViewById(R.id.save);

        b= new Bundle();
        b = getIntent().getExtras();

        if (b.getString("func").equals("edit")){
            this.setTitle("Update");
                name.setText(b.getString("name"));
                office.setText(b.getString("office"));
                phone.setText(b.getString("phone"));


        }
        else{

        }

        saves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(b.getString("func").equals("edit")){
                    url = Database.COLLEGE+"?opt=upd&mainkey="+b.getString("name");
                    url+= "&name="+name.getText().toString();
                    url+= "&office="+office.getText().toString();
                    url+= "&phone="+phone.getText().toString();

                    updateCollege(url);
                }else{

                }
            }
        });

    }


    public void updateCollege(String url ){


        Log.d("activities",this.getClass().getSimpleName());

        StringRequest js = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                if (string.contains(1+"")){
                    onBackPressed();
                }
                else{
                    Log.d("volley",string);
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

    }

}
