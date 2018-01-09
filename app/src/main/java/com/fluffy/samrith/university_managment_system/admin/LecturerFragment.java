package com.fluffy.samrith.university_managment_system.admin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fluffy.samrith.university_managment_system.R;

import java.util.ArrayList;

import recyclerview.RowAdapter;
import recyclerview.RowItem;
import recyclerview.RowListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class LecturerFragment extends Fragment {


    private View root;
    private ArrayList<RowItem> RowItemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RowAdapter mAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =inflater.inflate(R.layout.fragment_lecturer, container, false);

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

            }
        });

        return root;

    }

    private void prepareRowItemData() {
        RowItemList.add( new RowItem(1,"View Instrutor","zoom"));
        RowItemList.add( new RowItem(2,"Add Instrutor","createnew"));
        RowItemList.add( new RowItem(3,"Edit Instrutor","pencil"));
        RowItemList.add( new RowItem(4,"Delete Instrutor","trash"));

    }
}
