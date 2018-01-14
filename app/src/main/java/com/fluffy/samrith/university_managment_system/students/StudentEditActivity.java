package com.fluffy.samrith.university_managment_system.students;

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

public class StudentEditActivity extends AppCompatActivity {

    TextView ad;
    TextView dc;
    TextView fn;
    TextView ln;
    TextView mj;
    TextView ph;
    TextView id;
    TextView bd;

    Button save;

    Button saves;
    Bundle b;
    String url ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_edit);

        ad = (TextView)findViewById(R.id.ad);
        dc = (TextView)findViewById(R.id.dc);
        fn = (TextView)findViewById(R.id.fn);
        ln = (TextView)findViewById(R.id.ln);
        mj = (TextView)findViewById(R.id.mj);
        saves = (Button)findViewById(R.id.save);
        ph = (TextView)findViewById(R.id.ph);
        id = (TextView)findViewById(R.id.id);
        bd = (TextView)findViewById(R.id.bd);



        b= new Bundle();
        b = getIntent().getExtras();

        if (b.getString("func").equals("edit")){
            this.setTitle("Update Session");
            ad.setText(b.getString("ad"));
            dc.setText(b.getString("dc"));
            fn.setText(b.getString("fn"));
            mj.setText(b.getString("mj"));
            ln.setText(b.getString("ln"));
            ph.setText(b.getString("ph"));
            bd.setText(b.getString("bd"));
            id.setText(b.getString("id"));


        }
        else{
            this.setTitle("Add Session");
        }

        saves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(b.getString("func").equals("edit")){
                    url = Database.STUDENT+"?opt=upd";
                    url+= "&fn="+ fn.getText().toString();
                    url+= "&ln="+ ln.getText().toString();
                    url+= "&id="+ id.getText().toString();
                    url+= "&mj="+ mj.getText().toString();
                    url+= "&bd="+ bd.getText().toString();
                    url+= "&dc="+ dc.getText().toString();
                    url+= "&ph="+ ph.getText().toString();
                    url+= "&ad="+ ad.getText().toString();

                    Log.d("volley",url);
                    updates(url);

                }else{
                    url = Database.STUDENT+"?opt=adds";
                    url+= "&fn="+ fn.getText().toString();
                    url+= "&ln="+ ln.getText().toString();
                    url+= "&id="+ id.getText().toString();
                    url+= "&mj="+ mj.getText().toString();
                    url+= "&dc="+ dc.getText().toString();
                    url+= "&ad="+ ad.getText().toString();
                    url+= "&bd="+ bd.getText().toString();
                    url+= "&ph="+ ph.getText().toString();
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
