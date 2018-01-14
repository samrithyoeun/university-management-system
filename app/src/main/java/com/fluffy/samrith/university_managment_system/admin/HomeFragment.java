package com.fluffy.samrith.university_managment_system.admin;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fluffy.samrith.university_managment_system.R;
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
public class HomeFragment extends Fragment {


    private View root;
    private ArrayList<RowItem> RowItemList ;
    private RecyclerView recyclerView;
    private RowAdapter mAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         root =inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView)root.findViewById(R.id.recyclerViewList);
        recyclerView.setHasFixedSize(true);


        RowItemList = new ArrayList<>();
        RowItemList.add( new RowItem(1,"College","faculty"));
        RowItemList.add( new RowItem(2,"Department","department"));
        RowItemList.add( new RowItem(3,"Instructor","professor"));
        RowItemList.add( new RowItem(4,"Student","student"));
        RowItemList.add( new RowItem(5,"Course ","course"));
        RowItemList.add( new RowItem(6,"Session ","session"));

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

        return root;

    }
}
