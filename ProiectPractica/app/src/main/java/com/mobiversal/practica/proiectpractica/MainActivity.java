package com.mobiversal.practica.proiectpractica;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
private  static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG , "onCreate");
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message").child("2");
        DatabaseReference newusers = database.getReference("users").child("222005555");

        Log.d("App", newusers.getKey());
        newusers.child("profil").child("nume").setValue("ana");

        DatabaseReference groupspublic = database.getReference("groups").push();
        groupspublic.child("public").child("nume").setValue("Oradea - Cluj");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }



            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Intent intent = new Intent (this, SecondActivity.class);
        //startActivity(intent);
    }

    public void SendMessage(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);


    }

    public void CreateNewGroup(View view){
        Intent intent = new Intent(this, CreateGroup.class);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton) ;

        startActivity(intent);
    }

    public void ViewProfil(View view){
        Intent intent = new Intent(this, ViewProfill.class);
        startActivity(intent);
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
    protected void onPause() {
        super.onPause();
        Log.d(TAG , "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG , "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG , "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG , "onDestroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
