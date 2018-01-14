package com.fluffy.samrith.university_managment_system.admin;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.fluffy.samrith.university_managment_system.R;
import com.fluffy.samrith.university_managment_system.model.College;
import com.fluffy.samrith.university_managment_system.professor.ProfessorActivity;
import com.fluffy.samrith.university_managment_system.sampledata.Database;
import com.fluffy.samrith.university_managment_system.sampledata.MySingleton;
import com.fluffy.samrith.university_managment_system.student.PersonalInfoActivity;
import com.fluffy.samrith.university_managment_system.student.ScheduleActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import recyclerview.RowAdapter;
import recyclerview.RowItem;
import recyclerview.RowListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private View root;
    private ArrayList<RowItem> RowItemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RowAdapter mAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         root =inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView)root.findViewById(R.id.recyclerViewList);
        recyclerView.setHasFixedSize(true);


        String url = Database.HOME;
        JsonArrayRequest js = new JsonArrayRequest(Request.Method.GET,url , null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("volley",url);
                int count = 0;
                try {

                    RowItemList.add( new RowItem(1,"College " + response.getJSONObject(0).getString("col"),"faculty"));
                    RowItemList.add( new RowItem(2,"Department "+ response.getJSONObject(1).getString("dep"),"department"));
                    RowItemList.add( new RowItem(3,"Instructor "+ response.getJSONObject(2).getString("inst"),"professor"));
                    RowItemList.add( new RowItem(4,"Student "+ response.getJSONObject(3).getString("stu"),"student"));
                    RowItemList.add( new RowItem(5,"Course "+ response.getJSONObject(4).getString("cou"),"course"));
                    RowItemList.add( new RowItem(6,"Session "+ response.getJSONObject(5).getString("section"),"session"));

                    Log.d("volley", RowItemList.get(count).toString());
                    count++;

                    mAdapter = new RowAdapter(getContext(),RowItemList);
                    // vertical RecyclerView
                    // keep RowItem_list_row.xml width to `match_parent`
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),2);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setAdapter(mAdapter);
                    // row click listener


                    mAdapter.setOnClick(new RowListener() {
                        @Override
                        public void onRowClick(RowItem row) {

                        }});



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MySingleton.getInstance(getContext()).addToRequestQueue(js);



        return root;

    }

}
