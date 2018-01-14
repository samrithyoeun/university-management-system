package com.fluffy.samrith.university_managment_system.student;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.fluffy.samrith.university_managment_system.R;
import com.fluffy.samrith.university_managment_system.model.Session;
import com.fluffy.samrith.university_managment_system.sampledata.Database;
import com.fluffy.samrith.university_managment_system.sampledata.MySingleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import recyclerview.RowAdapter;
import recyclerview.RowItem;
import recyclerview.RowListener;

public class CheckGrade extends AppCompatActivity {
    private ArrayList<RowItem> RowItemList = new ArrayList<>();
    private SearchView searchView;
    private RecyclerView recyclerView;
    private RowAdapter mAdapter;
    String function;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_grade);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("YOUR_PREF_NAME", MODE_PRIVATE);
        String sid = pref.getString("sid","0");

        this.setTitle("Check Grade");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewList);
        mAdapter = new RowAdapter(this, RowItemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);


        String url = Database.GRADE+"?code="+sid;
        Log.d("volley",url+"");
        JsonArrayRequest js = new JsonArrayRequest(Request.Method.GET,url , null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("volley",url);
                int count = 0;
                try {

                    while (count < response.length()) {
                        JSONObject j = response.getJSONObject(count);
                        RowItemList.add(new RowItem(1,j.getString("CoName")+"\n"+j.getString("Grade")));
                        Log.d("volley", RowItemList.get(count).toString());
                        count++;

                    }

                    mAdapter.notifyDataSetChanged();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(js);


        mAdapter.setOnClick(new RowListener() {
            @Override
            public void onRowClick(RowItem row) {

                          }
        });

    }

}
