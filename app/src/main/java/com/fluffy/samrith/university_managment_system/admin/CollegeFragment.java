package com.fluffy.samrith.university_managment_system.admin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fluffy.samrith.university_managment_system.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollegeFragment extends Fragment {


    public CollegeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_college, container, false);
        return root;
    }

}
