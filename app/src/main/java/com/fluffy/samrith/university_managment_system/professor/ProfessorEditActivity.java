package com.fluffy.samrith.university_managment_system.professor;

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

public class ProfessorEditActivity extends AppCompatActivity {

    TextView name ;
    TextView dept;
    TextView code;
    TextView office;
    TextView phone;
    TextView college;
    TextView dates;
    TextView ranks;
    Button save;


    Button saves;
    Bundle b;
    String url ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_edit);

        name = (TextView)findViewById(R.id.name);
        dept = (TextView)findViewById(R.id.dept);
        code = (TextView)findViewById(R.id.code);
        office = (TextView)findViewById(R.id.office);
        phone = (TextView)findViewById(R.id.phone);
        saves = (Button)findViewById(R.id.save);
        college = (TextView)findViewById(R.id.college);
        dates = (TextView)findViewById(R.id.dates);
        ranks = (TextView)findViewById(R.id.rank);
        save = (Button)findViewById(R.id.save);

        b= new Bundle();
        b = getIntent().getExtras();

        if (b.getString("func").equals("edit")){
            this.setTitle("Update Professor");
            name.setText(b.getString("name"));
            dept.setText(b.getString("Dcode"));
            code.setText(b.getString("code"));
            phone.setText(b.getString("phone"));
            office.setText(b.getString("office"));
            college.setText(b.getString("CCode"));
            ranks.setText(b.getString("rank"));
            dates.setText(b.getString("dates"));


        }
        else{
            this.setTitle("Add Professor");
        }

        saves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(b.getString("func").equals("edit")){
                    url = Database.INSTRUCTOR+"?opt=upd";
                    url+= "&code="+ code.getText().toString();
                    url+= "&office="+ office.getText().toString();
                    url+= "&Cname="+ college.getText().toString();
                    url+= "&phone="+ phone.getText().toString();
                    url+= "&DCode="+ dept.getText().toString();
                    url+= "&dates="+ dates.getText().toString();
                    url+= "&name="+ name.getText().toString();
                    url+= "&rank="+ ranks.getText().toString();
                    Log.d("volley",url);
                    updates(url);

                }else{
                    url = Database.INSTRUCTOR+"?opt=adds";
                    url+= "&code="+ code.getText().toString();
                    url+= "&office="+ office.getText().toString();
                    url+= "&Cname="+ college.getText().toString();
                    url+= "&phone="+ phone.getText().toString();
                    url+= "&DCode="+ dept.getText().toString();
                    url+= "&dates="+ dates.getText().toString();
                    url+= "&name="+ name.getText().toString();
                    url+= "&rank="+ ranks.getText().toString();
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
