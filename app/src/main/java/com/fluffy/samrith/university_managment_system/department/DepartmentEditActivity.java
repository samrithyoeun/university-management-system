package com.fluffy.samrith.university_managment_system.department;

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

public class DepartmentEditActivity extends AppCompatActivity {

    TextView name ;
    TextView Cname;
    TextView code;
    TextView office;
    TextView phone;

    Button saves;
    Bundle b;
    String url ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_edit);

        name = (TextView)findViewById(R.id.name);
        Cname = (TextView)findViewById(R.id.CName);
        code = (TextView)findViewById(R.id.code);
        office = (TextView)findViewById(R.id.office);
        phone = (TextView)findViewById(R.id.phone);
        saves = (Button)findViewById(R.id.save);

        b= new Bundle();
        b = getIntent().getExtras();

        if (b.getString("func").equals("edit")){
            this.setTitle("Update Department");
            name.setText(b.getString("name"));
            Cname.setText(b.getString("CName"));
            code.setText(b.getString("code"));
            phone.setText(b.getString("phone"));
            office.setText(b.getString("office"));


        }
        else{
            this.setTitle("Add Department");
        }

        saves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(b.getString("func").equals("edit")){
                    url = Database.DEPARTMENT+"?opt=upd";
                    url+= "&code="+ code.getText().toString();
                    url+= "&office="+ office.getText().toString();
                    url+= "&Cname="+ Cname.getText().toString();
                    url+= "&phone="+ phone.getText().toString();
                    url+= "&name="+ name.getText().toString();
                    Log.d("volley",url);
                    updates(url);

                }else{
                    url = Database.DEPARTMENT+"?opt=adds";
                    url+= "&name="+name.getText().toString().replace(" ","%20%");
                    url+= "&code="+code.getText().toString().replace(" ","%20%");
                    url+= "&office="+ office.getText().toString().replace(" ","%20%");
                    url+= "&Cname="+ Cname.getText().toString().replace(" ","%20%");
                    url+= "&phone="+ phone.getText().toString().replace(" ","%20%");
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
