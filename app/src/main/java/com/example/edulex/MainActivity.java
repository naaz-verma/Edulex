package com.example.edulex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button si_su,explore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainactivity);

        si_su = (Button) findViewById(R.id.su_si);
        explore = (Button) findViewById(R.id.explore);


        si_su.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sign = new Intent(getApplicationContext(),sign_up.class);
                startActivity(sign);
            }
        });

        explore.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               Intent intent2 = new Intent(MainActivity.this, dashboard.class);
                startActivity(intent2);
            }
        });
    }

}

