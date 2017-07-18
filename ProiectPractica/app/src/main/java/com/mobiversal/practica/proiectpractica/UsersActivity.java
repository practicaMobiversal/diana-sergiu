package com.mobiversal.practica.proiectpractica;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.R.attr.showForAllUsers;
import static android.R.attr.value;

public class UsersActivity extends AppCompatActivity {

    private RecyclerView mUsersList;
    private List<Users> items = new ArrayList<>(  );
    private DatabaseReference mDatabaseReference;
    private EditText editText;
    private UsersAdapter mAdapter;
    private FirebaseDatabase mFirebase;
    private FirebaseRecyclerAdapter<Users, UsersViewHolder> firebaseRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_users );
        Intent intent = getIntent();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabaseReference = database.getReference().child( "users" );
      // mDatabaseReference = FirebaseDatabase.getInstance().getReference().child( "users" );

        mDatabaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    Users user = userSnapshot.getValue( Users.class );
                    items.add(user);
                }

                mAdapter = new UsersAdapter( items );
                mUsersList = (RecyclerView) findViewById( R.id.users_list );
                mUsersList.setHasFixedSize( true );
                LinearLayoutManager llManager = new LinearLayoutManager( UsersActivity.this );
                llManager.setOrientation( LinearLayoutManager.VERTICAL );
                mUsersList.setLayoutManager( new LinearLayoutManager( UsersActivity.this ) );
                mUsersList.setAdapter( mAdapter );
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );






        editText = (EditText) findViewById( R.id.searchusers );

        editText.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String text = s.toString();
                doSearch( text );


            }
        } );


    }
    public void doSearch(String queryStr) {
        queryStr = queryStr.toLowerCase( Locale.getDefault() );

        Log.i( "Your search: ", queryStr );

        List<Users> filteredusers = new ArrayList<>();

        for (Users user : items) {
            String displayName = user.getDisplayName().toLowerCase( Locale.getDefault() );

            if (displayName.contains( queryStr ))
                filteredusers.add( user );
        }

        mAdapter = new UsersAdapter( filteredusers );
        mUsersList.setAdapter( mAdapter );
    }


    public class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public UsersViewHolder(View itemView) {
            super( itemView );

            mView = itemView;
        }

        public void setName(String displayName) {

            TextView userNameView = (TextView) mView.findViewById( R.id.user_single_namr );
            userNameView.setText( displayName );

        }

        public void setStatus(String status) {
            TextView userStatusView = (TextView) mView.findViewById( R.id.user_single_status );
            userStatusView.setText( status );
        }

        public void setImage(String thumb_image, Context ctx) {
            CircleImageView userImageView = (CircleImageView) mView.findViewById( R.id.user_single_image );
            Picasso.with( ctx ).load( thumb_image ).placeholder( R.drawable.default_avatar ).into( userImageView );

        }
    }
}


