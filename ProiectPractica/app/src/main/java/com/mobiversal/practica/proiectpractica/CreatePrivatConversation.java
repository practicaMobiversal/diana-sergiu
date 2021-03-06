package com.mobiversal.practica.proiectpractica;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mobiversal.practica.proiectpractica.fragments.ConversationFragments;

/**
 * Created by Lenovo on 19.07.2017.
 */


public class CreatePrivatConversation extends AppCompatActivity {

    private EditText mPrivat;
    private Button mAddGroupPrivat;
    FirebaseAuth mAuth;
    private DatabaseReference groupUser;
    private DatabaseReference userPhoneNumber;
    private FirebaseUser mAuth1;
    private String groupnume;
    private DatabaseReference mDatabase;
    private String mCurrent_state;
    private FirebaseUser user;
    private DatabaseReference mConversaData;
    private DatabaseReference pGroup;

    public CreatePrivatConversation() {
    }

    public CreatePrivatConversation(String groupnume) {
        this.groupnume = groupnume;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_privat_conversation );

        mAuth = FirebaseAuth.getInstance();
        mAddGroupPrivat = (Button) findViewById( R.id.button8 );
        mPrivat = (EditText) findViewById( R.id.privat_name );
        user = FirebaseAuth.getInstance().getCurrentUser();
        pGroup = FirebaseDatabase.getInstance().getReference();




        mAddGroupPrivat.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String privatGroupName = mPrivat.getText().toString();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRefe = database.getReference( "Groups" ).child( "Privat" ).push();
                mAuth1 = FirebaseAuth.getInstance().getCurrentUser();
                PrivatGroup grup = new PrivatGroup( privatGroupName );
                myRefe.setValue( grup );


//                Intent chat = new Intent( getApplicationContext(), ChatPrivat.class );
//                String privatId =  (String) v.getTag();
//                chat.putExtra( "user_id", privatId );
//                startActivity( chat );
            }
        } );
    }


}