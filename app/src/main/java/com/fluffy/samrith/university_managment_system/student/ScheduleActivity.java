package com.fluffy.samrith.university_managment_system.student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fluffy.samrith.university_managment_system.R;

import java.util.ArrayList;
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

        prepareRowItemData();
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
    private void prepareRowItemData(){
        RowItemList.add(new Schedule("monday"));
        RowItemList.add(new Schedule(1,"2-4pm","SoftwareEnginering","IE-201","Pro.Daru Sima"));
        RowItemList.add(new Schedule(1,"2-4pm","SoftwareEnginering","IE-201","Pro.Daru Sima"));
        RowItemList.add(new Schedule(1,"2-4pm","SoftwareEnginering","IE-201","Pro.Daru Sima"));
        RowItemList.add(new Schedule(1,"2-4pm","SoftwareEnginering","IE-201","Pro.Daru Sima"));
        RowItemList.add(new Schedule(1,"2-4pm","SoftwareEnginering","IE-201","Pro.Daru Sima"));
        RowItemList.add(new Schedule(1,"2-4pm","SoftwareEnginering","IE-201","Pro.Daru Sima"));

        RowItemList.add(new Schedule("Tuesday"));
        RowItemList.add(new Schedule(1,"2-4pm","SoftwareEnginering","IE-201","Pro.Daru Sima"));
        RowItemList.add(new Schedule(1,"2-4pm","SoftwareEnginering","IE-201","Pro.Daru Sima"));
        RowItemList.add(new Schedule(1,"2-4pm","SoftwareEnginering","IE-201","Pro.Daru Sima"));
        RowItemList.add(new Schedule(1,"2-4pm","SoftwareEnginering","IE-201","Pro.Daru Sima"));
        RowItemList.add(new Schedule(1,"2-4pm","SoftwareEnginering","IE-201","Pro.Daru Sima"));



    }
}
