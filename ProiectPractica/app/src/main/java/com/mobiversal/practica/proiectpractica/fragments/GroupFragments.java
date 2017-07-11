package com.mobiversal.practica.proiectpractica.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobiversal.practica.proiectpractica.GroupAdapter;
import com.mobiversal.practica.proiectpractica.MyAdapter;
import com.mobiversal.practica.proiectpractica.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 10.07.2017.
 */

public class GroupFragments extends Fragment {

    private List<GroupAdapter> groupsList ;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private MyAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_grup, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        groupsList = new ArrayList<>();
        mAdapter = new MyAdapter(groupsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        prepareGroupData();

        return view;
    }


    private void prepareGroupData() {
        GroupAdapter g1 = new GroupAdapter("Mad Max: Fury Road",2);
        groupsList.add(g1);

        g1 = new GroupAdapter("Oradea", 5);
        groupsList.add(g1);
        mAdapter.notifyDataSetChanged();
    }


}
