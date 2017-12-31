package com.fluffy.samrith.university_managment_system;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import recyclerview.MRecyclerViewAdapter;
import recyclerview.RowItem;
import recyclerview.RowListener;

public class StudentHomeActivity extends AppCompatActivity {

    private ArrayList<RowItem> RowItemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        prepareRowItemData();
        recyclerView = (RecyclerView) findViewById(R.id.studentFeatureList);

        mAdapter = new MRecyclerViewAdapter(this,RowItemList);

        recyclerView.setHasFixedSize(true);

        // vertical RecyclerView
        // keep RowItem_list_row.xml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),2);

        int spanCount = 3; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = false;

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        // row click listener
        mAdapter.setOnClick(new RowListener() {
            @Override
            public void onRowClick(RowItem row) {
                //list what to do to each item on the row here
                Toast.makeText(getApplicationContext(), row.getName(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void prepareRowItemData() {
        RowItemList.add( new RowItem(1,"Check\nSchedule","schedule.png"));
        RowItemList.add( new RowItem(1,"Check\nSchedule","schedule.png"));
        RowItemList.add( new RowItem(1,"Check\nSchedule","schedule.png"));
        RowItemList.add( new RowItem(1,"Check\nSchedule","schedule.png"));

    }
}
