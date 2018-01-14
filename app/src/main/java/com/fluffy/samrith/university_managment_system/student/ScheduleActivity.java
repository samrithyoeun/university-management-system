package com.fluffy.samrith.university_managment_system.student;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

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
import recyclerview.Schedule;
import recyclerview.ScheduleAdapter;

public class ScheduleActivity extends AppCompatActivity {


    private ArrayList<Schedule> RowItemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ScheduleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("YOUR_PREF_NAME", MODE_PRIVATE);
        String sid = pref.getString("sid","0");

        String url = Database.SCHEDULE+"?id="+sid;
        Log.d("volley",url);
        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
        prepareRowItemData(url);
        this.setTitle("Schedule");
        recyclerView = (RecyclerView) findViewById(R.id.scheduleList);

        mAdapter = new ScheduleAdapter(this,RowItemList);

        recyclerView.setHasFixedSize(true);

        // vertical RecyclerView
        // keep RowItem_list_row.xml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);


    }
    private void prepareRowItemData(String url ){

        JsonArrayRequest js = new JsonArrayRequest(Request.Method.GET,url , null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("volley",url);
                int count = 0;
                try {
                    Log.d("volley",response.toString());
                    while (count < response.length()) {
                        JSONObject j = response.getJSONObject(count);
                        RowItemList.add(new Schedule(j.getString("DaysTime"),"SUBJECT : "+ j.getString("CoName"),
                                "ROOM : " +j.getString("RoomNo"),
                                "PRO. "+j.getString("IName")));
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




    }



}
