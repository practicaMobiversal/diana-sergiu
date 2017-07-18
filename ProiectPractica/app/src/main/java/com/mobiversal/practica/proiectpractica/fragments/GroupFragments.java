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
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mobiversal.practica.proiectpractica.GroupAdapter;
import com.mobiversal.practica.proiectpractica.MyAdapter;
import com.mobiversal.practica.proiectpractica.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lenovo on 10.07.2017.
 */

public class GroupFragments extends Fragment {

    private List<Groups> groupsList;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private MyAdapter mAdapter;
    private DatabaseReference mDatabase;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_grup, container, false );
        recyclerView = (RecyclerView) view.findViewById( R.id.my_recycler_view );
        recyclerView.setHasFixedSize( true );

        groupsList = new ArrayList<>();
        mAdapter = new MyAdapter( groupsList );
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getContext() );
        recyclerView.setLayoutManager( mLayoutManager );
        recyclerView.setItemAnimator( new DefaultItemAnimator() );
        recyclerView.setAdapter( mAdapter );


        mDatabase = FirebaseDatabase.getInstance().getReference().child( "Groups" ).child( "Public" );


        //prepareGroupData();


        // TODO: Remove this
// view.findViewById(R.id.btn_sign_out).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Intent intent = new Intent(getContext(), MainActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                    }
//                }, 1000L);
//            }
//        })
        ;
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Groups, GroupViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Groups, GroupViewHolder>(

                Groups.class,
                R.layout.group_list_vieww,
                GroupViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(GroupViewHolder viewHolder, Groups model, int position) {

                viewHolder.setName( model.getpublicGroupName() );

                final String group_id = getRef( position ).getKey();


            }
        };
    }

    public static class GroupViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public GroupViewHolder(View itemView) {
            super( itemView );
            mView = itemView;
        }

        public void setName(String publicGroupName) {
            TextView publicNameView = (TextView) mView.findViewById( R.id.group_single_title );
            publicNameView.setText( publicGroupName );
        }
    }
}



   // private void prepareGroupData() {

       // mDatabase = FirebaseDatabase.getInstance().getReference().child( "groups" ).child( "public" ).push();


//        HashMap<String,String> groupmap = new HashMap<>(  );
//        groupmap.put( "name", "Oradea - Beius" );
//        groupmap.put( "name", "Oradea - Bucuresti" );
//
//        mDatabase.setValue( groupmap );
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRefe = database.getReference("groups").child( "public" ).push();
//
//        myRefe.child( "name" ).setValue( "Oradea - Bucuresti" );
//        myRefe.child( "name" ).setValue( "Oradea - Beius" );
//        myRefe.child( "name" ).setValue( "Oradea - Cluj" );
//
//        GroupAdapter g1 = new GroupAdapter("Oradea - Beius","S",null,null);
//        groupsList.add(g1);
//
//        g1 = new GroupAdapter();
//        groupsList.add(g1);
//        mAdapter.notifyDataSetChanged();
//    }



