package com.fluffy.samrith.university_managment_system.admin;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fluffy.samrith.university_managment_system.R;
import com.fluffy.samrith.university_managment_system.college.CollegeActivity;
import com.fluffy.samrith.university_managment_system.college.CollegeEditActivity;
import com.fluffy.samrith.university_managment_system.professor.ProfessorActivity;
import com.fluffy.samrith.university_managment_system.student.PersonalInfoActivity;
import com.fluffy.samrith.university_managment_system.student.ScheduleActivity;

import java.util.ArrayList;

import recyclerview.RowAdapter;
import recyclerview.RowItem;
import recyclerview.RowListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollegeFragment extends Fragment {


    private View root;
    private ArrayList<RowItem> RowItemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RowAdapter mAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =inflater.inflate(R.layout.fragment_college, container, false);

        prepareRowItemData();
        recyclerView = (RecyclerView)root.findViewById(R.id.recyclerViewList);

        mAdapter = new RowAdapter(getContext(),RowItemList);

        recyclerView.setHasFixedSize(true);

        // vertical RecyclerView
        // keep RowItem_list_row.xml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        // row click listener
        mAdapter.setOnClick(new RowListener() {
            @Override
            public void onRowClick(RowItem row) {
                Intent i ;
                switch (row.getId()){
                    case 1:
                        i = new Intent(getContext(), CollegeActivity.class);
                        i.putExtra("func","view");
                        startActivity(i);
                        break;
                    case 2:
                        i = new Intent(getContext(), CollegeEditActivity.class);
                        i.putExtra("func","add");
                        startActivity(i);
                        break;
                    case 3:
                        i = new Intent(getContext(), CollegeActivity.class);
                        i.putExtra("func","edit");
                        startActivity(i);
                        break;
                    case 4:
                        i = new Intent(getContext(), CollegeActivity.class);
                        i.putExtra("func","delete");
                        startActivity(i);
                        break;

                }
            }
        });

        return root;

    }

    private void prepareRowItemData() {
        RowItemList.add( new RowItem(1,"View College","zoom"));
        RowItemList.add( new RowItem(2,"Add College","createnew"));
        RowItemList.add( new RowItem(3,"Edit College","pencil"));
        RowItemList.add( new RowItem(4,"Delete College","trash"));

    }
}
