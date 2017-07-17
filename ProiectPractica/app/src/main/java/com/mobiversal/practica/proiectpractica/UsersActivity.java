package com.mobiversal.practica.proiectpractica;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersActivity extends AppCompatActivity {

    private RecyclerView mUsersList;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_users );
        Intent intent = getIntent();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child( "users" );

        mUsersList = (RecyclerView) findViewById( R.id.users_list );
        mUsersList.setHasFixedSize( true );
        mUsersList.setLayoutManager( new LinearLayoutManager( this ) );
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Users, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Users, UsersViewHolder>(

                Users.class,
                R.layout.users_single_layout,
                UsersViewHolder.class,
                mDatabaseReference
        ) {
            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, Users model, int position) {

               viewHolder.setName( model.getDisplayName());
                viewHolder.setStatus(model.getStatus());
                viewHolder.setImage(model.getThumb_image(), getApplicationContext());

                final String user_id = getRef( position ).getKey();


                viewHolder.mView.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent profilIntent = new Intent( UsersActivity.this, ProfilActivity.class );
                        profilIntent.putExtra( "user_id", user_id );
                        startActivity( profilIntent );

                    }
                } );

            }
        };

        mUsersList.setAdapter( firebaseRecyclerAdapter );


    }

    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public UsersViewHolder(View itemView) {
            super( itemView );

            mView=itemView;
        }

        public void setName(String displayName){

            TextView userNameView = (TextView) mView.findViewById( R.id.user_single_namr );
            userNameView.setText( displayName );

        }

        public void setStatus(String status){
            TextView userStatusView = (TextView) mView.findViewById( R.id.user_single_status );
            userStatusView.setText( status );
        }

        public void setImage(String thumb_image, Context ctx){
            CircleImageView userImageView = (CircleImageView) mView.findViewById( R.id.user_single_image );
            Picasso.with( ctx ).load( thumb_image ).placeholder( R.drawable.default_avatar ).into( userImageView );

        }
    }
}
