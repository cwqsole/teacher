package com.example.usercwq.day02work.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usercwq.day02work.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KeChengFragment extends Fragment {

    public KeChengFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ke_shi, container, false);
    }

}
