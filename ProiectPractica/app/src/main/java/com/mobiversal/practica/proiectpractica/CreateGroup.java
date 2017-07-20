package com.mobiversal.practica.proiectpractica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mobiversal.practica.proiectpractica.fragments.Groups;

public class CreateGroup extends AppCompatActivity {
    private EditText mCreateGroup;
    private Button mAddGroup;
    private EditText mNameField;
    private EditText mPhoneNumber;
    private FirebaseAuth mAuth;
    private DatabaseReference groupUser;
    private DatabaseReference userPhoneNumber;
    private FirebaseUser mAuth1;
    private String groupnume;
    private DatabaseReference mDatabase;

    public CreateGroup(){};
    public CreateGroup(String groupnume){
        this.groupnume= groupnume;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        Intent intent = getIntent();

        mAuth = FirebaseAuth.getInstance();
        mAddGroup = (Button) findViewById(R.id.button2);
        mCreateGroup = (EditText) findViewById(R.id.editText2);




        mAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String groupName = mCreateGroup.getText().toString();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Groups").child("Public").push();
                mAuth1 = FirebaseAuth.getInstance().getCurrentUser();
                // String userName = mAuth1.getDisplayName();
                // String phoneNumber = mAuth1.getPhoneNumber();
               // groupUser = FirebaseDatabase.getInstance().getReference().child("users").child(current_id).child(phoneNumber);
               // userPhoneNumber

               // groupUser.setValue(current_id, groupName, );


                //creare obiect de tip grup
 PublicGroup grup = new PublicGroup(groupName);                myRef.setValue(grup);


            }
        });    }
}


