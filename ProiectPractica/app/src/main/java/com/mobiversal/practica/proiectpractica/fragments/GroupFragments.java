package com.mobiversal.practica.proiectpractica.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mobiversal.practica.proiectpractica.GroupAdapter;
import com.mobiversal.practica.proiectpractica.MyAdapter;
import com.mobiversal.practica.proiectpractica.PublicGroup;
import com.mobiversal.practica.proiectpractica.R;
import com.mobiversal.practica.proiectpractica.Users;
import com.mobiversal.practica.proiectpractica.UsersAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Lenovo on 10.07.2017.
 */

public class GroupFragments extends Fragment {

    private List<PublicGroup> groupsList = new ArrayList<>(  );
    private RecyclerView recyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private MyAdapter mAdapter;
    private DatabaseReference mDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_grup, container, false);
        setHasOptionsMenu( true );

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference().child( "Groups" ).child( "Public" );

        mDatabase.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot groupSnapshot : dataSnapshot.getChildren()){
                    PublicGroup groups = groupSnapshot.getValue(PublicGroup.class);
                    groupsList.add( groups );
                }

                mAdapter = new MyAdapter(groupsList);
                recyclerView = (RecyclerView) view.findViewById( R.id.my_recycler_view );
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
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_grup, container, false);
//        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
//
//        groupsList = new ArrayList<>();
//        mAdapter = new MyAdapter(groupsList );
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getContext() );
//        recyclerView.setLayoutManager( mLayoutManager );
//        recyclerView.setItemAnimator( new DefaultItemAnimator() );
//        recyclerView.setAdapter( mAdapter );
//        recyclerView.setHasFixedSize(true);
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        mDatabase = database.getReference("Groups").child("Public");
//
//        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PublicGroup, GroupsViewHolder>(
//
// PublicGroup.class,
//                R.layout.group_list_row,
//                GroupsViewHolder.class,
//                mDatabase
//        ) {
// public GroupsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
//                return super.onCreateViewHolder(parent, viewType);
//            }
//
//
// protected void populateViewHolder(GroupsViewHolder viewHolder, PublicGroup model, int position) {
//                Log.i("GroupFragments", "Populate view holder " + position + " with " + model);
//                View imageView = viewHolder.itemView.findViewById(R.id.imageButton2);
//                imageView.setTag(firebaseRecyclerAdapter.getRef(position).getKey());
//                viewHolder.setName(model.getPublicGroupName());
//            }
//        };
//
// recyclerView.setAdapter(firebaseRecyclerAdapter);
//
// return view;
//    }
//
//
//    public void onStart() {
//        super.onStart();
//    }
//
//}


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem searchItem = menu.findItem( R.id.search );
        SearchView searchView = (SearchView)  searchItem.getActionView();
        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                String text = newText.toString();
                doSearchh( text );

                return false;
            }
        } );

        super.onCreateOptionsMenu( menu, inflater );
    }

    public void doSearchh(String queryStr) {
        queryStr = queryStr.toLowerCase( Locale.getDefault() );

        Log.i( "Your search: ", queryStr );

        List<PublicGroup> filteredgroups = new ArrayList<>();

        for (PublicGroup group : groupsList) {
            String displayName = group.getPublicGroupName().toLowerCase( Locale.getDefault() );

            if (displayName.contains( queryStr ))
                filteredgroups.add( group );
        }

        mAdapter = new MyAdapter( filteredgroups );
        recyclerView.setAdapter( mAdapter );
    }
}