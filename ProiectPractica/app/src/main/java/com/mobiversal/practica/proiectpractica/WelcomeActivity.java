package com.mobiversal.practica.proiectpractica;




              import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    //private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnSkip, btnNext;
    private PreferenceManager prefManager;
    private Button mRegBtn;
    private Button mRegBtn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_screen);
        mRegBtn = (Button) findViewById(R.id.button4);
        mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg_intent = new Intent(WelcomeActivity.this, PhoneAuthActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(reg_intent);
            }
        });

    }
}