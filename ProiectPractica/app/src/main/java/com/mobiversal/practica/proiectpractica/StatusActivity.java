package com.mobiversal.practica.proiectpractica;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StatusActivity extends AppCompatActivity {

    private TextInputLayout mStatusInput;
    private Button mSaveStatus;
    private DatabaseReference mStatusDatabase;
    private FirebaseUser mCurrentUser;
    private ProgressDialog mProgress;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_status );

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        String current_id = mCurrentUser.getUid();
        mStatusDatabase = FirebaseDatabase.getInstance().getReference().child( "users" ).child( current_id );

        mProgress = new ProgressDialog( this );



        mStatusInput = (TextInputLayout) findViewById( R.id.status_input );
        mSaveStatus = (Button) findViewById( R.id.status_button );


        mSaveStatus.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mProgress = new ProgressDialog(StatusActivity.this );
                mProgress.setTitle( "Salvare modificari" );
                mProgress.setMessage( "Va rugam asteptati!" );
                mProgress.show();

                String status = mStatusInput.getEditText().getText().toString();

                mStatusDatabase.child( "status" ).setValue( status ).addOnCompleteListener( new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){
                            mProgress.dismiss();
                        } else {
                            Toast.makeText( getApplicationContext(), "Au aparut erori la salvarea modificarii!", Toast.LENGTH_LONG );
                        }
                    }
                } );

                Intent viewprofil = new Intent( StatusActivity.this, ViewProfill.class );
                startActivity( viewprofil );

            }
        } );

    }
}
