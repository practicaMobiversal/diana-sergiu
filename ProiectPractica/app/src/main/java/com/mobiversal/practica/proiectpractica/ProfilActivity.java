package com.mobiversal.practica.proiectpractica;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProfilActivity extends AppCompatActivity {

    private ImageView mProfilImage;
    private TextView mProfilName, mProfilStatus;
    private Button mSendRequest;

    private DatabaseReference mUsersDatabasee;

    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profil );

        String user_id = getIntent().getStringExtra( "user_id" );

        mUsersDatabasee = FirebaseDatabase.getInstance().getReference().child( "users" ).child( user_id );


        mProfilImage = (ImageView) findViewById( R.id.profil_image);
        mProfilName = (TextView) findViewById( R.id.profil_displayName );
        mProfilStatus  = (TextView) findViewById( R.id.profil_status );
        mSendRequest = (Button) findViewById( R.id.send_request );

        mProgressDialog = new ProgressDialog( this );
        mProgressDialog.setTitle( "Incarcare profil" );
        mProgressDialog.setMessage( "Va rugam asteptati" );
        mProgressDialog.setCanceledOnTouchOutside( false );
        mProgressDialog.show();

        mUsersDatabasee.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String display_name  = dataSnapshot.child( "displayName" ).getValue().toString();
                String status = dataSnapshot.child( "status" ).getValue().toString();
                String image = dataSnapshot.child( "image" ).getValue().toString();

                mProfilName.setText( display_name );
                mProfilStatus.setText( status );

                Picasso.with( ProfilActivity.this ).load( image ).placeholder( R.drawable.default_avatar ).into( mProfilImage );

                mProgressDialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );



    }
}
