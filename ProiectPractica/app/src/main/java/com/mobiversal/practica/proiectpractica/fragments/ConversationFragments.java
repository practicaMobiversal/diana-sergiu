package com.mobiversal.practica.proiectpractica.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mobiversal.practica.proiectpractica.*;
import com.mobiversal.practica.proiectpractica.PublicGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 10.07.2017.
 */

public class ConversationFragments extends Fragment {

    private List<PrivatGroup> groupsPList = new ArrayList<>(  );
    private RecyclerView recyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private GroupAdapter mAdapter;
    private DatabaseReference mDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_conversatii, container, false);

        FirebaseDatabase mdatabase = FirebaseDatabase.getInstance();

        mDatabase = mdatabase.getReference().child( "Groups" ).child( "Privat" );

        mDatabase.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot groupSnapshot : dataSnapshot.getChildren()){
                    PrivatGroup privatGroup = groupSnapshot.getValue(PrivatGroup.class);
                    groupsPList.add( privatGroup );


                }


                mAdapter = new GroupAdapter( groupsPList );
                recyclerView = (RecyclerView) view.findViewById( R.id.my_recycler_view8 );
                recyclerView.setHasFixedSize( true );
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getContext() );
                linearLayoutManager.setOrientation( LinearLayoutManager.VERTICAL );
                recyclerView.setLayoutManager( new LinearLayoutManager( getContext() ) );
                recyclerView.setAdapter( mAdapter );

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );

        return view;
    }


    }
