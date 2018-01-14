package com.fluffy.samrith.university_managment_system.student;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.fluffy.samrith.university_managment_system.MainActivity;
import com.fluffy.samrith.university_managment_system.R;
import com.fluffy.samrith.university_managment_system.professor.ProfessorActivity;

import java.util.ArrayList;

import recyclerview.RowAdapter;
import recyclerview.RowItem;
import recyclerview.RowListener;

public class StudentHomeActivity extends AppCompatActivity {

    private ArrayList<RowItem> RowItemList ;
    private RecyclerView recyclerView;
    private RowAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        this.setTitle("Welcome");
        recyclerView = (RecyclerView) findViewById(R.id.studentFeatureList);




        RowItemList= new ArrayList<>();
        RowItemList.add( new RowItem(1,"Check\nTimetable","schedule"));
        RowItemList.add( new RowItem(2,"Professor\nInformation","professor"));
        RowItemList.add( new RowItem(4,"Personal\nInformation","student"));
        RowItemList.add( new RowItem(5,"Log out","logout"));

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




                switch (row.id){
                    case 1:
                        Intent i ;
                        i = new Intent(getApplicationContext(),ScheduleActivity.class);
                        startActivity(i);

                    break;
                    case 2:
                        Intent j = new Intent(getApplicationContext(),ProfessorActivity.class);
                            j.putExtra("func","view");
                        startActivity(j);
                    break;
                    case 4:

                        Intent k = new Intent(getApplicationContext(),PersonalInfoActivity.class);
                        startActivity(k);
                        break;
                    case 5:

                        SharedPreferences pref = getApplicationContext().getSharedPreferences("YOUR_PREF_NAME", MODE_PRIVATE);
                        SharedPreferences.Editor edt = pref.edit();
                        edt.clear();
                        edt.commit();
                        Intent l = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(l);

                        finish();
                        break;
                    default: Toast.makeText(getApplicationContext(), row.getName(), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }




}
