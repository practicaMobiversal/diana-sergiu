package com.mobiversal.practica.proiectpractica;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Lenovo on 05.07.2017.
 */

public class SecondActivity extends AppCompatActivity {
private static String TAG="SecondActivity";
  //  private FirebaseListAdapter<ChatMessage> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.second_activity);



        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //


        //Intent intent = new Intent (this, MainActivity.class);
        //startActivity(intent);

        //   }
    }





    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG , "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG , "onResume");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG , "onDestroy");
    }
}
