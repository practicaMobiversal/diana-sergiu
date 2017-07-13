package com.mobiversal.practica.proiectpractica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewProfill extends AppCompatActivity {
    private DatabaseReference pUserReference;
    private FirebaseUser mCurrentUser;

    private TextView mName;
    private TextView mStatus;
    private CircleImageView mDisplayImage;

    private Button mStatusButton;
    private Button mImageButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profill);
        Intent intent = getIntent();

        mDisplayImage = (CircleImageView) findViewById( R.id.circleImageView );
        mName = (TextView) findViewById( R.id.profil_name );
        mStatus = (TextView) findViewById( R.id.profil_status ) ;
        mImageButton =(Button) findViewById( R.id.profil_b_img ) ;
        mStatusButton = (Button) findViewById( R.id.button4 );


        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        String current_user = mCurrentUser.getUid();

        pUserReference = FirebaseDatabase.getInstance().getReference().child( "users").child( current_user );

        pUserReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child( "displayName" ).getValue().toString();
                String status = dataSnapshot.child( "status" ).getValue().toString();

                mName.setText( name );
                mStatus.setText( status );
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );

        mStatusButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent status_intent  = new Intent( ViewProfill.this, StatusActivity.class );
                startActivity( status_intent );

            }
        } );




    }
}
