package com.fluffy.samrith.university_managment_system.session;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fluffy.samrith.university_managment_system.R;
import com.fluffy.samrith.university_managment_system.sampledata.Database;
import com.fluffy.samrith.university_managment_system.sampledata.MySingleton;

public class SessionEditActivity extends AppCompatActivity {

    TextView id ;
    TextView no;
    TextView se;
    TextView sy;
    TextView ro;
    TextView da;
    TextView cc;
    TextView ii;
    Button save;

    Button saves;
    Bundle b;
    String url ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_edit);

        id = (TextView)findViewById(R.id.id);
        no = (TextView)findViewById(R.id.no);
        se = (TextView)findViewById(R.id.se);
        sy = (TextView)findViewById(R.id.sy);
        ro = (TextView)findViewById(R.id.ro);
        saves = (Button)findViewById(R.id.save);
        da = (TextView)findViewById(R.id.da);
        cc = (TextView)findViewById(R.id.cc);
        ii = (TextView)findViewById(R.id.ii);


        b= new Bundle();
        b = getIntent().getExtras();

        if (b.getString("func").equals("edit")){
            this.setTitle("Update Session");
            id.setText(b.getString("id"));
            no.setText(b.getString("no"));
            se.setText(b.getString("se"));
            ro.setText(b.getString("ro"));
            sy.setText(b.getString("sy"));
            da.setText(b.getString("da"));
            ii.setText(b.getString("ii"));
            cc.setText(b.getString("cc"));


        }
        else{
            this.setTitle("Add Session");
        }

        saves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(b.getString("func").equals("edit")){
                    url = Database.SESSION+"?opt=upd";
                    url+= "&se="+ se.getText().toString();
                    url+= "&sy="+ sy.getText().toString();
                    url+= "&cc="+ cc.getText().toString();
                    url+= "&ro="+ ro.getText().toString();
                    url+= "&id="+ id.getText().toString();
                    url+= "&ii="+ ii.getText().toString();
                    url+= "&no="+ no.getText().toString();
                    url+= "&da="+ da.getText().toString();

                    Log.d("volley",url);
                    updates(url);

                }else{
                    url = Database.SESSION+"?opt=adds";
                    url+= "&se="+ se.getText().toString();
                    url+= "&sy="+ sy.getText().toString();
                    url+= "&cc="+ cc.getText().toString();
                    url+= "&ro="+ ro.getText().toString();
                    url+= "&id="+ id.getText().toString();
                    url+= "&ii="+ ii.getText().toString();
                    url+= "&no="+ no.getText().toString();

                    url+= "&da="+ da.getText().toString();
                    Log.d("volley",url);
                    adds(url);
                }
            }
        });

    }


    public void updates(String url ){


        Log.d("activities",this.getClass().getSimpleName());
        Log.d("volley",url);
        StringRequest js = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                if (string.contains("sucess")){
                    onBackPressed();
                    Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_SHORT).show();
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
    public void adds(String url){
        Log.d("activities",this.getClass().getSimpleName());

        StringRequest js = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                if (string.contains("sucess")){
                    Toast.makeText(getApplicationContext(), "Add new entry", Toast.LENGTH_SHORT).show();
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
