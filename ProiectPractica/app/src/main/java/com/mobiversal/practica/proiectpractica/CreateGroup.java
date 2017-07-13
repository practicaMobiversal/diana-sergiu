package com.mobiversal.practica.proiectpractica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateGroup extends AppCompatActivity {

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

    }

    private void NewGroup(String groupId, String nume){

        CreateGroup group = new CreateGroup( nume );

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefe = database.getReference("groups").child( "public" ).push();
        myRefe.child( "name" ).setValue( "Oradea - Beius" );
        myRefe.child( "name" ).setValue( "Oradea - Bucuresti" );

    }
}
