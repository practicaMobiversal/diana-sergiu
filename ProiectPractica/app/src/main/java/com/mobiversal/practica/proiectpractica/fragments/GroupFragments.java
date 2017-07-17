package com.mobiversal.practica.proiectpractica.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mobiversal.practica.proiectpractica.GroupAdapter;
import com.mobiversal.practica.proiectpractica.MyAdapter;
import com.mobiversal.practica.proiectpractica.PublicGroup;
import com.mobiversal.practica.proiectpractica.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 10.07.2017.
 */

public class GroupFragments extends Fragment {

    private List<GroupAdapter> groupsList;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private MyAdapter mAdapter;
    private DatabaseReference mDatabase;

    private FirebaseRecyclerAdapter<PublicGroup, GroupsViewHolder> firebaseRecyclerAdapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grup, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        groupsList = new ArrayList<>();
        mAdapter = new MyAdapter(groupsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference("Groups").child("Public");

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PublicGroup, GroupsViewHolder>(

                PublicGroup.class,
                R.layout.group_list_row,
                GroupsViewHolder.class,
                mDatabase
        ) {

            public GroupsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

                return super.onCreateViewHolder(parent, viewType);
            }


            protected void populateViewHolder(GroupsViewHolder viewHolder, PublicGroup model, int position) {
                Log.i("GroupFragments", "Populate view holder " + position + " with " + model);
                View imageView = viewHolder.itemView.findViewById(R.id.imageButton2);
                imageView.setTag(firebaseRecyclerAdapter.getRef(position).getKey());
                viewHolder.setName(model.getPublicGroupName());
            }


        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);

        return view;
    }



    public void onStart() {
        super.onStart();
    }

}