package com.fluffy.samrith.university_managment_system.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.fluffy.samrith.university_managment_system.R;

import java.util.ArrayList;

import recyclerview.RowAdapter;
import recyclerview.RowItem;
import recyclerview.RowListener;

public class StudentHomeActivity extends AppCompatActivity {

    private ArrayList<RowItem> RowItemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RowAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        prepareRowItemData();
        this.setTitle("Welcome");
        recyclerView = (RecyclerView) findViewById(R.id.studentFeatureList);

        mAdapter = new RowAdapter(this,RowItemList);

        recyclerView.setHasFixedSize(true);

        // vertical RecyclerView
        // keep RowItem_list_row.xml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        // row click listener
        mAdapter.setOnClick(new RowListener() {
            @Override
            public void onRowClick(RowItem row) {
                //list what to do to each item on the row here

                Intent i=null;
                if(row.id==1){
                    i = new Intent(getApplicationContext(),ScheduleActivity.class);
                }else{
                    Toast.makeText(getApplicationContext(), row.getName(), Toast.LENGTH_SHORT).show();
                }

                startActivity(i);
            }
        });


    }

    private void prepareRowItemData() {
        RowItemList.add( new RowItem(1,"Check\nTimetable","schedule"));
        RowItemList.add( new RowItem(2,"Professor\nInformation","professor"));
        RowItemList.add( new RowItem(3,"University\nInformation","university"));
        RowItemList.add( new RowItem(4,"Personal\nInformation","student"));
        RowItemList.add( new RowItem(5,"Log out","logout"));

    }
}
