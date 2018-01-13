package com.fluffy.samrith.university_managment_system.course;

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
import com.fluffy.samrith.university_managment_system.college.CollegeEditActivity;
import com.fluffy.samrith.university_managment_system.sampledata.Database;
import com.fluffy.samrith.university_managment_system.sampledata.MySingleton;

public class CourseEditActivity extends AppCompatActivity {

    TextView name ;
    TextView desc;
    TextView code;
    TextView credit ;
    TextView level;
    TextView dcodes;

    Button saves;
    Bundle b;
    String url ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_edit);

        name = (TextView)findViewById(R.id.name);
        desc = (TextView)findViewById(R.id.desc);
        code = (TextView)findViewById(R.id.code);
        credit = (TextView)findViewById(R.id.credit);
        dcodes = (TextView)findViewById(R.id.decodes);
        level = (TextView)findViewById(R.id.level);
        saves = (Button)findViewById(R.id.save);

        b= new Bundle();
        b = getIntent().getExtras();

        if (b.getString("func").equals("edit")){
            this.setTitle("Update Course");
            name.setText(b.getString("name"));
            desc.setText(b.getString("desc"));
            code.setText(b.getString("code"));
            dcodes.setText(b.getString("dcodes"));
            level.setText(b.getString("level"));
            credit.setText(b.getString("credit"));


        }
        else{
            this.setTitle("Add Course");
        }

        saves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(b.getString("func").equals("edit")){
                    url = Database.COURSE+"?opt=upd&mainkey="+b.getString("name");
                    url+= "&name="+name.getText().toString();
                    url+= "&code="+code.getText().toString();
                    url+= "&dcodes="+dcodes.getText().toString();
                    url+= "&credit="+credit.getText().toString();
                    url+= "&desc="+desc.getText().toString();
                    url+= "&level="+level.getText().toString();
                    Log.d("volley",url);
                    updates(url);
                }else{
                    url = Database.COURSE+"?opt=adds";
                    url+= "&name="+name.getText().toString();
                    url+= "&code="+code.getText().toString();
                    url+= "&dcodes="+dcodes.getText().toString();
                    url+= "&credit="+credit.getText().toString();
                    url+= "&desc="+desc.getText().toString();
                    url+= "&level="+level.getText().toString();
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
