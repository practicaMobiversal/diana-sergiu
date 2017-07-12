package com.mobiversal.practica.proiectpractica.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;import android.support.annotation.DrawableRes;import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.mobiversal.practica.proiectpractica.GroupAdapter;
import com.mobiversal.practica.proiectpractica.MainActivity;
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

        // TODO: Remove this
        view.findViewById(R.id.btn_sign_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }, 1000L);
            }
        });

        return view;
    }


    private void prepareGroupData() {
        GroupAdapter g1 = new GroupAdapter("Oradea - Beius","S",null,null);
        groupsList.add(g1);

        g1 = new GroupAdapter();
        groupsList.add(g1);
        mAdapter.notifyDataSetChanged();
    }


}
